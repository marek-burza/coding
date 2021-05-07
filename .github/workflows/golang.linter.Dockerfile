FROM golang:1.14.2-buster

COPY . /code
WORKDIR /code
RUN .github/workflows/golang.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/golang.linter.run.sh" ]