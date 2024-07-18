#!/usr/bin/env sh

set -e

cd algorithms
dotnet restore
PATH=$PATH:/root/.dotnet/tools
jb inspectcode -o=./report.txt -f=Text algorithms.csproj | grep -v ^Analyzing
cd -
cat algorithms/report.txt
[ -z "$(sed 1d algorithms/report.txt | tr -d '[:space:]')" ]

