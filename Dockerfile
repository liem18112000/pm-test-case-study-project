FROM openjdk:8-alpine
VOLUME /tmp
ADD target/spring-petclinic-2.4.5.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
