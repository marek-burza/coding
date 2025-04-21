#!/usr/bin/env sh

set -e

uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project coverage run -m unittest discover -s . -p "*.py"
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project coverage report --show-missing --omit="*/__init__.py" -i
uv run --with-requirements .github/workflows/python.environment.requirements.txt --no-project coverage xml -i
(find . -name __pycache__ -exec rm -rf {} \; 2>/dev/null) || true
