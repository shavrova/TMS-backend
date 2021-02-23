FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/gateway-service-1.0-SNAPSHOT.jar GatewayService.jar
ENTRYPOINT ["java","-jar","GatewayService.jar"]
EXPOSE 7003
