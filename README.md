# Blogging Platform API

A production-grade, secure, RESTful blogging API built with Spring Boot 3, JWT authentication, PostgreSQL, and modular layered architecture.

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- PostgreSQL + JPA
- Hibernate + ModelMapper
- Maven

---

## ✅ Core Features

- User, Role, Category, Post entities
- JWT-based Authentication
- Role-based Authorization
- CRUD operations for Posts, Categories, Users
- Pagination & Sorting
- Global Exception Handling

---

## 🧱 Architecture

Layered approach:
- `Controller`: Handles HTTP
- `Service`: Business logic
- `Repository`: DB layer
- `DTO`: Safe data exposure
- `Entity`: Persistence model

---

## 🔐 Security

- JWT-based login/auth
- BCrypt password hashing
- Spring Security
- Role-based API protection

---

## 🧪 API Endpoints

### Users:
- Create, Read, Update, Delete
- GET all users

### Posts:
- Create, Read, Update, Delete
- Get posts by user or category
- Pagination + Sorting

### Categories:
- Create, Read, Update, Delete

---

## 🔧 Getting Started

1. Clone repo
2. Configure PostgreSQL in `application.properties`
3. Run `mvn spring-boot:run`
4. Use Postman collection to test

Default admin login:
`admin@gmail.com / admin`

---

## 🛡️ Status

✅ In active development  
🧪 Test coverage: [in progress]  
🚀 Deployment: [planned on Railway/Render]

---

## 📄 License

MIT
