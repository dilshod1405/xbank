
# Xbank - Spring Boot Banking Application

Xbank is a simple banking system built with Spring Boot. The project includes user registration, authentication via JWT, user roles, and integration with a PostgreSQL database.

---

## Technologies Used

- Java 21
- Spring Boot 3.5.3
- Spring Security (JWT)
- Spring Data JPA (PostgreSQL)
- Maven
- Lombok
- dotenv-java (for reading env files)
- Swagger/OpenAPI (springdoc)

---

## How to Run the Project

### Requirements

- Java 21 SDK installed
- PostgreSQL database
- Maven

### Steps

1. Start your PostgreSQL server and create a database named:

   ```
   xbank_db
   ```

2. Create a `.env` file in your project root directory and add:

   ```env
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/xbank_db
   SPRING_DATASOURCE_USERNAME=postgres
   SPRING_DATASOURCE_PASSWORD=yourpassword
   XBANK_JWT_SECRET=secretKey123
   XBANK_JWT_EXPIRATION_MS=86400000
   SERVER_PORT=8080
   ```

3. Build and run the project:

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. The application will start and be accessible at:

   ```
   http://localhost:8080
   ```

---

## API Documentation

- Swagger UI is available at:

  ```
  http://localhost:8080/swagger-ui.html
  ```

- ReDoc documentation (if installed) is available at:

  ```
  http://localhost:8080/redoc
  ```

---

## Usage

- Registration and login endpoints are under `/api/auth/**`.
- JWT tokens must be obtained and sent in the Authorization header for protected endpoints.
- Admin endpoints under `/api/admin/**` require the user to have the `ADMIN` role.

---

## Running with Docker

A Dockerfile is included. To build and run the project in a Docker container:

```bash
docker build -t xbank .
docker run -p 8080:8080 --env-file .env xbank
```

---

## Future Improvements

- User profile and account management
- Transactions and money transfer modules
- Reports and admin dashboard

---

## Author

Dilshodjon Normurodov

---
