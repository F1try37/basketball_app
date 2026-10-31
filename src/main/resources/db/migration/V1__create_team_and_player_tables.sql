CREATE TABLE teams (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    founded_year INTEGER NOT NULL,
    CONSTRAINT chk_team_founded_year CHECK (founded_year BETWEEN 1891 AND 2026)
);

CREATE UNIQUE INDEX uk_team_name ON teams (lower(name));

CREATE TABLE players (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    position VARCHAR(30) NOT NULL,
    jersey_number INTEGER NOT NULL,
    birth_date DATE NOT NULL,
    games_played INTEGER NOT NULL,
    points_per_game NUMERIC(3, 1) NOT NULL,
    rebounds_per_game NUMERIC(3, 1) NOT NULL,
    assists_per_game NUMERIC(3, 1) NOT NULL,
    team_id UUID NOT NULL,
    CONSTRAINT chk_player_jersey_number CHECK (jersey_number BETWEEN 0 AND 99),
    CONSTRAINT chk_player_games_played CHECK (games_played >= 0),
    CONSTRAINT chk_player_stats_non_negative
     CHECK (points_per_game >= 0 AND rebounds_per_game >= 0 AND assists_per_game >= 0),
    CONSTRAINT chk_player_position
     CHECK (position IN ('PointGuard', 'ShootingGuard', 'SmallForward', 'PowerForward', 'Center')),
    CONSTRAINT fk_player_team
     FOREIGN KEY (team_id) REFERENCES teams (id) ON DELETE RESTRICT
);

CREATE INDEX idx_player_team_id ON players (team_id);