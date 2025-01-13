FROM rust:1.80-bookworm

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/rust.environment.sh
ENTRYPOINT [ "/bin/bash" ]
