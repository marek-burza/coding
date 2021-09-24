FROM eclipse-temurin:11.0.12_7-jdk-focal

ENV DEBIAN_FRONTEND noninteractive
RUN apt-get -yq update && \
    apt-get -yq install maven bsdmainutils


COPY . /code
WORKDIR /code
RUN .github/workflows/java.tester.get.sh
ENTRYPOINT [ "/bin/sh", ".github/workflows/java.tester.run.sh" ]