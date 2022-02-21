FROM mcr.microsoft.com/dotnet/sdk:6.0

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/csharp.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/csharp.linter.run.sh"]
