#!/usr/bin/env sh

set -e

rustup component add llvm-tools-preview
cargo install grcov
