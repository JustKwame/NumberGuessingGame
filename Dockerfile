# Step 1: Build stage with Maven and Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy the entire repository into the container
COPY . .

# Move into the folder where your pom.xml actually lives and build
WORKDIR /workspace/app

# Build with optimizations for memory-constrained environments
RUN mvn clean package -DskipTests \
    -Dmaven.compiler.fork=false \
    -Dvaadin.productionMode=true

# Step 2: Run stage with JRE 21
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the specific compiled jar (not the .original file)
COPY --from=build /workspace/app/target/app-*.jar app.jar

# Set environment variables for better JVM behavior on Render
ENV JAVA_OPTS="-Xmx512m -Xms256m" PORT=8080

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar app.jar"]