# Customer Support Ticketing System

A simple web-based ticketing system built with Spring Boot and MySQL where customers can raise support tickets and support agents can respond, manage, and resolve them.

## Features

- **User Authentication**: Simple login/registration without password encryption
- **Role-Based Access**: Customer, Agent, Admin roles with different permissions
- **Ticket Management**: Create, view, and manage tickets
- **Reply System**: Customers and agents can communicate through ticket replies
- **Admin Panel**: Assign/unassign tickets, manage ticket status
- **Filters**: Filter tickets by status, assigned agent, etc.

## Tech Stack

- **Backend**: Java 21, Spring Boot 3.1
- **Database**: MySQL
- **Build Tool**: Maven
- **API Testing**: Postman

## Prerequisites

- Java 21 or higher
- Maven
- MySQL Server
- Postman

## Setup & Running Instructions

### 1. Database Setup

Ensure you have MySQL running. The application will create the database automatically with the following default configuration:

```
Database Name: ticketing
Username: root
Password: root
```

If you need to change these settings, modify the `application.properties` file.

### 2. Clone the Repository

```bash
git clone <repository-url>
cd customer-support-ticketing
```

### 3. Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

### 4. Default Users

The application creates the following default users on startup:

- **Admin**
  - Email: admin@support.com
  - Password: admin
  - Role: ADMIN

- **Agent 1**
  - Email: agent1@support.com
  - Password: 1234
  - Role: AGENT

- **Agent 2**
  - Email: agent2@support.com
  - Password: 1234
  - Role: AGENT

## API Endpoints

### User Management

- **Register User**: `POST /api/users/register`
- **Login**: `POST /api/users/login`
- **Get All Agents**: `GET /api/users/agents`

### Ticket Management

- **Create Ticket**: `POST /api/tickets?userId={id}`
- **Get Customer Tickets**: `GET /api/tickets/customer/{customerId}`
- **Get Agent Tickets**: `GET /api/tickets/agent/{agentId}`
- **Get All Tickets**: `GET /api/tickets/all`
- **Get Unassigned Tickets**: `GET /api/tickets/unassigned`
- **Get Tickets by Status**: `GET /api/tickets/status/{OPEN|IN_PROGRESS|RESOLVED}`
- **Get Ticket by ID**: `GET /api/tickets/{id}`
- **Assign Ticket**: `PUT /api/tickets/{id}/assign`
- **Unassign Ticket**: `PUT /api/tickets/{id}/unassign`
- **Update Ticket Status**: `PUT /api/tickets/{id}/status`

### Reply Management

- **Add Reply**: `POST /api/tickets/{ticketId}/replies?userId={id}`
- **Get Replies**: `GET /api/tickets/{ticketId}/replies`

## Example API Usage

### Register a new customer

```
method : POST
 http://localhost:8080/api/users/register 
 {
    "name": "Customer 1",
    "email": "customer1@example.com",
    "password": "password",
    "role": "CUSTOMER"
  }'
```

### Login

```
Method : POST
http://localhost:8080/api/users/login 
 {
    "email": "customer1@example.com",
    "password": "password"
  }
```

### Create a ticket

```
Method: POST
http://localhost:8080/api/tickets?userId=4 
 {
    "title": "Issue with login",
    "description": "I cannot login to my account",
    "priority": "HIGH"
  }
```

### Assign a ticket to an agent

```
Method : PUT
http://localhost:8080/api/tickets/1/assign 
  {
    "agentId": 2
  }
```

### Add a reply to a ticket

```bash
Method : POST
http://localhost:8080/api/tickets/1/replies?userId=2 
  {
    "message": "I am looking into this issue, will get back to you shortly."
  }
```

## User Flow

1. **Customer**:
   - Register/Login
   - Create new tickets
   - View their tickets
   - Reply to tickets

2. **Agent**:
   - Login with credentials provided by admin
   - View assigned tickets
   - Reply to tickets
   - Update ticket status

3. **Admin**:
   - Login with admin credentials
   - View all tickets
   - Assign tickets to agents
   - Filter tickets by various criteria

## Note

This is a simple implementation focusing on backend functionality without password encryption as requested.
