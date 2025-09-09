# Spring Boot CRUD (H2) — Java 21

A minimal, production-grade Spring Boot CRUD REST API using **Java 21**, **Spring Boot 4.0.0-M2** (milestone), **Maven**, and **H2** in‑memory database.

> ✅ If you prefer a stable release, change the parent version in `pom.xml` to `3.4.9` and remove the `spring-milestones` repository. Everything else is the same.

## Quick Start

Prereqs:
- JDK **21**
- Maven **3.9+**

Run:

```bash
mvn spring-boot:run
# or build a jar
mvn -q -DskipTests package
java -jar target/spring-boot-crud-h2-0.0.1-SNAPSHOT.jar
```

API:
- `GET    /api/employees`
- `GET    /api/employees/{id}`
- `POST   /api/employees` (JSON body: `{ "name": "...", "email": "...", "role": "..." }`)
- `PUT    /api/employees/{id}`
- `DELETE /api/employees/{id}`

H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:demo`, user: `sa`, password empty).

## Project Structure

```
src/main/java/com/example/employees
├─ SpringBootCrudH2Application.java
├─ model/Employee.java
├─ repository/EmployeeRepository.java
├─ service/EmployeeService.java
└─ web
   ├─ dto/EmployeeRequest.java
   └─ EmployeeController.java
```

## Notes

- Uses Lombok **1.18.40** to avoid `IllegalAccessError` issues on newer JDKs.
- `maven-compiler-plugin` is set to **3.14.0** with `release=21`.
- Database is in-memory; change `application.properties` if you want persistent storage.
# spring-boot-curd-operations
# spring-boot-crud-h2
