import os
import shutil
from pathlib import Path
from typing import cast

from cloudpathlib import AnyPath, CloudPath

# `AnyPath` gives `Path` for a local directory and `CloudPath` for an "s3://"
StoragePath = Path | CloudPath
STORAGE = cast(StoragePath, AnyPath(os.environ.get("MAC_STORAGE", Path.cwd())))
UPLOADS_DIR = STORAGE / "uploads"
RESULTS_DIR = STORAGE / "results"
UPLOAD_CHUNK_SIZE = 1 << 20  # 1 MiB


def copy_into_storage(source: Path, destination: StoragePath) -> None:
    with source.open("rb") as src, destination.open("wb") as dst:
        shutil.copyfileobj(src, dst)
