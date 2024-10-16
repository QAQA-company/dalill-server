FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY build/libs/eureka-service-0.0.1-SNAPSHOT.jar eureka-service.jar
ENTRYPOINT ["java","-jar","/eureka-service.jar"]
