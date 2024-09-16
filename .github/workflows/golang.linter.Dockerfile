FROM golang:1.23-bookworm

RUN useradd -m user
USER user
COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/golang.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/golang.linter.run.sh" ]