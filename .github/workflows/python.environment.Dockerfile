FROM python:3.12

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/python.environment.sh
RUN useradd -m user
USER user
ENTRYPOINT [ "/bin/bash" ]
