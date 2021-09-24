#!/usr/bin/env sh

set -e

cd algorithms
mvn verify

cat target/site/jacoco/jacoco.csv | column -t -s,
