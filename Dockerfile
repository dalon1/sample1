FROM openjdk:8u111-jdk-alpine
WORKDIR app
COPY target/sample1-1.0.jar .
EXPOSE 8099
ENTRYPOINT ["java", "-jar", "sample1-1.0.jar"]
