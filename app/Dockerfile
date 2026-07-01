# Step 1: Use Maven + Java 17 as the build environment
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Install Node.js (Required by Vaadin to compile frontend themes and styles)
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodesource-repo && \
    apt-get install -y nodejs

# Copy all your project files into the container
COPY . .

# Compile and package the Java jar file
RUN mvn clean package -DskipTests

# Step 2: Use a clean, lightweight Java runtime to run the app
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]