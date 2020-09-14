FROM openjdk:13-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} store_shop.jar
ENTRYPOINT ["java", "-jar", "/store_shop.jar" ]
EXPOSE 8082