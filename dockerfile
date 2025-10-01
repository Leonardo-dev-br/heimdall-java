
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn -B -e -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar


EXPOSE 8080


ENTRYPOINT ["sh","-c","java -jar /app/app.jar --server.port=${PORT:-8080}"]
