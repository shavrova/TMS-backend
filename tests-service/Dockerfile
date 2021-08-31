FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/tests-service-1.0-SNAPSHOT.jar TestsService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/TestsService.jar"]
EXPOSE 7005
