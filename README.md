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
- A **PostgreSQL** database running

### Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/ericduncandev/tennistrackr.git
    ```
   
2. Use Docker compose in the compose.yml file to initialize a database to use

**Note**: If you do not have docker, you can configure the database connection in the `application.properties` file to the database you are using:

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
```

### Example Response: Get All Matches
```json
[
   {
      "id": 1,
      "tournament_id": 1,
      "round": "Quarterfinal",
      "courtSurface": "Clay",
      "matchDate": "2024-10-07T14:00:00",
      "duration": 5400
   },
   {
      "id": 2,
      "tournament_id": 2,
      "round": "Final",
      "courtSurface": "Grass",
      "matchDate": "2024-09-15T10:30:00",
      "duration": 7200
   }
]
```

## Project Structure
```
src
├── main
│   ├── java
│   │   └── com.ericduncandev.TennisTrackr
│   │       ├── TennisGame
│   │       │   ├── TennisMatchController.java
│   │       │   ├── TennisMatchRepository.java
│   │       │   └── Records
│   │       │       └── TennisMatches.java
│   │       └── Application.java
│   └── resources
│       └── application.properties

```

## Database Schema

TennisMatches Table:
```postgresql
-- Players table
CREATE TABLE IF NOT EXISTS Players
(
    player_id   SERIAL       NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    nationality VARCHAR(100),
    ranking     INT
);

-- Tournaments table
CREATE TABLE IF NOT EXISTS Tournaments
(
    tournament_id SERIAL       NOT NULL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    location      VARCHAR(100),
    start_date    DATE,
    end_date      DATE
);

-- TennisMatches table
CREATE TABLE IF NOT EXISTS TennisMatches
(
    match_id      SERIAL      NOT NULL PRIMARY KEY,
    tournament_id INT         NOT NULL,
    round         VARCHAR(50),
    court_surface VARCHAR(20) NOT NULL,
    match_date    TIMESTAMP   NOT NULL,
    duration      TIME,
    FOREIGN KEY (tournament_id) REFERENCES Tournaments (tournament_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- MatchPlayers table
CREATE TABLE IF NOT EXISTS MatchPlayers
(
    match_id    INT     NOT NULL,
    player_id   INT     NOT NULL,
    team_number INT,
    is_winner   BOOLEAN NOT NULL,
    PRIMARY KEY (match_id, player_id),
    FOREIGN KEY (match_id) REFERENCES TennisMatches (match_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (player_id) REFERENCES Players (player_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Sets table
CREATE TABLE IF NOT EXISTS Sets
(
    set_id                SERIAL PRIMARY KEY,
    match_id              INT NOT NULL,
    set_number            INT NOT NULL,
    team1_games           INT NOT NULL,
    team2_games           INT NOT NULL,
    team1_tiebreak_points INT,
    team2_tiebreak_points INT,
    FOREIGN KEY (match_id) REFERENCES TennisMatches (match_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- PlayerStats table
CREATE TABLE IF NOT EXISTS PlayerStats
(
    stat_id            SERIAL PRIMARY KEY,
    match_id           INT NOT NULL,
    player_id          INT NOT NULL,
    aces               INT,
    double_faults      INT,
    first_serves_in    INT,
    first_serves_total INT,
    winners            INT,
    unforced_errors    INT,
    points_won         INT,
    FOREIGN KEY (match_id) REFERENCES TennisMatches (match_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (player_id) REFERENCES Players (player_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);
```