FROM python:3.12

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/python.linter.get.sh
RUN useradd -m user
USER user
ENTRYPOINT [ "/bin/sh", ".github/workflows/python.linter.run.sh" ]
