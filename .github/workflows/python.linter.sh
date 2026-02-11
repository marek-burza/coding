#!/usr/bin/env sh

set -e

echo "--- ruff ---"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project ruff check --select I algorithms/code
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project ruff format --check --diff --config .github/linters/.ruff.toml algorithms/code
echo "--- ty ---"
# uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project ty check algorithms/code
echo "--- isort ---"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project isort --settings-file .github/linters/.isort.cfg --check algorithms/code
echo "--- pycodestyle ---"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project pycodestyle --ignore=E203,W503 .
echo "--- flake8 ---"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project flake8 --ignore=E203,W503
echo "--- pylint ---"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project sh -c "find . -name '*.py' | grep -v banking_api | xargs pylint --disable=C0103,C0111,C0302,R0801,R0903"
echo "--- mypy ---"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project mypy algorithms --exclude banking_api
echo "--- bandit ---"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project bandit -r .
