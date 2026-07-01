# Step 1: Build stage using Java 17
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

RUN apt-get update && apt-get install -y nodejs npm

COPY . .

RUN mvn clean package -DskipTests

# Step 2: Runtime stage using Java 17
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]