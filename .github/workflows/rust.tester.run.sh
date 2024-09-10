#!/usr/bin/env sh

set -e

export CARGO_INCREMENTAL=0
export RUSTFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off -Cinstrument-coverage'
export RUSTDOCFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off'
rm -rf Cargo.lock target
LLVM_PROFILE_FILE="./target/coverage/training.profraw" cargo test
find . -name mod.rs -exec rm {} \;
grcov ./target/coverage --binary-path ./target/debug/ -s . -o . --ignore-not-existing --keep-only 'algorithms/*' --output-types lcov,files
sort < files
mv lcov lcov.info
