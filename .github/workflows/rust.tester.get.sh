#!/usr/bin/env sh

set -e

rustup component add llvm-tools-preview
curl -L https://github.com/mozilla/grcov/releases/latest/download/grcov-x86_64-unknown-linux-gnu.tar.bz2 | tar jxf -
