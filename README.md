# PetCare Backend

To run this project locally, follow the instructions below to set up your development environment.

## Prerequisites

Make sure the following software is installed:

- **Java 17** – This project uses Java 17.
- **PostgreSQL** – A relational database to store user and animal data.
- **pgAdmin 4** – A graphical interface to manage your PostgreSQL database.
- **Postman** – For testing the API endpoints.

## Setting Up PostgreSQL

1. **Install PostgreSQL** (if not already installed)
    - You can download it from [PostgreSQL's official website](https://www.postgresql.org/download/).

2. **Create Database:**
    - Open **pgAdmin 4** and log in to your PostgreSQL server.
    - Create a new database called **petplatform**.

3. **Configure Database Connection:**
    - Use the following credentials for the database connection:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/petplatform
    spring.datasource.username=postgres
    spring.datasource.password=pass
    ```

## Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/PetCare-Connect/petcare-backend.git
```
Running the Application
Navigate to the project folder:

bash
Copy
cd petcare-backend
Run the application with Gradle:

bash
```
./gradlew bootRun
```
By default, the application will run on http://localhost:8081.

Testing with Postman
Open Postman and import the API requests for testing.

Base URL for testing is: http://localhost:8081.

You can now test the following API endpoints:

1. Sign Up (POST)
URL: /auth/signup

Body (JSON):

json
```
{
  "username": "testuser",
  "email": "testuser@example.com",
  "password": "password123",
}
```
2. Log In (POST)
URL: /auth/login

Body (JSON):

json
```
{
  "username": "testuser",
  "password": "password123"
}
```
3. POST a new pet
URL: /animals
```
{
  "name": "Riki",
  "breed": "Pug",
  "age": 2,
  "location": "Cluj-Napoca",
  "adopted": false
}
```
- Adopt a new pet (POST)
URL: /animals/{id}/adopt

Authorization: Bearer Token (from the login response)

Body (JSON):
```
{
  "name": "Riki",
  "breed": "Pug",
  "age": 2,
  "location": "Cluj-Napoca",
  "adopted": false
}
```
Example of Animal JSON:(PUT)
```
{
  "id": 3,
  "name": "Riki",
  "breed": "Pug",
  "age": 2,
  "location": "Cluj-Napoca",
  "adopted": false
}
```
Common Endpoints
GET /animals – Get a list of all animals.

POST /animals – Add a new animal.

PUT /animals/{id} – Update an existing animal.

DELETE /animals/{id} – Delete an animal.
