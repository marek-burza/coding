FROM golang:1.13.6-buster

COPY . /code
WORKDIR /code
RUN .github/workflows/golang.tester.sh
