#!/usr/bin/env sh

set -e

find algorithm-design -type d | grep -v \/bin | grep -v \/obj | sed -e 's/$/\/__init__.py/' | xargs touch
coverage run -m unittest discover -s . -p "*.py"
coverage report --show-missing --omit="*/__init__.py"
coverage xml -i
(find -name __pycache__ -exec rm -rf {} \; 2> /dev/null) || true
(find -name __init__.py -exec rm -rf {} \; 2> /dev/null) || true
