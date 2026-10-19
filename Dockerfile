# Etap 1: budowanie paczki JAR
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# nadpisanie appsettings.properties wersją dockerową
COPY src/main/resources/appsettings.docker.properties src/main/resources/appsettings.properties
RUN mvn -B package

# Etap 2: uruchomienie
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/order-management-1.0-SNAPSHOT.jar app.jar
RUN mkdir -p /app/orders
ENTRYPOINT ["java", "-jar", "app.jar"]