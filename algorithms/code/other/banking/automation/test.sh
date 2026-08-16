#!/bin/sh

set -e

CONTAINER=banking-db

if [ -z "${BANKING_DATABASE_URL}" ]; then
    if [ -z "$(docker ps --quiet --filter "name=^${CONTAINER}$")" ]; then
        docker rm "${CONTAINER}" > /dev/null 2>&1 || true
        docker run --detach --rm --name "${CONTAINER}" \
            --env POSTGRES_USER=banking \
            --env POSTGRES_PASSWORD=banking \
            --env POSTGRES_DB=banking \
            --publish 5432:5432 \
            --tmpfs /var/lib/postgresql \
            postgres:18-alpine
    fi

    for _ in $(seq 30); do
        docker exec "${CONTAINER}" pg_isready --quiet --username banking && break
        sleep 1
    done

    export BANKING_DATABASE_URL="postgresql+psycopg://banking:banking@localhost:5432/banking"
fi

uv sync
uv run alembic upgrade head
uv run coverage run -m pytest -v
uv run coverage report
