#!/usr/bin/env sh

set -e

go install golang.org/x/lint/golint@v0.0.0-20210508222113-6edffad5e616
go install golang.org/x/tools/cmd/goimports@v0.24.0
