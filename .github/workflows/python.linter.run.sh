#!/usr/bin/env sh

set -e

echo "--- pycodestyle ---"
pycodestyle --ignore=E203 .
echo "--- flake8 ---"
flake8 --ignore=E203
echo "--- pylint ---"
find . -name '*.py' | xargs pylint --disable=C0103,C0111,C0302,R0801,R0903
echo "--- bandit ---"
bandit -r .
echo "--- mypy ---"
mypy algorithms
