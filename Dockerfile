FROM maven:3.2-jdk-8-onbuild
RUN mkdir -p /app
WORKDIR /app
ONBUILD ADD ./app
ONBUILD RUN mvn install
