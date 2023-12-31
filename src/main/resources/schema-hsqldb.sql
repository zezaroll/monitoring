CREATE TABLE IF NOT EXISTS users (
    id INT GENERATED BY DEFAULT AS IDENTITY (start with 1) PRIMARY KEY,
    first_name VARCHAR(55) NOT NULL,
    last_name VARCHAR(55) NOT NULL
);

CREATE TABLE IF NOT EXISTS measurements (
    id INT GENERATED BY DEFAULT AS IDENTITY (start with 1)  PRIMARY KEY,
    user_id INT NOT NULL,
    gas DOUBLE NOT NULL,
    water_volume DOUBLE NOT NULL,
    water_type VARCHAR(5) NOT NULL CHECK (water_type IN ('COLD', 'HOT')),
    FOREIGN KEY (user_id) REFERENCES users(id),
);

DROP INDEX IF EXISTS idx_user_id;
CREATE INDEX idx_user_id ON measurements(user_id);