# Library Management REST API

## Project Description

This project is a simple Library Management REST API developed using Spring Boot and PostgreSQL.

### Features

* Layered Architecture (Controller → Service → Repository)
* CRUD Operations
* DTO Pattern
* Input Validation
* Global Exception Handling
* Pagination & Sorting
* Swagger/OpenAPI Documentation
* Unit Testing (JUnit 5 & Mockito)


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

Example `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/library_db
    username: your_username
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

springdoc:
  swagger-ui:
    path: /swagger-ui.html
```


## How to Run

1. Clone the repository.
2. Create a PostgreSQL database named `library_db`.
3. Configure the `application.yml` file.
4. Run the Spring Boot application.
5. Open Swagger UI in your browser.



## Swagger/OpenAPI

After starting the application, Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html


## Testing

The service layer is tested using JUnit 5 and Mockito.
