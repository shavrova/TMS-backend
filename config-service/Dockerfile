FROM openjdk:11-jdk-slim
MAINTAINER Norika766 <norika1111@gmail.com>
VOLUME /tmp 
COPY src/main/resources/tmsEncryptionKey.jks tmsEncryptionKey.jks
COPY UnlimitedJCEPolicyJDK8/* /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/ 
COPY target/config-service-1.0-SNAPSHOT.jar ConfigServer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","ConfigServer.jar"]
EXPOSE 7001


