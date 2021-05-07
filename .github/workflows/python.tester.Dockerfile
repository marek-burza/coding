FROM python:3.9

COPY . /code
WORKDIR /code
RUN .github/workflows/python.tester.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/python.tester.run.sh" ]