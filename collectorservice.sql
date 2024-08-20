-- database: datacollectorservice_db



-- tables script:

CREATE SCHEMA IF NOT EXISTS datacollectorservice;

CREATE TABLE datacollectorservice.user (
    id BIGINT PRIMARY KEY,
    fio TEXT
);

CREATE TABLE datacollectorservice.email (
    id BIGINT PRIMARY KEY,
    email TEXT,
    userId BIGINT,
    FOREIGN KEY (userId) REFERENCES datacollectorservice.user(id) ON DELETE CASCADE
);



-- view script:

CREATE VIEW datacollectorservice.user_emails AS
SELECT
  u.id AS user_id,
  u.fio AS user_fio,
  e.email AS user_email
FROM
  datacollectorservice.user u
FULL JOIN
  datacollectorservice.email e ON u.id = e.userId;


CREATE VIEW datacollectorservice.fio_email_list AS
SELECT
    u.fio AS user_fio,
    e.email AS user_email
FROM
    datacollectorservice.user u
        FULL JOIN
    datacollectorservice.email e ON u.id = e.userId;



-- insert data script:

INSERT INTO datacollectorservice.user (id, fio) VALUES
(1, 'Иван Иванов'),
(2, 'Петр Петров'),
(3, 'Сидр Сидоров');

INSERT INTO datacollectorservice.email (id, email, userId) VALUES
(1, 'ivan1@example.com', 1),
(2, 'ivan2@example.com', 1),
(3, 'ivan3@example.com', 1),
(4, 'petr1@example.com', 2),
(5, 'petr2@example.com', 2),
(6, 'petr3@example.com', 2),
(7, 'sidr1@example.com', 3),
(8, 'sidr2@example.com', 3),
(9, 'sidr3@example.com', 3);

