FROM eclipse-temurin:11.0.12_7-jdk-focal

ENV DEBIAN_FRONTEND noninteractive
RUN apt-get -yq update && \
    apt-get -yq --no-install-recommends install maven=3.6.3-1 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY . /code
WORKDIR /code
RUN /bin/sh .github/workflows/java.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/java.linter.run.sh" ]