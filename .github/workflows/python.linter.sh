#!/usr/bin/env sh

set -e

unset VIRTUAL_ENV
uv venv --allow-existing
uv pip install -r .github/workflows/requirements.txt
echo "--- ruff ---"
uv run --no-project ruff check --config .github/linters/.ruff.toml algorithms/code
uv run --no-project ruff format --check --diff --config .github/linters/.ruff.toml algorithms/code
echo "--- ty ---"
uv run --no-project ty check algorithms/code
echo "--- mypy ---"
uv run --no-project mypy algorithms --exclude banking --exclude mac
echo "--- bandit ---"
uv run --no-project bandit --skip B101 -r . -x ./.venv
echo "--- complexipy ---"
uv run --no-project complexipy algorithms/code --max-complexity-allowed 25
