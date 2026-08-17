# Code Challenge - Senior Software Engineer - Cloud

**TL;DR**: The __Introduction__ section only repeats the contents of the assignment, for my write-up and instructions jump to __Implementation__. 

## Introduction

Your implementation for the code challenge will be the base for a more in depth technical discussion.

### Task 1: API Implementation

#### IEEE 802.11 MAC address parser
    
**Important**: The implementation should be in `CloudFormation` and `Python`.

MAC addresses of the `IEEE 802.11` standard always start with the `00:0F:AC` prefix.
You should build an API which would filter through a file to keep only `IEEE 802.11` MAC addresses
and store them for later retrieval.
For simplicity, you should only consider MAC addresses which have `12` hexadecimal digits in total.
For example: `00:0F:AC:15:20:13` or `00-0f-ac-15-20-13` are valid MAC addresses.

Your service should allow the following:

- Submit a file to the endpoint to be processed. This endpoint should return a Task ID which could be used to fetch or delete the results. Example files are provided, see `mac_addresses_1.txt`, `mac_addresses_2.txt`, `mac_addresses_3.txt`, `...`
- Using the Task ID, a user should be able to retrieve the results of the submitted file.
- Using the Task ID, a user should be allowed to delete the results associated to the Task ID.
- An endpoint to fetch all Task IDs.
- Be aware of duplicates.

### Task 2: Testing

Implement suitable tests and please provide instructions on how to run the tests in a `README` file.

### Task 3: Deployment

How would you deploy your solution?

- If you have an AWS account: Please provide the script/code that deploys your app to AWS and the link to test it live
- If you do not have an AWS account: Please describe how you would deploy your app in details.

### Deliverable

- A `README` file which includes instructions on which links to open for testing the API and instructions on how to run the tests.
- Please provide your source code.
- Optional: Postman collection for easy testing

If you have used AI for this assignment, please mention exactly which parts have been so.

**Have fun!**


## Implementation

### Task 1: API Implementation

#### Web framework & server

I picked `FastAPI` since recently it is most familiar to me, it is fast, self documenting, and well integrated with Pydantic for validation (though I did use `Flask`, `Django`, `aiohttp` in the past). Similar applies to the choice of `uvicorn` - most familiar to me recently, fast ASGI web server, frequently coupled with `FastAPI` (though, on occasion, I also used `uWSGI` in the past).

#### API Design

Given that the assignment is centered around __files__ and their processing I implemented the following endpoints:

Every endpoint sits under a single `/v1/filtering` resource - a filtering task - and the HTTP verb, not the path, says what is being done to it:

- `POST /v1/filtering` - to submit a file for processing; returns a `{"task_id": TASK_ID}` JSON
- `GET /v1/filtering` - to fetch all `Task ID`s; returns a `{"task_ids": [TASK_ID_1, ...]}` JSON
- `GET /v1/filtering/{task_id}` - to retrieve the results of the submitted file; returns a plain text file only with the sanitized `IEEE 802.11` MAC addresses (I opted for plain text as the simplest option since it was not specified, symmetric to the `POST`, and picking another format would make sense depending on what would consume it downstream)
- `DELETE /v1/filtering/{task_id}` - to delete the results of the submitted file

Keeping one path for the collection and one for its members follows [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) & [Richardson Maturity Model](https://en.wikipedia.org/wiki/Richardson_Maturity_Model) conventions: no verbs in the path, and `POST`/`GET`/`DELETE` carry the intent. Level 3 (hypermedia controls) is the one step not taken, as it would be excessive for a quick assignment.

#### Task IDs & De-duplication

Given that the API operates on files on input and output I opted to go for content-addressed storage. `Task ID` is the `SHA-256` digest of the uploaded file. This gives us very low collision probability (lower than for the commonly used UUID4). We do pay some computational cost for the `SHA-256` digest but in return we get deduplication (identical files land on the same `Task ID`, and do not get reprocessed so long as the results are not deleted).

The assignment cautions to be aware of the duplicates, and besides file upload duplication there might be duplicate addresses in the files - i.e. the same address but written with a different separator (`:` or `-`, or none at all) or in a different letter case, thus the results for the submitted file are also standardized on the canonical form (colon-separated, uppercase hexadecimal digits) and de-duplicated on the fly during filtering.

#### Validation & Sanitization

The service is using `Pydantic` for validation of the simple structures exchanged via the API. Notably, the `Task ID` is validated to be exactly 64 lowercase hexadecimal characters (preventing path traversal attempts).

See file: `src/mac/main.py`

The input file is sanitized by dropping any characters which are not: hexadecimal digits or `end-of-line`. The `:` and `-` separators are dropped alongside the rest of the noise, which keeps a candidate line to bare hexadecimal digits and lets the separator style (`:`, `-`, mixed, or none) be a non-issue for matching. The provided files suggested that they are simply ASCII-encoded (rather than `utf-8` or `utf-16`), hence a simple byte-wise filtering could be applied to avoid loading entire file into memory (and if speed is key it could be turned into a chunk-wise processing to keep the memory constant but process faster).

The address is validated against the case-insensitive pattern `000FAC` + exactly 6 further hexadecimal digits (12 in total), thus if there are less or more than 12 hexadecimal digits until the `end-of-line` or `end-of-file` then the address is skipped. `_standardized` then only rewrites what matched into the standard form - colons between the octets and uppercase hexadecimal digits. Special care was taken also to prevent buffer overflow attempts with very long addresses (single MAC address per line is assumed).

See files: `src/mac/patterns.py` & `src/mac/sanitization.py`

#### Packaging

The Python code is a proper Python package, built with `uv`.

The service is packaged as a Docker container, I picked a small variant of a base image (`python:3.13.14-slim-bookworm`) for a quick spin-up of an instance and to keep the storage costs for the Docker registry in check. Knowing that I will be later going for `Lambda` as the deployment target, I opted for `Debian`-based image rather than `Alpine` since the former is `glibc`-based (compatible with `Lambda` out of the box) and the latter `musl`-based (and causing issues on `Lambda`). `Alpine` would be more applicable if the deployment target were e.g. `ECS` or `EKS`.

Currently, the container image leaves the `uv` binary in to keep the `Dockerfile` simple, however, it would be easy to slim it further down with a multistage approach.

The installation of Python package dependencies and the package itself is split into two `RUN` entries to keep the local development iteration fast (change in the code, but not its dependencies, does not require their refetch on container rebuild).

#### Running Locally

To build the service locally:

```shell
podman build -t mac .
```

To run the service locally:

```shell
podman run --rm -it --network host mac
```

To run calls to the service:

```shell
export TASK_ID=$(curl -F "file=@tests/assets/mac_addresses_1.txt" http://127.0.0.1:8000/v1/filtering  | jq -r .task_id)
curl http://127.0.0.1:8000/v1/filtering
curl http://127.0.0.1:8000/v1/filtering/$TASK_ID
curl -X DELETE http://127.0.0.1:8000/v1/filtering/$TASK_ID
curl http://127.0.0.1:8000/v1/filtering/$TASK_ID
```

### Task 2: Testing

What is covered:

- `tests/test_api.py` - every endpoint (e.g. status codes, validation, path traversal).
- `tests/test_auth.py` - authentication on every public endpoint.
- `tests/test_extraction.py` - the matching/sanitizing rules.
- `tests/test_roles.py` - consumer role, its presence, etc.
- `tests/test_sample_files.py` - all ten provided `mac_addresses_*.txt` files.
- `tests/test_storage.py` - behavior of the storage, local and mocked S3.

To prepare the `venv` run:

```shell
uv sync
```

To run the tests (implemented with `pytest`):

```shell
uv run pytest -v
```

Or, if you want to include coverage check:

```shell
uv run coverage run -m pytest -v
uv run coverage report
```

I also like to run other checks:

- Check imports and basic linting: `uv run ruff check --select I,E,B,SIM src`
- Check source code formatting: `uv run ruff format --check --diff`
- Keep the dependencies trim to the project needs: `uv run deptry .`
- Run a type checker (still `mypy` since `ty` does not have the rule coverage yet): `uv run mypy src`
- Run basic security linter: `uv run bandit -r src`
- And to keep the code well-structured: `uv run complexipy src --max-complexity-allowed 25`
- Validate the `CloudFormation` templates: `uv run cfn-lint infra/*.yaml`
- Scan the `CloudFormation` templates for misconfigurations: `uv run checkov -d infra --quiet --compact`

### Task 3: Deployment

#### Cloud Compute & Container Registry

I picked `Lambda` as the most lightweight option, `ECS` or `EKS` would require justification for the additional code and infrastructural overhead and costs.

Given that I wanted to use the same `Docker` container for local and cloud-deployed environments, I needed to inject `AWS Lambda Web Adapter` into the `Dockerfile`. On `Lambda` it polls the Runtime API and forwards each invocation to the `uvicorn` the image runs, so the entrypoint can stay the same. The cost is an additional process running on `Lambda` (and additional HTTP hop) - to eliminate it the base image would need to be switched to `public.ecr.aws/lambda/python:3.13` but running it locally would require a different setup and the container image would be much larger.

This of course required also `ECR`:

- For the encryption I picked `AES256` - `KMS` would make sense when key management control is needed (with key rotation policies, auditing of key usage, or for regulatory compliance).
- I also restricted mutability of images (moving `latest` tag being the only exception)
- Added policy for `Lambda` to be able to access it

#### API

For simplicity, I coupled `Lambda` with `API Gateway` to expose the endpoints. Both come with important limitations to be aware of - `API Gateway` caps an HTTP API request at 10 MB, `Lambda` caps a synchronous invocation payload at 6 MB, and Base64 inflation makes the practical limit roughly 4.5 MB for the file upload (see [`API Gateway`](https://docs.aws.amazon.com/apigateway/latest/developerguide/http-api-quotas.html) & [`Lambda`](https://docs.aws.amazon.com/lambda/latest/dg/gettingstarted-limits.html) quotas). The provided files are smaller than the limit but if higher cap is needed then this would require a different approach - e.g. use presigned `S3` `PUT` URL or move to `ECS` with `ALB`.

#### Cloud Storage

Given that the service is operating on files (rather than a database) the `S3` was a natural pick for the storage.

Using `FUSE` and mounting `S3` to the `Lambda` container is not possible (while it is possible to mount object storage on serverless container on `Azure`, `Lambda` only allows `/tmp` ephemeral storage and `EFS` mounts, and gives no `/dev/fuse`). Thus, because the original code was operating on `pathlib.Path`, I used `cloudpathlib` to access `S3` with a `pathlib.Path`-compatible interface. I opted for `cloudpathlib` instead of the more popular `smart_open` because the `GET /v1/filtering` endpoint relied on listing besides just `open` (`fsspec` + `s3fs` could be another option but would require more scaffolding). Since `/tmp` survives across warm `Lambda` invocation I needed to be cautious about cleaning up files to not fill the ephemeral storage up - that is also why `CLOUDPATHLIB_FILE_CACHE_MODE` is set to `close_file` to delete each cached download as its file handle closes.

Selection of the storage is governed by the `MAC_STORAGE` environment variable - points to an `S3` bucket when deployed on `AWS` and defaulting to current working directory for local development.

Note on paging: Listing `S3` bucket and returning a complete set of `Task ID`s may be costly, thus - depending on the planned use of the service - it would make sense to add paging to the `GET /v1/filtering` endpoint.

Note on storage of processing results: I briefly considered using `DynamoDB` or `RDS` for storing the results but decided against both. Results are plain-text blobs, so a key-value store fits poorly: `DynamoDB` caps items at 400 kB, and an idiomatic pattern would still keep the file in `S3` with only a pointer in the table (an alternative could be to have one MAC address per DB row and an `ID` column, and a table mapping `ID` to the longer `Task ID` - quite excessive for a simple assignment). `RDS` adds an always-on instance the cost of which would need to be justified by a downstream use case. Since downstream use case is not specified, I opted for the simplest solution: the result file in `S3`.

#### Task Queue

Rather than relying on "long running" `BackgroundTasks` (which I used for local development) I opted to decouple processing from API call by coupling `s3:ObjectCreated` event (scoped to `/uploads` prefix) with an `SQS` queue serviced by a `Lambda` function (`POST /events` endpoint packaged in the same container as the API to make sure the logic stays in sync).

Selection of the processing variant is governed by the `MAC_BACKGROUND_PROCESSING` environment variable - `true` uses `BackgroundTasks`, `false` does not.

The presence of the `POST /events` endpoint is governed by the `MAC_ROLE` environment variable - for `"consumer"` the endpoint is exposed,  for `"api"` it is not.

| Deployment           | MAC_ROLE | MAC_BACKGROUND_PROCESSING |
| -------------------- | -------- | ------------------------- |
| Local (API+Consumer) | api      | true                      |
| AWS API              | api      | false                     |
| AWS Consumer         | consumer | false                     |

As a side note: Picking `SHA-256` digest as the `Task ID` has the added benefit that in case of any duplication of tasks coming through `SQS` (re-play, retry, etc.) the files for which the results exist, do not get reprocessed.

See file: `src/mac/main.py`

### Auth

To have elementary endpoint protection for the deployed API and ease your interaction with the service - without the full authentication flow - I opted for setting a fixed HTTP basic access authentication instead of using `Cognito`.

The credential is governed by `MAC_BASIC_AUTH` in the form of `<username>:<salt_hexadecimal>:<scrypt_hexadecimal>`. The password is hashed with `scrypt` at close to the [OWASP recommended](https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html) cost parameter value (targeted at offline cracking) - to provide a good enough protection for the purpose of an assignment without making the `Lambda` call (running on underpowered instance) too sluggish. 

Additionally, calls to the API are throttled to 1 request per second, which caps trials a potential attacker might run as well as the costs this might incur.

The service is deployed at `https://gzxy5ghu94.execute-api.eu-central-1.amazonaws.com` and the credentials are provided separately to Rina Punko. Thus to interact with the service you need to replace in the `curl` commands above the `http://127.0.0.1:8000/` part of the URL with the one provided here, and add `-u $USERNAME:$PASSWORD`.

The benefits that using `Cognito` instead would give are:
- Proper user management and authentication flow
- Authentication would be taken out of compute budget of `Lambda`
- Follows the best practice of never designing your own security protocols

### CI & CloudFormation

I used GitLab CI, but since it was not mandated by the assignment I cover it here only briefly.

Important thing to mention is that I split the `CloudFormation` template in two to prevent a circular dependency - `Lambda` deployment depends on a `Docker` container image built and present in `ECR`, `Docker` container image build depends on `ECR` being present.

It uses four stages:

- `check` - which runs tests and linters
- `provision` - ensures that `ECR` is deployed (see `infra/StackRegistry.yaml` `CloudFormation` template)
- `build` - builds the `Docker` image and pushes it to `ECR`
- `deploy` - that ensures the `S3` storage, `SQS` queue, `Lambda` functions are deployed and wired together (see `infra/StackApp.yaml` `CloudFormation` template)

Note on CI credentials - for simplicity the pipeline authenticates to AWS with an IAM user's `AWS_ACCESS_KEY_ID` & `AWS_SECRET_ACCESS_KEY`, stored as masked (redacted in job logs) and protected (available only to pipelines on protected branches) CI/CD variables. This limits their exposure but the keys are still long-lived. Two improvements to address this would be:

- Replace the static keys with `OIDC` federation (GitLab issues a short-lived ID token and the job assumes a role (`sts:AssumeRoleWithWebIdentity`) and no long-lived secret is stored at all).
- Give `CloudFormation` a service role with a permissions boundary (and restricting permission of the CI).

Note on rollout environments: For the simple assignment I opted not to split the environment between staging and production, and also to stick to only one region.

## Postman Collection

The file `tests/assets/PostmanCollection.json` covers every endpoint but in a very simple fashion. Import it,
then set the password to the one provided to Rina Punko.

**Important: Run the `Upload a file` with the `mac_addresses_1.txt` file (the tests are targeting that file).** For the __Upload a file__ (`POST /v1/filtering`) endpoint, go to the `Body` tab and select the file from your disk as a value for the `file` key.

### AI Use

These items were written by me, without use of AI:
- `src`, `pyproject.toml`, `Dockerfile`, `.gitlab-ci.yml`, `README.md`, `tests/test_api.py`, `tests/test_auth.py`, `tests/test_extraction.py`, `tests/test_roles.py`

Elements, for which I used AI:
- `LifecyclePolicyText` contents in `infra/StackRegistry.yaml` file (since not so important for the assignment, the rest of the file is mine).
- Entries with `Type: AWS::ApiGatewayV2::*` in `infra/StackApp.yaml` (I have not used `v2` before so to stay fast I let AI generate an example and checking the reference documentation I reworked it for my purpose).
- `REFERENCE` & `reference_addresses` in `tests/test_sample_files.py` - to get an independent re-implementation of the RegExp to strip spaces, match whole lines, normalise.
- Postman collection. I have not used Postman much - usually preferring programmatic verification methods, thus I opted to generate it with AI, given a couple of carfully prescribed tests.
- `s3_client` fixture in `tests/test_storage.py` - the AI helped me to discover a way to mock-test S3 storage (I wasn't aware of `LocalS3Path` existance), the tests using it are mine
- After adding `checkov` and resolving myself some of the issues it found in `infra/StackApp.yaml`, I let the AI add inline skip suppression for the ones I disagreed with.

## Appendices

### Appendix A: Teardown

AWS CLI:

```shell
podman run -it --rm -v $PWD:/w -w /w --entrypoint /bin/bash amazon/aws-cli
```

Login:

```shell
aws login --remote
```

Finally, to teardown:
Once reviewed to be taken down (order matters, delete S3 content before):

```shell
aws cloudformation delete-stack --stack-name mac-stack-app
aws cloudformation wait stack-delete-complete --stack-name mac-stack-app
aws cloudformation delete-stack --stack-name mac-stack-registry
aws cloudformation wait stack-delete-complete --stack-name mac-stack-registry
```

### Appendix B: CI permissions

Caution: `mac-ci-policy.json` is the quick'n'dirty policy for the CI user, use `OIDC` federation in production.

It is attached as a managed policy rather than inline (inline user policies are capped at 2048 characters, managed policies allow more).

Look up the ID of the AWS account:

```shell
export AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
```

Create the policy once:

```shell
aws iam create-policy --policy-name mac-ci-policy \
  --policy-document file://mac-ci-policy.json
aws iam attach-user-policy --user-name admin \
  --policy-arn arn:aws:iam::$AWS_ACCOUNT_ID$:policy/mac-ci-policy
```

Create new policy version afterwards:

```shell
aws iam list-policy-versions \
  --policy-arn arn:aws:iam::$AWS_ACCOUNT_ID$:policy/mac-ci-policy \
  --query 'sort_by(Versions[?IsDefaultVersion==`false`], &CreateDate)[0]VersionId' \
  --output text | grep -v '^None$' | xargs -r -I {} \
aws iam delete-policy-version \
    --policy-arn arn:aws:iam::$AWS_ACCOUNT_ID$:policy/mac-ci-policy --version-id {}
aws iam create-policy-version \
  --policy-arn arn:aws:iam::$AWS_ACCOUNT_ID$:policy/mac-ci-policy \
  --policy-document file://mac-ci-policy.json --set-as-default
```

## Appendix C: Generating Basic Auth

The public routes accept HTTP Basic credentials when `MAC_BASIC_AUTH` is set.

Generate a credential once:

```shell
uv run python -c "
import secrets
from mac.auth import hash_password
password = input('password: ')
salt = secrets.token_bytes(16)
print('MAC_BASIC_AUTH:', f'mac:{salt.hex()}:{hash_password(password, salt)}')
"
```

Put the `MAC_BASIC_AUTH` in a GitLab CI/CD secret. Then:

```shell
curl -u mac:$PASSWORD -F "file=@tests/assets/mac_addresses_1.txt" "$API/v1/filtering"
```

## Appendix D: Agent Toolkit for AWS (`aws-core`) plugin

The `aws-core` plugin is the entry-point plugin of AWS's officially supported Agent Toolkit, pairing the managed AWS MCP Server with ~19 curated skills so Claude Code works from current, AWS-authored guidance rather than training-data recall. The skills cover service selection, CDK/CloudFormation, serverless, containers, databases, networking, IAM, observability, billing, and SDK usage - each with secure defaults and best practices - and load on demand, so only their descriptions sit in context until a task matches. The MCP Server adds authenticated access to 300+ AWS APIs, sandboxed script execution, and live documentation search, under IAM condition keys that distinguish agent from human actions plus CloudTrail and CloudWatch visibility. The purpose is production-ready infrastructure output rather than plausible-looking code.

To install, run:

```shell
claude plugin install aws-core@claude-plugins-official --scope project
```
