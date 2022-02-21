#!/usr/bin/env sh

set -e

pycodestyle .
flake8
find . -name '*.py' | xargs pylint --disable=C0103,C0111,R0801,R0201,R0903
bandit -r .
mypy algorithms || true
