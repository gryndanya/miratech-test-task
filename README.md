# Miratech Tech Task
## Table of Contents
1. [Introduction](#Introduction)
2. [Tasks](#Tasks)
3. [Project Requirements](#Project-Requirements)
4. [Installation](#Installation)
5. [API Documentation](#API-Documentation)

## Introduction
I would like to ask you to develop a simple RESTful API for managing tasks. 
The API should allow users to perform CRUD operations (Create, Read, Update, Delete) on tasks

## Tasks

### Technologies:
- Use Spring Boot for server-side development.
- Use any appropriate database of your choice (e.g., PostgreSQL, SQLite, etc.) for storing tasks.
- Use git as a version control system (maybe create a private GitHub repository)

### Endpoints:
The API should have endpoints for performing CRUD operations on tasks.

Implement the following endpoints:
- GET /tasks: Retrieve all tasks.
- GET /tasks/:id: Retrieve a specific task by ID.
- POST /tasks: Create a new task.
- PUT /tasks/:id: Update an existing task by ID.
- DELETE /tasks/:id: Delete a task by ID.

### Data Persistence:
- Persist task data using a database of your choice.
- Set up appropriate database schema and models for storing tasks.

### Optional tasks:

#### Validation:
- [x] Implement basic validation for input data.
- [x] Ensure that required fields are present when creating or updating tasks.
- [x] Validate the input data to ensure it meets the expected format and type.
#### Error Handling:
- [x] Implement error handling for various scenarios (e.g., invalid input, server errors, etc.).
- [x] Return appropriate HTTP status codes and error messages.
#### Testing:
- [x] Write basic integration tests to ensure that API endpoints work as expected.
Use a testing framework
Test CRUD operations for tasks, including edge cases.
#### Filtering:
- [x] Add optional filtering of tasks by attributes in GET method
Optional attributes may be present in query in form &attr1=value1&attr2=value2
Only tasks with matching attributes are returned in response

## Project Requirements
- Java version 17.0.7
- Spring Boot 3.2.5
- Maven 4.0.0
- Docker*
- PostgreSQL

## Installation

## API Documentation:
### Swagger
Swagger is integrated into this project to provide interactive API documentation. With Swagger, you can easily explore and test the available endpoints without leaving your browser.

To access the Swagger documentation, follow these steps:

1. Ensure the application is running locally.
2. Open a web browser and navigate to `http://localhost:8080/swagger-ui/index.html#/`.
3. You will see the Swagger UI interface, which displays a list of available endpoints along with detailed information about each endpoint, including request and response formats.
4. Explore the available endpoints by expanding the sections and clicking on individual endpoints to view their details.
5. You can also test the endpoints directly from the Swagger UI interface by providing input parameters and executing requests.

### API Requests

