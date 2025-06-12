### How I Built a Production-Ready Blogging Platform API with Spring Boot 3 & JWT

## 🏗️ Overview
This project is a secure, modular, production-ready blogging API built with Spring Boot 3.x, Java 17, and PostgreSQL. It’s designed using a layered architecture, JWT-based authentication, and clean separation of concerns for scalability and clarity.

## ⚙️ Tech Stack
Java 17

Spring Boot 3.x

Spring Security + JWT

PostgreSQL + JPA

ModelMapper + DTO pattern

BCrypt encryption

## 🧠 Key Features
✅ Secure login with JWT token

✅ Role-based access (ADMIN/USER)

✅ RESTful APIs for posts, users, and categories

✅ Pagination, sorting, filtering

✅ Layered architecture (Controller → Service → Repository)

✅ Global exception handling

✅ Clean DTOs for safe client interaction

## 🧱 Architecture Breakdown
Controllers: REST endpoints

Services: Business logic

Repositories: Database access via JPA

DTOs: Data transfer objects (PostDto, UserDto, etc.)

Security Layer: JwtAuthFilter, JwtTokenHelper, CustomUserDetailsService

## 🔐 Authentication Flow
1. User logs in via /api/auth/login

2. Backend validates and returns JWT

3. Token is sent in all secured requests as a Bearer token

4. JwtAuthFilter intercepts and validates token before processing

## 📌 Authorization Header Example
      Authorization: Bearer <your-jwt-token>

## 📁 Data Model
User (id, name, email, password, roles)

Post (id, title, content, imageName, user, category)

Category (id, title, description)

Role (id, roleName)

## 📄 Sample Endpoints

| Operation              | Endpoint                                               |
| ---------------------- | ------------------------------------------------------ |
| Register User          | `POST /api/users/`                                     |
| Login & Generate Token | `POST /api/auth/login`                                 |
| Create Post            | `POST /api/users/{userId}/category/{categoryId}/posts` |
| Get All Posts          | `GET /api/posts?page=1&size=5&sortBy=title`            |
| Get Posts by Category  | `GET /api/category/{id}/posts`                         |

## 🧪 Test Collection
Use our Postman collection here:
[📥 blog-api.postman_collection.json](https://github.com/JYBhavsar/springboot-blogging-platform-api/blob/development/blog-api.postman_collection.json)

## 🎯 Deployment Ready
The project can be deployed with Railway, Render, or Docker with proper production configuration.

## 📎 GitHub Repo
[View Code & Docs](https://github.com/JYBhavsar/springboot-blogging-platform-api)

## 🛠️ Want Something Similar?
This API can be adapted into your startup backend, LMS engine, or content platform with minimal changes. Want us to build a PoC? We deliver version 1 in 3–5 days — free.
