#!/usr/bin/env sh

set -e

$(which golint 2> /dev/null || echo /home/runner/go/bin/golint) -set_exit_status ./...
goimports -d .
