FROM python:3.12

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/python.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/python.linter.run.sh" ]
