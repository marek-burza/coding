#!/usr/bin/env sh

set -e

pycodestyle .
flake8
pylint --disable=C0103,C0111,R0801,R0201,R0903 $(find . -name "*.py")
bandit -r .
