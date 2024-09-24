FROM rust:1.80-bookworm

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/rust.linter.get.sh
RUN useradd -m user
USER user
ENTRYPOINT [ "/bin/sh", ".github/workflows/rust.linter.run.sh" ]
