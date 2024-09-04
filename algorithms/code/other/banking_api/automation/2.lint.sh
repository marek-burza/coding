#!/bin/sh

set -e

BASE=$(realpath $(dirname "$0")/..)

docker run --rm -it -v $BASE:/tests -w /tests --entrypoint /bin/sh banking -c 'pip install -e .[tests] && /bin/sh automation/2.linters.sh'
