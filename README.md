# prices-app

Application that exposes an API REST with information related to products and its prices.

## Getting Started
Run project (Java17 as minimum required):
>./gradlew bootRun
---
## H2 Database
Access URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:pricesDB
- user: root
- password: root
---
## Swagger
Access to REST API documentation via Swagger: http://localhost:8080/swagger-ui.html

---
## Technologies
- Gradle
- Java OpenJDK 17
- Spring Boot 3.3.3
- H2 Database 2.3.232
- Lombok 1.18.34
- MapStruct 1.6.2
- Springdoc Openapi-UI 2.6.0
