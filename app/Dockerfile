# Step 1: Build stage using standard Debian/Ubuntu image (No Alpine)
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /workspace

# Install Node.js and npm for Vaadin frontend compilation
RUN apt-get update && apt-get install -y nodejs npm

# Copy the entire project repo into the container
COPY . .

# Move into the app folder where pom.xml actually lives
WORKDIR /workspace/app
RUN mvn clean package -DskipTests

# Step 2: Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the compiled jar file over from the app's target directory
COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]