FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY build/libs/schedule-0.0.1-SNAPSHOT.jar schedule.jar
ENTRYPOINT ["java","-jar","/schedule.jar"]
