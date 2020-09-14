FROM openjdk:13-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} shop_service.jar
ENTRYPOINT ["java", "-jar", "/shop_service.jar" ]
EXPOSE 8081