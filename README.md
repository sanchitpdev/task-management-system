# Task Management System (Spring Boot)

A simple **Task / Request Management System** built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.  
This project is created to understand **real backend architecture**, database interaction, and request–response workflow.

---

## 🚀 Project Goal

The main goal of this project is to:
- Learn **Spring Boot backend structure**
- Understand **Controller → Service → Repository → Entity flow**
- Work with a **real relational database (PostgreSQL)**
- Build a **role-based task management system**
- Practice **Git & GitHub workflow**

---

## 🧩 Core Features

- User management with roles (**ADMIN**, **USER**)
- Task creation and assignment
- Task status tracking
- Entity relationships using JPA
- Clean layered architecture

---

## 🏗️ Project Architecture

---
```
task-management-system
│
├── common
│   └── enums
│       ├── Role.java
│       └── TaskStatus.java
│
├── user
│   ├── controller
│   ├── service
│   ├── repository
│   └── entity
│       └── User.java
│
├── task
│   ├── controller
│   ├── service
│   ├── repository
│   └── entity
│       └── Task.java
│
├── resources
│   └── application.properties
│
└── TaskManagementSystemApplication.java
```
---

## 🧠 Key Concepts Used

- Spring Boot
- REST APIs
- Spring Data JPA
- Hibernate ORM
- PostgreSQL
- Enums
- Entity Relationships (`@ManyToOne`)
- Layered Architecture
- Maven
- Git & GitHub

---

## 📦 Technologies Used

| Technology | Purpose |
|---------|--------|
| Java 17 | Programming language |
| Spring Boot | Backend framework |
| Spring Data JPA | ORM & database access |
| PostgreSQL | Relational database |
| Maven | Dependency management |
| IntelliJ IDEA | IDE |

---

## 🗂️ Database Design

### Users Table

| Column | Type |
|------|------|
| id | BIGINT |
| name | VARCHAR |
| email | VARCHAR |
| role | ENUM (ADMIN, USER) |

### Tasks Table

| Column | Type |
|------|------|
| id | BIGINT |
| title | VARCHAR |
| description | VARCHAR |
| task_status | ENUM |
| assigned_to | FOREIGN KEY (users.id) |

---

## ⚙️ Application Configuration

Example `application.properties`:



spring.datasource.url=jdbc:postgresql://localhost:5432/task_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

---

## ▶️ How to Run the Project

1. Clone the repository

git clone [https://github.com/sanchitpdev/task-management-system.git](https://github.com/sanchitpdev/task-management-system.git)

2. Open the project in IntelliJ IDEA

3. Configure PostgreSQL database

4. Run:

TaskManagementSystemApplication.java

5. Application will start at:

[http://localhost:8080](http://localhost:8080)

---

## 🛣️ Future Enhancements

- REST Controllers with CRUD APIs
- DTOs and request validation
- Global exception handling
- Authentication & Authorization
- Pagination and sorting
- Swagger API documentation

---

## 📚 Learning Outcome

Through this project, you will understand:
- How real backend projects are structured
- How business logic flows in Spring Boot
- How entities map to database tables
- How repositories interact with services
- How Git and GitHub are used professionally

---

## 👨‍💻 Author

**Sanchit Pawar**  
Backend Developer (Java | Spring Boot)

---

⭐ If you find this project useful, consider giving it a star!
