# Use OpenJDK base image
FROM openjdk:17-jdk-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the local code to the container
COPY . .

# Build the application using Maven (assuming Maven is being used)
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot application
FROM openjdk:17-jdk-slim

# Copy the packaged .jar file from the build stage
COPY --from=build /app/target/crud-0.0.1-SNAPSHOT.jar /app/crud-0.0.1-SNAPSHOT.jar

# Expose the port that the app will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/crud-0.0.1-SNAPSHOT.jar"]
