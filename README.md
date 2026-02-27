Purpose and Scope:
--------------------------

This document provides a high-level introduction to the Health Management System, a REST API backend for managing healthcare appointments, patients, and doctors. 
The system is built with Spring Boot 3.2.1 and deployed as a containerized application on the Railway platform.

What is the Health Management System:
-------------------------------------

The Health Management System is a backend REST API application that provides comprehensive appointment scheduling and management capabilities for healthcare facilities. The application manages three core entities: patients, doctors, and appointments, along with supporting features like dashboard statistics and global search.
The system is containerized using Docker and deployed on Railway's platform, connecting to a MySQL database for persistent storage. It exposes a complete REST API accessible at /api/* endpoints with full CORS support.


Core Capabilities
The Health Management System provides the following functional capabilities:

| **Feature Area**           | **Capabilities**                                                                                                   | **Primary Controller** |
| -------------------------- | ------------------------------------------------------------------------------------------------------------------ | ---------------------- |
| **Appointment Management** | Create, read, update, delete appointments; query by patient, doctor, date, or status; update appointment status    | AppointmentController  |
| **Patient Management**     | Register patients; update patient information; view patient list; delete patient records                           | PatientController      |
| **Doctor Management**      | Register doctors; query doctors by specialization; view doctor profiles                                            | DoctorController       |
| **Dashboard**              | View aggregate statistics (total patients, doctors, appointments); retrieve recent appointments; status breakdowns | DashboardController    |
| **Search**                 | Global keyword search across patients and doctors                                                                  | SearchController       |



Technology Stack
----------------
The application is built using the following technologies and frameworks:

Core Framework:


Spring Boot 3.2.1 - Application framework and dependency injection container
Java 17 - Programming language (Eclipse Temurin JDK/JRE)

Web Layer:

Spring Web MVC (spring-boot-starter-web) - REST controller framework
Jackson - JSON serialization/deserialization (included with Spring Web)

Data Layer:

Spring Data JPA (spring-boot-starter-data-jpa) - ORM abstraction layer
Hibernate - JPA implementation with MySQL8 dialect
MySQL Connector/J (mysql-connector-j) - JDBC driver

Build and Deployment:

Maven 3.9 - Build automation and dependency management
Docker - Containerization with multi-stage builds
Railway Platform - Cloud deployment environment
<img width="2076" height="1592" alt="NoteGPT-Flowchart-1771522783716" src="https://github.com/user-attachments/assets/d86b3675-003e-4045-940e-65b6147965fb" />





