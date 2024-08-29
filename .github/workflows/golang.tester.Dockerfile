FROM golang:1.23-bookworm

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/golang.tester.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/golang.tester.run.sh" ]