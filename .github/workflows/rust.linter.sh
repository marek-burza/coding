#!/usr/bin/env sh

set -e

rustup component add clippy rustfmt

rm -rf Cargo.lock target || true
cargo clippy
cargo fmt --all -- --check
find . -name mod.rs -exec rm -f {} \;
