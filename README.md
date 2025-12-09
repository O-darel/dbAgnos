# Project Idea Vault API (DB Agnostic)

A robust, database-agnostic REST API built with Spring Boot, designed to demonstrate scalable architecture and multi-database support (MySQL, PostgreSQL, MSSQL, H2) using Spring Profiles and Flyway.

## 🚀 Features

- **Database Agnostic**: Seamlessly switch between H2, MySQL, PostgreSQL, and MSSQL using profiles.
- **Modular Configuration**: Clean separation of Environment (`dev`, `prod`) and Database settings.
- **Secure Authentication**: Stateless JWT authentication (Register/Login).
- **Database Migrations**: Vendor-specific SQL migrations managed by Flyway.
- **Modern Stack**: Java 21, Spring Boot 3.4.0, Spring Data JPA.

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.4.0
- **Language**: Java 21
- **Database Access**: Spring Data JPA (Hibernate)
- **Migrations**: Flyway
- **Security**: Spring Security + JWT (jjwt)
- **Build Tool**: Gradle

## ⚙️ Configuration & Profiles

The application uses a **composed profile** strategy. You must select an **Environment** and a **Database**.

### 1. Environments
- `dev`: High verbosity (DEBUG logs), shows SQL, designed for local development.
- `prod`: Low verbosity (INFO logs), optimized for production.

### 2. Databases
- `h2`: In-memory database (default for dev).
- `mysql`: Connects to MySQL (default port 3306).
- `postgres`: Connects to PostgreSQL (default port 5432).
- `mssql`: Connects to MS SQL Server (default port 1433).

## 🏃‍♂️ How to Run

### Prerequisites
- JDK 21+ installed.
- Docker or Local instance of target DB (if not using H2).

### Running the Application

**Default (Dev + H2)**
No setup required. Runs in-memory.
```bash
./gradlew bootRun
```

**Development + MySQL**
```bash
# Ensure MySQL is running on localhost:3306, DB name 'dbagnos'
./gradlew bootRun --args='--spring.profiles.active=dev,mysql'
```

**Production + PostgreSQL**
```bash
# Ensure Postgres is running on localhost:5432, DB name 'dbagnos'
./gradlew bootRun --args='--spring.profiles.active=prod,postgres'
```

### Environment Variables
For security and flexibility, use environment variables in production:

| Variable | Description | Default |
|----------|-------------|---------|
| `JWT_SECRET` | 256-bit Secret key for signing tokens | (Dev Default) |
| `DB_HOST` | Database Hostname | `localhost` |
| `DB_PORT` | Database Port | Vendor Default |
| `DB_NAME` | Database Name | `dbagnos` |
| `DB_USER` | Database Username | Vendor Default |
| `DB_PASS` | Database Password | Vendor Default |

Example:
```bash
export JWT_SECRET="MySecureKey..."
export DB_USER="admin"
./gradlew bootRun --args='--spring.profiles.active=prod,postgres'
```

## 🔌 API Reference

### Auth
- **POST** `/api/auth/register`
  ```json
  { "username": "user", "email": "user@mail.com", "password": "123" }
  ```
- **POST** `/api/auth/authenticate`
  ```json
  { "username": "user", "password": "123" }
  ```
  _Returns: `{ "token": "eyJhb..." }`_

### Ideas (Protected)
_Headers: `Authorization: Bearer <token>`_

- **POST** `/api/ideas`
  ```json
  { "title": "My Idea", "description": "Docs", "tags": "api" }
  ```
- **GET** `/api/ideas` - List all ideas
- **GET** `/api/ideas/my` - List current user's ideas

## 📂 Project Structure

```
├── src/main/resources
│   ├── application.yml          # Main Config & Profile Groups
│   ├── application-dev.yml      # Dev Env Settings
│   ├── application-prod.yml     # Prod Env Settings
│   ├── application-h2.yml       # H2 DB Settings
│   ├── application-mysql.yml    # MySQL DB Settings
│   ├── ...
│   └── db/migration             # Flyway Scripts
│       ├── h2
│       ├── mysql
│       ├── postgresql
│       └── sqlserver
└── src/main/java/com/dbAgnos
    ├── auth         # Auth DTOs & Controller
    ├── idea         # Project Idea Domain
    ├── security     # JWT & Security Config
    └── user         # User Domain
```
