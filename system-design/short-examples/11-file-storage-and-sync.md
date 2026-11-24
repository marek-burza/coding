# File storage and sync (Google Drive / Dropbox-like)

Prompt shape: "Design a service where users store files in folders, sync them across devices, share them, and recover old versions."

## Requirements (ask, then assume)

- Scale: users and storage? (assume 100M users, 10M daily active, average 2 GB each)
- File sizes? (assume up to 10 GB)
- Sync across devices, offline edits? (assume yes)
- Sharing: users, groups, public links? (assume all three, view/edit permissions)
- Collaborative real-time editing of documents? (assume out of scope)

## Numbers

- Storage: 100M × 2 GB = 200 PB before dedup and replication.
- Uploads: 10M DAU × 2 files × 2 MB ≈ 40 TB/day ≈ 0.5 GB/s average.
- Metadata: billions of file and folder records; read-heavy (listing, sync checks).

## Data model

- `files`: `file_id, owner, parent_folder, name, current_version, deleted_at`
- `versions`: `file_id, version, size, chunk_list (hashes), created_at, device`
- `chunks`: `hash (SHA-256), size, storage_location, ref_count`
- `acl`: `resource_id, principal, role`; folder ACLs inherited
- `journal`: per-user ordered change log for sync (`cursor`, change type, file_id)

## Boxes

```
client (chunker, local index) → API gateway → metadata service (sharded by user/namespace)
                              → upload/download via pre-signed URLs → block storage (object store, content-addressed chunks)
metadata change → journal → notification service → other devices (long poll / push)
async: indexer (search, thumbnails), virus scan, garbage collector
```

## Deep dives (the probes)

- **Upload:** client splits into ~4 MB chunks, hashes them, asks which hashes are missing, uploads only those (resumable), then commits a new version atomically in metadata. Dedup comes for free.
- **Sync:** each device keeps a cursor into the user's change journal; notifications wake it; it fetches changed metadata, then only the chunks it lacks (delta sync).
- **Conflicts:** a commit includes the base version; if the current version differs, keep both ("conflicted copy") rather than overwrite.
- **Sharing:** ACL checks in the metadata service with caching; inherited folder permissions; share links are tokens with scope and expiry.
- **Versions and trash:** old versions keep their chunk lists; deleting decrements chunk ref counts; a garbage collector removes unreferenced chunks after a grace period.
- **Sharding metadata:** by user or shared namespace, so most operations stay on one shard; shared folders get their own namespace.

## Failure modes, observability, security

- Encryption in transit and at rest; per-chunk keys or envelope encryption.
- Rate limits per user; abuse and malware scanning on shared files.
- Metrics: upload success rate, sync lag (journal head vs device cursor), metadata p99, storage growth.
