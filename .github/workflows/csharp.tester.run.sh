#!/usr/bin/env sh

set -e

cd algorithms
dotnet restore
dotnet test /p:IncludeTestAssembly=true /p:CollectCoverage=true /p:CoverletOutputFormat=opencover /p:ExcludeByFile="**/*.Tests.cs"
cd -
