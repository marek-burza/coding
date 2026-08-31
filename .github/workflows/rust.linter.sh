#!/usr/bin/env sh

set -e

rustup component add clippy rustfmt
cargo install cargo-machete --locked

rm -rf Cargo.lock target || true
cargo clippy
cargo fmt --all -- --check
cargo machete
find . -name mod.rs -exec rm -f {} \;
