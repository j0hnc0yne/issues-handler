FROM amazoncorretto:21-alpine

# Metadata as a label
LABEL maintainer="john.coyne@gmail.com" version="1.0" description="Demo app for Integration Testing"

# Copy the application JAR into the container
COPY build/libs/*-boot.jar /app/app.jar

# Command to run the application
CMD ["java", "-jar", "/app/app.jar"]
