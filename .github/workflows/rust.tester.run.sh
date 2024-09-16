#!/usr/bin/env sh

set -e

export CARGO_INCREMENTAL=0
export RUSTFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off -Cinstrument-coverage'
export RUSTDOCFLAGS='-Ccodegen-units=1 -Cinline-threshold=0 -Clink-dead-code -Coverflow-checks=off'
rm -rf Cargo.lock target || true
LLVM_PROFILE_FILE="./target/coverage/training.profraw" cargo test
find . -name mod.rs -exec rm -f {} \;
grcov ./target/coverage --binary-path ./target/debug/ -s . -o . --ignore-not-existing --keep-only 'algorithms/*' --output-types lcov
mv lcov lcov.info
grep -E "^SF|^DA:.+,0$" lcov.info | sed s/\,0$//g | awk -F: '{print $2}'
