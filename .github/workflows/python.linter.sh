#!/usr/bin/env sh

set -e

pip3 --disable-pip-version-check install -r .github/workflows/python.linter.requirements.txt
find . -iname "*.py" | xargs pylint --disable=C0103,C0111,R0801
pycodestyle .
flake8 *.py
bandit -r --skip B322 .
