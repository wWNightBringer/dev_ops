FROM openjdk:13-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} dev_ops.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/dev_ops.jar" ]
