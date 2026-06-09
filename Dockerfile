# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/librarymanagement-0.0.1-SNAPSHOT.jar librarymanagement.jar
EXPOSE 9080
ENTRYPOINT ["java","-jar","librarymanagement.jar"]
