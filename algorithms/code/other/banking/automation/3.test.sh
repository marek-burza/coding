#!/bin/sh

set -e

SELF=$(dirname "$0")
BASE=$(realpath "$SELF/..")

rm -f "$BASE/banking.db"

docker run --rm -it -v "$BASE:/tests" -w /tests --entrypoint /bin/sh banking -c 'pip install -e .[tests] && coverage run -m unittest discover -s tests -p "*.py" && coverage report --show-missing --omit="tests/*"'
