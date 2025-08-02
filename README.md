# Microservices Project

This is a microservices-based project built using **Spring Boot**, **Spring Cloud**, and **Spring Security**. It follows a modular approach where each service is responsible for a specific piece of functionality and communicates via REST APIs.

---

## 🧱 Microservices Overview

| Service Name      | Description                                                   | Port  |
|-------------------|---------------------------------------------------------------|-------|
| API-GATEWAY       | Central gateway that routes requests to the appropriate service | 9090  |
| SERVICE-DISCOVERY | Eureka server for service registration and discovery           | 8761  |
| AUTH-SERVICE      | Handles user authentication and JWT token management           | 8083  |
| EMPLOYEE-SERVICE  | Manages employee data (CRUD operations)                        | 8082  |
| ADDRESS-SERVICE   | Manages addresses and links them to employees                  | 8081  |

---

## 🔐 Authentication Flow

- **User Registration**: `/auth/register-user`
- **Token Generation**: `/auth/generate-token`
- **Token Validation**: `/auth/validate-token/{token}`

Authentication is managed using Spring Security with JWT tokens. The token is required for accessing protected resources.

---

## 🔄 API Endpoints

### 📘 Address Service (`8081`)
- `POST /addresses/save` – Save a new address
- `GET /addresses/{id}` – Get address by ID
- `GET /addresses` – Get all addresses
- `PUT /addresses/{id}` – Update address by ID
- `DELETE /addresses/{id}` – Delete address
- `GET /addresses/employee/{empId}` – Get address by employee ID

### 👤 Employee Service (`8082`)
- `POST /employees/save` – Save a new employee
- `GET /employees/{id}` – Get employee by ID
- `PUT /employees/{id}` – Update employee by ID
- `DELETE /employees/{id}` – Delete employee
- `GET /employees/all` – Get all employees

---

## 🚪 Gateway Routing (`9090`)
API Gateway acts as a unified access point. Example routes:
- `/employees/**` → routed to **EMPLOYEE-SERVICE**
- `/addresses/**` → routed to **ADDRESS-SERVICE**
- `/auth/**` → routed to **AUTH-SERVICE**

---

## 🔍 Service Discovery (`8761`)
Eureka is used for registering and discovering all services dynamically.  
Access Eureka Dashboard at:  
`http://localhost:8761`

---

## ✅ Tech Stack

- Java 17
- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Spring Security with JWT
- Maven

---

## 🛠️ How to Run

Make sure you start the services in this order:

1. **SERVICE-DISCOVERY** (`8761`)
2. **AUTH-SERVICE** (`8083`)
3. **EMPLOYEE-SERVICE** (`8082`)
4. **ADDRESS-SERVICE** (`8081`)
5. **API-GATEWAY** (`9090`)

---

