FROM python:3.13.14-slim-bookworm

COPY --from=ghcr.io/astral-sh/uv:0.11.30 /uv /uvx /bin/

COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:1.0.1 /lambda-adapter /opt/extensions/lambda-adapter

ENV UV_LINK_MODE=copy \
    UV_COMPILE_BYTECODE=1 \
    UV_NO_CACHE=1 \
    UV_HTTP_TIMEOUT=60 \
    VIRTUAL_ENV=/app/venv \
    CLOUDPATHLIB_FILE_CACHE_MODE=close_file \
    AWS_LWA_PORT=8000
RUN --mount=type=bind,source=uv.lock,target=uv.lock \
    --mount=type=bind,source=pyproject.toml,target=pyproject.toml \
    /bin/uv venv $VIRTUAL_ENV && \
    /bin/uv sync --active --frozen --no-dev --no-install-project
RUN --mount=type=bind,source=.,target=/app/shrubbery \
    /bin/uv pip install --python $VIRTUAL_ENV /app/shrubbery

WORKDIR /app/volume

ENTRYPOINT ["/bin/uv", "run", "uvicorn", "mac.main:app", "--port", "8000"]
