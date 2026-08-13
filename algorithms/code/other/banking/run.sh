#!/bin/sh

set -e

SELF=$(dirname "$0")
BASE=$(realpath "$SELF/..")
/bin/sh "$BASE/automation/1.build.sh"
mkdir -p "$BASE/db"
docker run --rm -it --name banking -v "$BASE/db:/db" -w /db -p 80:80 banking
