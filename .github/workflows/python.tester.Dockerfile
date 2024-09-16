FROM python:3.12

RUN useradd -m user
USER user
COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/python.tester.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/python.tester.run.sh" ]
