FROM openjdk:11-jre-slim

EXPOSE 8081

RUN mkdir /app

COPY target/*.jar /app/spring-boot-application.jar

CMD ["java","-jar","-Djdk.tls.client.protocols=TLSv1.2", "/app/spring-boot-application.jar"]
