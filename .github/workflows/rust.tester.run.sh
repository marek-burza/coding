#!/usr/bin/env sh

set -e

export CARGO_INCREMENTAL=0
export RUSTFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off'
export RUSTDOCFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off'
rm -rf Cargo.lock target
cargo test
cargo tarpaulin
find . -name mod.rs -exec rm {} \;
