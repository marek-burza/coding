#!/usr/bin/env sh

set -e

cd algorithms
go test -race -coverprofile=coverage.txt -covermode=atomic ./...
