# MCP server over microservices, and evaluating an agent workflow

Prompt shape: "We have a microservice architecture. Design a single MCP endpoint that agents connect to. Then: an agent uses it to build semantic data models from already-ingested data; how do you test and evaluate that?"

## Requirements (ask, then assume)

- Who are the clients: internal agents, customer agents, both? (assume both)
- Read-only or also write actions? (assume mostly read, some writes)
- Whose permissions does the agent use? (assume the calling user's, delegated)
- How many services and operations? (assume ~30 services, hundreds of endpoints)
- What does "good" mean for a semantic model? (assume it matches a reviewed reference and its queries run)

## Part 1: the MCP gateway

```text
agent → MCP gateway (single endpoint, OAuth, per-tool scopes, rate limits, audit)
      → tool registry (curated tools, schemas, descriptions, versions)
      → router → microservices (internal APIs)
traces per tool call → observability
```

- **Task-level tools, not 1:1 CRUD:** agents do better with fewer, intent-shaped tools ("describe dataset", "propose model") than with every endpoint. Generate a draft from OpenAPI specs, then curate.
- **Tool count:** agents degrade with too many tools; group them into namespaces or use tool search/discovery so the agent sees a few dozen at a time.
- **Token budget:** paginate and summarise results; cap response size.
- **Identity:** propagate the user's identity to services; per-tool scopes; write tools need confirmation or a dry-run mode.
- **Versioning:** tool schemas are a public API; version them and keep old versions during migration.

## Part 2: evaluation

- **Golden set:** representative datasets with reviewed reference semantic models (entities, relationships, measures).
- **Automatic metrics:** schema validity; entity and relationship precision/recall against the reference; generated queries execute and return plausible results.
- **Judgement metrics:** LLM-as-judge with a rubric, calibrated against human reviews on a sample.
- **Process metrics from traces:** tool-selection accuracy, steps, cost, latency, error recovery.
- **Non-determinism:** run each case several times and report a pass rate, not one result.
- **Regression suite in CI:** run on every prompt, model or tool change; pin model versions; block on drops.
- **Online:** user acceptance and edit rate of proposed models; sample failures back into the golden set.

## Failure modes, observability, security

- Prompt injection through data: treat tool outputs as data, never instructions; restrict write tools; allowlists.
- Audit log of every tool call with user, inputs and outputs (redacted).
- Rate limits per user and per tool; circuit breakers to failing services.

## Traps

- Keep both parts separate and name them before answering.
- Lead with one concrete example of each metric rather than a list of frameworks.
