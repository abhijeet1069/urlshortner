FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY target/*.jar app.jar

RUN mkdir -p /data

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]