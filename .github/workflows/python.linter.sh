#!/usr/bin/env sh

set -e

echo "--- ruff ---"
uv run --with-requirements .github/workflows/requirements.txt --no-project ruff check --select I,E,B,SIM algorithms/code
uv run --with-requirements .github/workflows/requirements.txt --no-project ruff format --check --diff --config .github/linters/.ruff.toml algorithms/code
echo "--- ty ---"
uv run --with-requirements .github/workflows/requirements.txt --no-project ty check algorithms/code
echo "--- mypy ---"
uv run --with-requirements .github/workflows/requirements.txt --no-project mypy algorithms --exclude banking --exclude mac
echo "--- bandit ---"
uv run --with-requirements .github/workflows/requirements.txt --no-project bandit --skip B101 -r .
echo "--- complexipy ---"
uv run --with-requirements .github/workflows/requirements.txt --no-project complexipy algorithms/code --max-complexity-allowed 25
