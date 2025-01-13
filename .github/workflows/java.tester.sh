#!/usr/bin/env sh

set -e

cd algorithms
mvn verify

column -t -s, target/site/jacoco/jacoco.csv
