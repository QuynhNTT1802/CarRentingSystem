# FU Car Rental System

A Car Rental Management System built with Spring Boot 3.5, Thymeleaf, and SQL Server.

## Features

### Admin
- Manage customers (CRUD)
- Manage cars (CRUD with conditional delete - changes status if linked to rentals)
- Manage rental transactions
- Generate rental reports by date range (descending order)

### Customer
- Register an account
- Rent one or multiple cars in a single transaction
- Update profile information
- View rental transaction history

## Tech Stack

- **Backend:** Spring Boot 3.5, Spring Data JPA, Spring Security
- **Frontend:** Thymeleaf, Bootstrap 5
- **Database:** SQL Server
- **Build:** Maven
- **Java:** 21

## Database Schema

6 tables: `Account`, `CarProducer`, `Car`, `Customer`, `CarRental`, `Review`

## Getting Started

1. Create database `CarRentingSystem_DB` in SQL Server
2. Update `application.properties` with your DB credentials
3. Run the application
4. Access at `http://localhost:8080/login`

## Default Accounts

| Role | Email | Password |
|---|---|---|
| Admin | admin@carrental.com | 123 |
| Customer | customer@carrental.com | 123 |

## API Endpoints

| URL | Role | Description |
|---|---|---|
| `/login` | - | Login page |
| `/register` | - | Registration |
| `/home` | All | Dashboard |
| `/admin/**` | ADMIN | Admin management |
| `/customer/**` | CUSTOMER | Customer features |
