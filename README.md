# Tax Filing System

 Overview

Tax Filing System is a Java-based desktop application that allows users to create, update, submit, and track tax forms.
The system also includes an administrator role that can review, approve, or reject submitted forms.

This project demonstrates the use of Object-Oriented Programming (OOP), layered architecture, JDBC, and JavaFX.

---

* Register and login
* Create tax forms
* Update existing forms
* Submit tax forms
* View all personal forms
* Delete forms

### Admin Features

* View all submitted tax forms
* Approve tax forms
* Reject tax forms

---

## Tax Logic

The system supports two types of taxation:

* **INDIVIDUAL** → 13% tax rate
* **OSOO (business)** → 25% tax rate

Tax is calculated automatically when creating or updating a form.

---

## Architecture

The project follows a layered architecture:

UI (JavaFX)
↓
Service Layer (Business Logic)
↓
Repository Layer (JDBC)
↓
Database (PostgreSQL)

---

##  Project Structure

```
src/
 ├── model/            # Data classes (User, TaxForm)
 ├── repository/
 │    ├── interfaces/  # Interfaces
 │    └── db/          # JDBC implementations
 ├── service/          # Business logic
 ├── ui/               # JavaFX UI
 ├── util/             # Validators and helpers
 └── Main.java
```

---

##  Database

### Tables

#### users

| Column   | Type   |
| -------- | ------ |
| id       | SERIAL |
| name     | TEXT   |
| email    | TEXT   |
| password | TEXT   |
| role     | TEXT   |

---

#### tax_forms

| Column   | Type   |
| -------- | ------ |
| id       | SERIAL |
| user_id  | INT    |
| income   | DOUBLE |
| tax      | DOUBLE |
| status   | TEXT   |
| tax_type | TEXT   |

---

### Status Flow

DRAFT → SUBMITTED → APPROVED / REJECTED

---

## ⚙️ Technologies Used

* Java
* JavaFX
* PostgreSQL
* JDBC
* IntelliJ IDEA

---

## How to Run

1. Install PostgreSQL and create a database:

   ```
   CREATE DATABASE taxdb;
   ```

2. Create tables (see SQL script in project)

3. Update database connection in:

   ```
   DBConnection.java
   ```

4. Add PostgreSQL JDBC driver (.jar)

5. Run:

   ```
   MainApp.java
   ```

---

## Security

* Basic validation for email and input
* PreparedStatement used to prevent SQL injection
* Role-based access control (USER / ADMIN)

---

##  Limitations

* Passwords are stored in plain text (no hashing)
* No advanced UI (TableView not implemented)
* No transaction management
* No REST API

---

##  Future Improvements

* Password hashing (BCrypt)
* Better UI with TableView
* REST API (Spring Boot)
* Role-based security improvements
* More tax types

---

##  Author

Student project for educational purposes
