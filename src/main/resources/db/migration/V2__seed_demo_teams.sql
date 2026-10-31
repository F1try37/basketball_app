INSERT INTO teams (
                   id, created_at, updated_at, name, city, description, founded_year
) VALUES
    ('11111111-1111-4111-8111-111111111111', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
    'Chicago Bulls', 'Чикаго',
    'Профессиональный баскетбольный клуб из Центрального дивизиона Восточной конференции НБА.', 1966),
    ('22222222-2222-4222-8222-222222222222', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
    'Boston Celtics', 'Бостон',
    'Один из старейших и самых титулованных клубов НБА.', 1946);

INSERT INTO players (
    id, created_at, updated_at, first_name, last_name, position, jersey_number,
    birth_date, games_played, points_per_game, rebounds_per_game, assists_per_game, team_id
) VALUES (
    'aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaa1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
    'Майкл', 'Джордан', 'ShootingGuard', 23,
    DATE '1963-02-17', 1072, 30.1, 6.2, 5.3,
    '11111111-1111-4111-8111-111111111111'
);