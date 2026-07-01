# Step 1: Build stage
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /workspace

# Install Node.js and npm for Vaadin frontend compilation
RUN apt-get update && apt-get install -y nodejs npm

# Copy everything from your repository into the container
COPY . .

# Find the pom.xml dynamically, move to its folder, and compile
RUN POM_PATH=$(find . -name "pom.xml" | head -n 1) && \
    cd $(dirname "$POM_PATH") && \
    mvn clean package -DskipTests && \
    mkdir -p /workspace/target-out && \
    cp target/*.jar /workspace/target-out/app.jar || cp */target/*.jar /workspace/target-out/app.jar

# Step 2: Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the compiled jar file over
COPY --from=build /workspace/target-out/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]