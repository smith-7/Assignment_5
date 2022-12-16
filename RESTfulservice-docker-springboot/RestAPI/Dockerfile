FROM openjdk:8-jdk-alpine
RUN mkdir -p /etc/neerav/
COPY customers.json /etc/neerav/customers.json
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]