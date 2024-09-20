FROM openjdk:17-jdk-alpine
COPY build/libs/app-0.0.1-SNAPSHOT.jar spring-boot-docker.jar
EXPOSE 9987
CMD ["java", "-jar", "spring-boot-docker.jar"]
