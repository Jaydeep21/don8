FROM openjdk:11
EXPOSE 8080
ADD target/don8.jar don8.jar
ENTRYPOINT ["java", "-jar", "don8.jar"]