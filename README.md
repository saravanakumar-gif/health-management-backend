# 🏥 Health Management System - Backend

## 📌 Description

This project is a backend REST API for a Health Management System. It is designed to manage healthcare-related data such as patients, doctors, appointments, and medical records efficiently.

The system provides structured APIs for handling hospital operations and ensures smooth communication between frontend and database.

---

## 🛠️ Tech Stack

* Backend: Java / Spring Boot
* Database: MySQL / PostgreSQL
* ORM: Hibernate / JPA
* API: RESTful APIs
* Tools: Maven

---

## ✨ Features

* 👤 Patient Management (Add, View, Update, Delete)
* 👨‍⚕️ Doctor Management
* 📅 Appointment Scheduling
* 🗂️ Medical Records Handling
* 🔄 CRUD Operations
* 🔗 REST API Integration

---

## 📂 Project Structure

```id="v73k1k"
health-management-backend/
│── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│── pom.xml
│── application.properties
```

---

## ⚙️ Installation

### 1️⃣ Clone Repository

```bash
git clone https://github.com/saravanakumar-gif/health-management-backend.git
cd health-management-backend
```

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/health_db
spring.datasource.username=root
spring.datasource.password=your_password
```

---

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

---

## 🚀 API Endpoints (Example)

| Method | Endpoint       | Description      |
| ------ | -------------- | ---------------- |
| GET    | /patients      | Get all patients |
| POST   | /patients      | Add patient      |
| PUT    | /patients/{id} | Update patient   |
| DELETE | /patients/{id} | Delete patient   |

---

## 🧪 Testing

You can test APIs using:

* Postman
* Thunder Client

---

## 📸 Screenshots

<img width="1920" height="786" alt="Screenshot (123)" src="https://github.com/user-attachments/assets/45c3012f-76b5-464a-b61d-328ccabfd537" />

<img width="1920" height="778" alt="Screenshot (120)" src="https://github.com/user-attachments/assets/e99ae9d3-5096-4cc1-b8e0-456b4f133000" />

<img width="1920" height="800" alt="Screenshot (121)" src="https://github.com/user-attachments/assets/7611735d-56ec-42dd-850a-848b6c25174e" />

<img width="1920" height="787" alt="Screenshot (122)" src="https://github.com/user-attachments/assets/a4611d89-cd15-4080-9bb0-bcd91112939c" />

---

## 🔗 Related Project

Frontend Repository:
https://github.com/saravanakumar-gif/smart-expense-tracker-frontend

---

## 👨‍💻 Author

Saravanakumar
