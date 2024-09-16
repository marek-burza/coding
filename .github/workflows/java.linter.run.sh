#!/usr/bin/env sh

set -e

cd algorithms
mvn compile checkstyle:check spotbugs:check # pmd:check
