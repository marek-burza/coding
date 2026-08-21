#!/usr/bin/env sh

set -e

go install github.com/golangci/golangci-lint/v2/cmd/golangci-lint@latest
go install golang.org/x/vuln/cmd/govulncheck@latest

cd algorithms
gofmt -d .
golangci-lint run --config ../.github/linters/.golangci.yml
fixes=$(go fix -diff ./...) || true
if [ -n "$fixes" ]; then
	printf '%s\n' "$fixes"
	exit 1
fi
go vet ./...
govulncheck ./...
