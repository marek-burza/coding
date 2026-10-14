#!/usr/bin/env sh

set -e

unset VIRTUAL_ENV
uv venv --allow-existing
uv pip install -r .github/workflows/requirements.txt
uv run --no-project coverage run -m pytest . -o python_files="*.py" -p no:cacheprovider --ignore=algorithms/code/other/banking --ignore=algorithms/code/other/mac
uv run --no-project coverage report --show-missing --omit="*/__init__.py" -i
uv run --no-project coverage xml -i
(find . -name __pycache__ -exec rm -rf {} \; 2>/dev/null) || true
