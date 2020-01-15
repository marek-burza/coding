FROM python:3.8

COPY . /code
WORKDIR /code
RUN .github/workflows/python.linter.sh
