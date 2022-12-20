ARG MAVEN_VERSION=3.6.0-jdk-11-slim
ARG OPENJDK_VERSION=11-jre-slim


## Stage build
FROM maven:${MAVEN_VERSION} AS todo_build
COPY ./todo-api/src /home/app/src
COPY ./todo-api/pom.xml /home/app
COPY pom.xml /home/
RUN mvn -f /home/app/pom.xml clean package
## Stage run
FROM openjdk:${OPENJDK_VERSION} AS todo_api
COPY --from=todo_build /home/app/target/todo-api-0.0.1-SNAPSHOT.jar /usr/local/lib/todo-api.jar
EXPOSE 5000

CMD = ["java", "-jar", "/usr/local/lib/todo-api.jar"]

