#!/usr/bin/env sh

set -e

go get -u golang.org/x/lint/golint
$GOPATH/bin/golint ./...
