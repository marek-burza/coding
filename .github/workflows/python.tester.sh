#!/usr/bin/env sh

set -e

export VIRTUAL_ENV=/tmp/venv
UV_VENV_CLEAR=1 uv venv $VIRTUAL_ENV
uv pip install --python $VIRTUAL_ENV -r .github/workflows/requirements.txt
uv run --active --no-project coverage run -m pytest . -o python_files="*.py" -p no:cacheprovider --ignore=algorithms/code/other/banking --ignore=algorithms/code/other/mac
uv run --active --no-project coverage report --show-missing --omit="*/__init__.py" -i
uv run --active --no-project coverage xml -i
(find . -name __pycache__ -exec rm -rf {} \; 2>/dev/null) || true
