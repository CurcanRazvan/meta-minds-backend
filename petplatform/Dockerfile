# ---------- Stage 1: Build ----------
    FROM gradle:8.5-jdk17-alpine AS builder

    WORKDIR /app
    # Copy the project files into the container
    COPY . .
    
    RUN chmod +x gradlew
    
    # Build the project using the wrapper
    RUN ./gradlew clean build --no-daemon
    
    # ---------- Stage 2: Run ----------
    FROM eclipse-temurin:17-alpine
    
    WORKDIR /app
    
    # Copy the built JAR file from the builder stage
    COPY --from=builder ./app/build/libs/*.jar app.jar
    
    EXPOSE 8080
    
    # Run the application
    CMD ["java", "-jar", "app.jar"]