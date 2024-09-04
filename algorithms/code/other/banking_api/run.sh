#!/bin/sh

set -e

BASE=$(realpath $(dirname "$0"))
/bin/sh $BASE/automation/1.build.sh
mkdir -p $BASE/db
docker run --rm -it --name banking -v $BASE/db:/db -w /db -p 80:80 banking
