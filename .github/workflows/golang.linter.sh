#!/usr/bin/env sh

set -e

go install github.com/golangci/golangci-lint/cmd/golangci-lint@latest

cd algorithms
gofmt -d .
golangci-lint run
