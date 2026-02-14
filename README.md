# PetCare Backend

This project was developed during my internship at **MetaMinds**. It represents the backend service for a pet adoption platform, built to manage user and animal data securely and efficiently.

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
   - Use the following credentials for the database connection (or update `application.properties` with your own):

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/petplatform
   spring.datasource.username=postgres
   spring.datasource.password=pass
