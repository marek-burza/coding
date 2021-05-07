#!/usr/bin/env sh

set -e

go test -race -coverprofile=coverage.txt -covermode=atomic ./...
