# TennisTrackr API

TennisTrackr is a RESTful API designed for tracking and managing tennis matches, tournaments, and player stats. This project allows you to create, update, retrieve, and delete tennis match records. It's built using **Java Spring Boot** with **JDBC** for database interaction.

## Features

- **Retrieve** all tennis matches or a specific match by ID.
- **Create** new tennis match records.
- **Update** existing tennis match records.
- **Delete** tennis match records.
- **Count** total number of matches.
- **Filter** matches by tournament.

## Technologies Used

- **Java 17**
- **Spring Boot**: For building the RESTful API.
- **JDBC**: To interact with the database.
- **Docker**: Create a container for a PostgreSQL database
- **PostgreSQL**: Backend relational database.
- **Maven**: For dependency management.
- **Slf4j**: For logging.

## Getting Started

### Prerequisites

- **Java 17** or higher installed
- **Maven** installed
- A **PostgreSQL** or **MySQL** database running

### Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/ericduncandev/tennistrackr.git
    ```
   
2. Use Docker compose in the compose.yml file to initialize a database to use

Note: If you do not have docker, you can configure the database connection in the `application.properties` file:

    properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/tennistrackr
    spring.datasource.username=yourUsername
    spring.datasource.password=yourPassword
    spring.datasource.driver-class-name=org.postgresql.Driver
    

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. The API will be available at `http://localhost:8080/api/matches`.

## API Endpoints

### Tennis Matches

| Method | Endpoint             | Description                      |
|--------|----------------------|----------------------------------|
| GET    | `/api/matches`        | Retrieve all tennis matches      |
| GET    | `/api/matches/{id}`   | Retrieve a specific match by ID  |
| POST   | `/api/matches`        | Create a new tennis match        |
| PUT    | `/api/matches/{id}`   | Update an existing tennis match  |
| DELETE | `/api/matches/{id}`   | Delete a tennis match by ID      |

### Example Request: Create a Match

```bash
POST /api/matches
Content-Type: application/json

{
  "tournament_id": 1,
  "round": "Quarterfinal",
  "courtSurface": "Clay",
  "matchDate": "2024-10-07T14:00:00",
  "duration": 5400
}
