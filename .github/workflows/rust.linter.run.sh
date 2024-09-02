#!/usr/bin/env sh

set -e

cd algorithms
rm -rf Cargo.lock
cargo clippy
find . -name mod.rs -exec rm {} \;
