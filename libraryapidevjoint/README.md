# Library Management REST API

## Project Description

This project is a simple Library Management REST API developed using Spring Boot and PostgreSQL.

### Features

* Layered Architecture (Controller → Service → Repository)
* CRUD Operations
* DTO Pattern
* MapStruct Mapping
* Input Validation
* Global Exception Handling
* Pagination & Sorting
* Swagger/OpenAPI Documentation
* Unit Testing (JUnit 5 & Mockito)
* Environment Variables for Database Configuration


## Technologies

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok
* Spring Validation
* Swagger/OpenAPI

## Database Configuration

Create a PostgreSQL database named:

```text
library_db
```

Configure the following environment variables before running the application:

```text
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/library_db
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
```

Example `application.yml`:

```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```


### Authentication
Implemented JWT-based authentication using Spring Security.

Features:
- User registration
- User login
- Password hashing with BCrypt
- JWT token generation
- CustomUserDetails and CustomUserDetailsService
- Stateless authentication
- Role entity instead of enum for future extensibility
- DTO mapping using MapStruct


## How to Run

1. Clone the repository.
2. Create a PostgreSQL database named `library_db`.
3. Configure the required environment variables.
4. Run the Spring Boot application.
5. Open Swagger UI in your browser.


## Swagger/OpenAPI

After starting the application, Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html


## Testing

The service layer is tested using JUnit 5 and Mockito.
