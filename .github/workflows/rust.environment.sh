#!/usr/bin/env sh

set -e

cd algorithms
rustup component add clippy rustfmt llvm-tools-preview
cargo install grcov
