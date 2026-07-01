# Step 1: Use Maven + Java 17 as the build environment
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Install Node.js and npm directly from standard repositories
RUN apt-get update && apt-get install -y nodejs npm

# Copy all your project files into the container
COPY . .

# Compile and package the Java jar file for production
RUN mvn clean package -DskipTests

# Step 2: Use a clean, lightweight Java runtime to run the app
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]