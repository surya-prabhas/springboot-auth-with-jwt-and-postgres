Spring Boot Authentication with JWT & PostgreSQL

A complete backend authentication system built using Spring Boot, Spring Security, JWT (JSON Web Tokens), and PostgreSQL.
This project demonstrates secure login, registration, role-based access control, password encryption, and JWT token validation.

🚀 Features

User registration & login

JWT-based authentication (stateless)

Role-based authorization (USER / ADMIN)

Password hashing using BCrypt

Secure Spring Security configuration

PostgreSQL database integration

Clean layered architecture:
Controller → Service → Repository → Entity

Uses DTOs for clean request/response handling

🛠️ Tech Stack
Category	Technology
Language	Java 17+
Framework	Spring Boot
Security	Spring Security, JWT
Database	PostgreSQL
Build Tool	Maven
ORM	Spring Data JPA
Validation	Jakarta / Hibernate Validation
📦 Project Structure (Simplified)
src/main/java/com/example
│
├── config          # Security config, JWT filters, utils
├── controller      # REST endpoints
├── dto             # Request/Response data models
├── entity          # JPA Entities
├── repository      # Database interfaces
├── service         # Business logic
└── util            # Helper classes (JWT Utils etc.)

⚙️ How to Run the Project
1. Prerequisites

Java 17 or later

Maven

PostgreSQL installed and running

2. Create the PostgreSQL database
CREATE DATABASE jwt_auth;

3. Configure application.properties

Create or update:

spring.datasource.url=jdbc:postgresql://localhost:5432/jwt_auth
spring.datasource.username=your_pg_username
spring.datasource.password=your_pg_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

security.jwt.secret=your_secret_key
security.jwt.expiration=3600000


⚠️ Do not commit your real DB password or JWT secret to GitHub.

4. Run the project
mvn spring-boot:run


Backend will start at:

http://localhost:8080

🔐 API Endpoints
Auth APIs
Method	Endpoint	Description
POST	/api/auth/register	Register new user
POST	/api/auth/login	Authenticate & return JWT token
Protected APIs

(Require JWT token in Authorization: Bearer <token>)

Method	Endpoint	Role	Description
GET	/api/user/me	USER/ADMIN	Get logged-in user details
GET	/api/admin/all-users	ADMIN	View all users
🧪 Example Login Response
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cC...",
  "username": "surya",
  "roles": ["USER"]
}

📘 How JWT Works in This Project

User logs in → server validates credentials

If valid → server generates JWT token

Token sent back to client

Client includes token in every request header

Server validates token via filter

Access is granted based on roles

🧭 Future Improvements

Refresh tokens

Account lock after multiple failed logins

Email verification

OAuth2 login (Google / GitHub)

Centralized exception handling

Unit & integration tests (JUnit + Mockito)

📝 Resume Description (Copy-Paste)

Implemented a secure authentication & authorization backend using Spring Boot, Spring Security, JWT, and PostgreSQL. Designed REST APIs for user login/registration, role-based access, and token validation. Used BCrypt for password encryption and built a clean layered architecture with DTOs and standardized responses.

🤝 Contributions

Feel free to fork the repo, improve features, or submit PRs.

⭐ If this project helps you, don’t forget to star the repo!
