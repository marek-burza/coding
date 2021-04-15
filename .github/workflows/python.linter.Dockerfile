FROM python:3.9

COPY . /code
WORKDIR /code
RUN .github/workflows/python.linter.sh
