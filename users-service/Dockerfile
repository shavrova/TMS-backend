FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/users-service-1.0-SNAPSHOT.jar UsersService.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/UsersService.jar"]
EXPOSE 7004
