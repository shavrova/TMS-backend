FROM openjdk:11-jdk-slim
COPY target/discovery-service-1.0-SNAPSHOT.jar DiscoveryService.jar
ENTRYPOINT ["java","-jar","DiscoveryService.jar"]
EXPOSE 7002
