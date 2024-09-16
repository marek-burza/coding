FROM rust:1.80-bookworm

RUN useradd -m user
USER user
COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/rust.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/rust.linter.run.sh" ]
