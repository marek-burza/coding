#!/usr/bin/env sh

set -e

echo "--- pycodestyle ---"
pycodestyle --ignore=E203,W503 .
echo "--- flake8 ---"
flake8 --ignore=E203,W503
echo "--- pylint ---"
find . -name '*.py' | xargs pylint --disable=C0103,C0111,C0302,R0801,R0903
echo "--- mypy ---"
mypy algorithms
echo "--- bandit ---"
bandit -r .
