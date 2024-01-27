CREATE TABLE IF NOT EXISTS flight
(
    id                   SERIAL PRIMARY KEY,
    name                 VARCHAR(255) NOT NULL,
    flight_code          VARCHAR(255) NOT NULL,
    departure_date       TIMESTAMP    NOT NULL,
    arrival_date         TIMESTAMP    NOT NULL,
    departure_airport_id INTEGER      NOT NULL,
    arrival_airport_id   INTEGER      NOT NULL,
    created_at           TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP,
    FOREIGN KEY (departure_airport_id) REFERENCES airport (id) ON DELETE CASCADE,
    FOREIGN KEY (arrival_airport_id) REFERENCES airport (id) ON DELETE CASCADE
);