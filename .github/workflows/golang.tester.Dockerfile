FROM golang:1.14.2-buster

COPY . /code
WORKDIR /code
RUN .github/workflows/golang.tester.sh
