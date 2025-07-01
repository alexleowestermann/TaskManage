# DevTasker

Full-stack task manager app using React + Spring Boot.

## Requirements
- Java 17+
- Maven
- Node.js + npm

## Running the application locally

### Backend
```bash
cd server
./mvnw spring-boot:run
```
Runs at: http://localhost:8080

### Frontend
```bash
cd client
npm install
npm install axios
npm start
```
Runs at: http://localhost:3000

## Features
- Create, rename, delete tasks.
- Set deadlines and importance (1–10).
- Mark tasks as completed.
- UI menu.
- H2 in-memory database.
- CORS enabled (frontend ↔ backend).

## API Endpoints
| Method | Endpoint               | Description                   |
|--------|------------------------|-------------------------------|
| GET    | `/tasks`               | Get all tasks                 |
| POST   | `/tasks`               | Create a new task             |
| DELETE | `/tasks/{id}`          | Delete a task by ID           |
| PUT    | `/tasks/{id}`          | Rename a task by ID           |
| PUT    | `/tasks/{id}/complete` | Mark task complete/incomplete |

## Running tests
```bash
cd server
./mvnw test
```
Tests cover model, service, and controller logic using JUnit and Mockito.

## Author
Alexander Westermann
