#!/usr/bin/env sh

set -e

export CARGO_INCREMENTAL=0
export RUSTFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off'
export RUSTDOCFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off'
cd algorithms
rm -rf Cargo.lock
cargo test  # -vv
find . -name mod.rs -exec rm {} \;
