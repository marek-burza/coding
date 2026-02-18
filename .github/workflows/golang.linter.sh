#!/usr/bin/env sh

set -e

go install github.com/golangci/golangci-lint/v2/cmd/golangci-lint@v2.10.1

cd algorithms
gofmt -d .
golangci-lint run --config ../.github/linters/.golangci.yml
if [ -n "$(go fix -diff ./...)" ]; then
	go fix -diff ./...
	exit 1
fi
go vet ./...
