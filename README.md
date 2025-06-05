# TaskFlow

TaskFlow is a Spring Boot-based task management system that provides a robust backend API for managing tasks and workflows.

## 🚀 Features

- RESTful API endpoints for task management
- User authentication and authorization using Spring Security and JWT
- MySQL database integration with JPA
- Swagger/OpenAPI documentation
- Secure endpoints with role-based access control

## 🛠️ Technologies

- Java 17
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- JWT (JSON Web Tokens)
- Maven
- Swagger/OpenAPI 3.0

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

## 🔧 Setup and Installation

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd taskflow-back
   ```

2. Configure MySQL database:
   - Create a new MySQL database
   - Update `src/main/resources/application.properties` with your database credentials

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📚 API Documentation

Once the application is running, you can access the Swagger UI documentation at:
```
http://localhost:8080/swagger-ui.html
```

## 🔐 Security

The application uses JWT (JSON Web Tokens) for authentication. Protected endpoints require a valid JWT token in the Authorization header:
```
Authorization: Bearer [token]
```

## 🛠️ Development

To run the application in development mode with hot reload:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

