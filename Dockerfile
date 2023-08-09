FROM eclipse-temurin:17-jdk-alpine

COPY ./build/libs/AuthenticationService.jar AuthenticationService.jar

ENTRYPOINT ["java", "-jar","AuthenticationService.jar"]
