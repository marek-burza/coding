FROM python:3.7

COPY . /code
WORKDIR /code
RUN .github/workflows/python.tester.sh
