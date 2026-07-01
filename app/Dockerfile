# Step 1: Build environment using Java 17 and Maven
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /workspace

# Install Node.js and npm (Required by Vaadin to compile frontend components)
RUN apt-get update && apt-get install -y nodejs npm

# Copy everything into the workspace container
COPY . .

# Move into your nested app folder to compile the actual code
WORKDIR /workspace/app
RUN mvn clean package -DskipTests

# Step 2: Create a clean, secure runtime container to execute the jar
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the compiled jar from the build stage over to the runtime stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Hugging Face Spaces automatically listens on port 7860
EXPOSE 7860
ENV PORT=7860

# Run the Vaadin application, overriding the default port to match Hugging Face
ENTRYPOINT ["java", "-Dserver.port=7860", "-jar", "app.jar"]