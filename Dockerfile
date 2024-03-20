FROM openjdk:21
ENV PORT 8081
EXPOSE 8081
ADD /target/RidesService-0.0.1-SNAPSHOT.jar rides-service.jar
ENTRYPOINT ["java", "-jar", "rides-service.jar"]