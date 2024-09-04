#!/bin/sh

set -e

SELF=$(dirname "$0")
BASE=$(realpath "$SELF/..")

docker run --rm -it -v $BASE:/tests -w /tests --entrypoint /bin/sh banking -c 'pip install -e .[tests] && /bin/sh automation/2.linters.sh'
