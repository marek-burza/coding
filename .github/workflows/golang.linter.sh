#!/usr/bin/env sh

set -e

go install github.com/golangci/golangci-lint/v2/cmd/golangci-lint@latest

cd algorithms
gofmt -d .
golangci-lint run --config ../.github/linters/.golangci.yml
if [ -n "$(go fix ./...)" ]; then
	go fix ./...
	exit 1
fi
go vet ./...
