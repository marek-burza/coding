#!/usr/bin/env sh

set -e

go get -u golang.org/x/lint/golint
$(which golint 2> /dev/null || echo /home/runner/go/bin/golint) -set_exit_status ./...
