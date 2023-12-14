INSERT INTO users (first_name, last_name)
VALUES ('John', 'Doe'),
       ('Jane', 'Smith'),
       ('Bob', 'Johnson');

INSERT INTO measurements (user_id, gas, water_volume, water_type)
VALUES (1, 50.0, 100.0, 'COLD'),
       (1, 30.0, 50.0, 'HOT'),
       (2, 20.0, 75.0, 'COLD'),
       (3, 40.0, 60.0, 'HOT'),
       (3, 40.0, 60.0, 'COLD');