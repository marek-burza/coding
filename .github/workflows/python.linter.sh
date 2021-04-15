#!/usr/bin/env sh

set -e

pip3 --disable-pip-version-check install -r .github/workflows/python.linter.requirements.txt
find . -iname "*.py" | xargs pylint --disable=C0103,C0111,R0801,R0201,R0903
pycodestyle .
flake8
bandit -r .
