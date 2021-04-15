FROM mcr.microsoft.com/dotnet/sdk:5.0

COPY . /code
WORKDIR /code
RUN .github/workflows/csharp.tester.sh
