#!/usr/bin/env sh

set -e

CARGO_INCREMENTAL=0
RUSTFLAGS='-Zprofile -Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off -Cpanic=abort -Zpanic_abort_tests'
RUSTDOCFLAGS='-Zprofile -Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off -Cpanic=abort -Zpanic_abort_tests'
cd algorithms
rm -rf Cargo.lock
cargo test  # -vv
find . -name mod.rs -exec rm {} \;
