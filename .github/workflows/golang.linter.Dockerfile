FROM golang:1.22.5-bookworm

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/golang.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/golang.linter.run.sh" ]