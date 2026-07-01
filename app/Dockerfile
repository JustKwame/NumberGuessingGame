# Step 1: Use a Maven image that supports newer Java runtimes
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Install Node.js and npm directly for Vaadin frontend compilation
RUN apt-get update && apt-get install -y nodejs npm

# Copy all your project files into the container
COPY . .

# Compile and package the Java jar file for production
RUN mvn clean package -DskipTests

# Step 2: Use a matching Java runtime to execute the app
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]