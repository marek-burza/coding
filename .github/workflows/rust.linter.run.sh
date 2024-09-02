#!/usr/bin/env sh

set -e

rm -rf Cargo.lock target
cargo clippy
find . -name mod.rs -exec rm {} \;
