#!/usr/bin/env sh

set -e

bash -c "go get -u golang.org/x/lint/golint"
bash -c "golint ./..."
