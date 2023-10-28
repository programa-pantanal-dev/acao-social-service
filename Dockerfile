FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 8081

COPY --from=build /target/acao-social-service-3.1.3.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]