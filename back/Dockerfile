# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .mvn/ ./

# Copy the src directory
COPY src ./src/

# Build the Spring Boot application (including tests)
RUN mvn clean package

# Stage 2: Create a lightweight runtime image
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default port Spring Boot uses
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]