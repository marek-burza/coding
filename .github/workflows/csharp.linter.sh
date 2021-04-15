#!/usr/bin/env sh

set -e

cd algorithm-design
dotnet restore
PATH=$PATH:/root/.dotnet/tools
dotnet tool install -g JetBrains.ReSharper.GlobalTools
jb inspectcode -o=./report.txt -f=Text algorithm-design.csproj | grep -v ^Analyzing
cd -
cat algorithm-design/report.txt
[ -z $(cat algorithm-design/report.txt | sed 1d | tr -d "[:space:]") ]

