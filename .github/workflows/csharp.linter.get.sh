#!/usr/bin/env sh

set -e

PATH=$PATH:/root/.dotnet/tools
dotnet tool install -g JetBrains.ReSharper.GlobalTools
