FROM golang:1.17.1-buster

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/golang.tester.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/golang.tester.run.sh" ]