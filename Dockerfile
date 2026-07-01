# Step 1: Build stage with Maven and Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy the entire repository into the container
COPY . .

# Move into the folder where your pom.xml actually lives and build
WORKDIR /workspace/app
RUN mvn clean package -DskipTests

# Step 2: Run stage with JRE 21
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the compiled jar from the app's target directory
COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]