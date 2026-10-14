#!/usr/bin/env sh

set -e

export VIRTUAL_ENV=/tmp/venv
UV_VENV_CLEAR=1 uv venv $VIRTUAL_ENV
uv pip install --python $VIRTUAL_ENV -r .github/workflows/requirements.txt
echo "--- ruff ---"
uv run --active --no-project ruff check --select I,E,B,SIM algorithms/code
uv run --active --no-project ruff format --check --diff --config .github/linters/.ruff.toml algorithms/code
echo "--- ty ---"
uv run --active --no-project ty check algorithms/code
echo "--- mypy ---"
uv run --active --no-project mypy algorithms --exclude banking --exclude mac
echo "--- bandit ---"
uv run --active --no-project bandit --skip B101 -r .
echo "--- complexipy ---"
uv run --active --no-project complexipy algorithms/code --max-complexity-allowed 25
