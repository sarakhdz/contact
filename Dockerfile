FROM maven:3.6.0-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER AmirHossein Najmi
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar
#COPY  /target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]