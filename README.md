# prices-app

Application that exposes an API REST with information related to products and its prices.

### Note:

In the initial implementation, it was decided that the database would be responsible for filtering the obtained results
and directly returning the highest priority price. Later, that logic was moved to the application layer, as it was
considered business logic that should not reside in the infrastructure layer. The initial idea was to avoid obtaining a
large number of results and having to iterate over that list in memory, with the associated risks. Additionally, the
database engine would likely perform that task much faster and more efficiently.

## Getting Started (Java17 as minimum required)

Run project:
> ./gradlew bootRun

Run tests:
> ./gradlew test
>

## Example of application usage

> http://localhost:8080/products/35455?brandId=1&selectedDate=2020-06-14T10%3A00%3A00

```json 
{
  "price": 35.5,
  "product_id": 35455,
  "brand_id": 1,
  "rate_id": 1,
  "start_date": "2020-06-14T00:00:00",
  "end_date": "2020-12-31T23:59:59"
}
```
If no results are found:

```json
{
  "status": "NOT_FOUND",
  "timestamp": "2024-09-27T11:03:56.7664091",
  "message": "Price not found for product with id: 354555 and brand with id: 1 on selected date: 2020-06-14T10:00"
}
```
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
