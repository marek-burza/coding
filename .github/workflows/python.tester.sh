#!/bin/sh

set -e

find algorithm-design -type d -exec touch {}/__init__.py \;
python -m unittest discover -s . -p "*.py"
