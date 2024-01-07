# Use Eclipse Temurin 21 as the base image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/*.jar app.jar

# Expose port 9898
EXPOSE 9898

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
