FROM eclipse-temurin:11.0.12_7-jdk-focal

ENV DEBIAN_FRONTEND noninteractive
RUN apt-get -yq update && \
    apt-get -yq install maven

COPY . /code
WORKDIR /code
RUN .github/workflows/java.linter.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/java.linter.run.sh" ]