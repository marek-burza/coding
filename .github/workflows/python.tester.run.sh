#!/usr/bin/env sh

set -e

coverage run -m unittest discover -s . -p "*.py"
coverage report --show-missing --omit="*/__init__.py"
coverage xml -i
(find . -name __pycache__ -exec rm -rf {} \; 2> /dev/null) || true
