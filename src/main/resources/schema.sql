-- Players table
CREATE TABLE IF NOT EXISTS Players (
    player_id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nationality VARCHAR(100),
    ranking INT
);

-- Tournaments table
CREATE TABLE IF NOT EXISTS Tournaments (
    tournament_id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    start_date DATE,
    end_date DATE
);

-- TennisMatches table
CREATE TABLE IF NOT EXISTS TennisMatches (
    match_id SERIAL NOT NULL PRIMARY KEY,
    tournament_id INT NOT NULL,
    round VARCHAR(50),
    court_surface VARCHAR(20) NOT NULL,
    match_date TIMESTAMP NOT NULL,
    duration TIME,
    FOREIGN KEY (tournament_id) REFERENCES Tournaments(tournament_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

-- MatchPlayers table
CREATE TABLE IF NOT EXISTS MatchPlayers (
    match_id INT NOT NULL,
    player_id INT NOT NULL,
    team_number INT,
    is_winner BOOLEAN NOT NULL,
    PRIMARY KEY (match_id, player_id),
    FOREIGN KEY (match_id) REFERENCES TennisMatches(match_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (player_id) REFERENCES Players(player_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

-- Sets table
CREATE TABLE IF NOT EXISTS Sets (
    set_id SERIAL PRIMARY KEY,
    match_id INT NOT NULL,
    set_number INT NOT NULL,
    team1_games INT NOT NULL,
    team2_games INT NOT NULL,
    team1_tiebreak_points INT,
    team2_tiebreak_points INT,
    FOREIGN KEY (match_id) REFERENCES TennisMatches(match_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- PlayerStats table
CREATE TABLE IF NOT EXISTS PlayerStats (
   stat_id SERIAL PRIMARY KEY,
   match_id INT NOT NULL,
   player_id INT NOT NULL,
   aces INT,
   double_faults INT,
   first_serves_in INT,
   first_serves_total INT,
   winners INT,
   unforced_errors INT,
   points_won INT,
   FOREIGN KEY (match_id) REFERENCES TennisMatches(match_id)
   ON DELETE CASCADE
   ON UPDATE CASCADE,
   FOREIGN KEY (player_id) REFERENCES Players(player_id)
   ON DELETE RESTRICT
   ON UPDATE CASCADE
);