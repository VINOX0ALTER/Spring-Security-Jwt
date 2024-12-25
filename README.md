# Spring Security JWT Project

## Description
This project is a Spring Boot application implementing JSON Web Token (JWT) authentication and authorization. It provides functionalities for managing user accounts and roles. The application supports different user roles: `ADMIN`, `TEACHER`, and `STUDENT`, each with specific access rights.

## Features
- User authentication and role-based authorization using JWT.
- CRUD operations for users and roles.

## Technologies Used
- **Java**: Programming language.
- **Spring Boot**: Framework for application setup and configuration.
    - Spring Security: For authentication and authorization.
    - Spring Data JPA: For database interactions.
- **H2 Database**: In-memory database for testing and development.
- **Maven**: Dependency management and build tool.
- **JWT**: For secure token-based authentication.

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- IDE (e.g., IntelliJ IDEA, Eclipse)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/VINOX0ALTER/Spring-Security-Jwt
   ```
2. Navigate to the project directory:
   ```bash
   cd Spring-security-jwt
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Running the Application
1. Run the application:
   ```bash
   mvn spring-boot:run
   ```
2. Access the application at:
   ```
   http://localhost:8080
   ```

## API Endpoints
### Authentication
- **POST** `/api/auth/login`: Authenticate a user and receive a JWT token.

### Users
- **GET** `/api/users`: Retrieve all users.
- **POST** `/api/users`: Create a new user.

### Roles
- **GET** `/api/roles`: Retrieve all roles.
- **POST** `/api/roles`: Create a new role.

## Configuration
### Application Properties
The default database and JWT configuration are set in `src/main/resources/application.properties`. Modify these properties as needed.

Example:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

jwt.secret=your_jwt_secret
jwt.expirationMs=3600000
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contribution
Contributions are welcome! Please fork the repository and submit a pull request.

## Contact
For further information or inquiries, please contact:
- Email: Boutahar.youness@hotmail.com
