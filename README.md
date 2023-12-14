# Monitoring Application

Simple monitor application for user water & gas usage

## Table of Contents
- [Prerequisites](#prerequisites)
- [Build and Run](#build-and-run)
- [API Documentation](#api-documentation)
- [Testing](#testing)

## Prerequisites
Before you begin, ensure you have met the following requirements:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Build and Run
Follow these steps to build and run the application:

1. Build the project:
    ```bash
    mvn clean install
    ```

2. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## API Documentation
The API documentation is generated using Swagger. After starting the application, you can access the Swagger UI at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Testing
To run the tests, use the following command:
```bash
mvn test