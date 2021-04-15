#!/usr/bin/env sh

set -e

cd algorithm-design
dotnet restore
dotnet test /p:IncludeTestAssembly=true /p:CollectCoverage=true /p:CoverletOutputFormat=opencover /p:ExcludeByFile="**/*.Tests.cs"
cd -
