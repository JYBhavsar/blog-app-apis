### Getting Started
	 - Implementing Blog Application with user role base authentication

## About Project

# Technical stack using
	- Spring boot
	- Java17
	- PostgreSQL
	- Hibernate ORM


## Useful Guidance

	- You can use static username and password while doing practice only CRUD like, 
	Set this values in your application.propertie:

	spring.security.user.name=admin
	spring.security.user.password=123
	spring.security.user.roles=ADMIN

# JWT Token Guidance
	- while creating the token -
		1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
		2. Sign the JWT using the HS512 algorithm and secret key.
		3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1) 
		compaction of the JWT to a URL-safe string
		
	- During Generation of token secret-key must have minimum keyBytes 32 or more.

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

