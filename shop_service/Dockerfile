FROM openjdk:13-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} shop_service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/shop_service.jar" ]
