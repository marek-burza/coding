FROM python:3.9

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/python.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/python.linter.run.sh" ]
