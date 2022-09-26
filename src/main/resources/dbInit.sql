CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(128) NOT NULL,
    patr_name VARCHAR(128),
    surname   VARCHAR(128) NOT NULL,
    role      VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(128)              NOT NULL,
    patr_name  VARCHAR(128),
    surname    VARCHAR(128)              NOT NULL,
    picture    VARCHAR(128),
    importance INT                       NOT NULL,
    origin     INT REFERENCES users (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS personal_profile
(
    id          INT REFERENCES person (id) PRIMARY KEY,
    birthday    DATE NOT NULL,
    nationality VARCHAR(128),
    religion    VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS scope
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS professional_profile
(
    id       INT REFERENCES person (id) PRIMARY KEY,
    position VARCHAR(512)                         NOT NULL,
    hired    DATE,
    scope    INT REFERENCES scope (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS private_profile
(
    id      INT REFERENCES person (id) PRIMARY KEY,
    wedding DATE
);

CREATE TABLE IF NOT EXISTS story
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL,
    description VARCHAR(256) NOT NULL,
    start_date  DATE         NOT NULL,
    end_date    DATE         NOT NULL,
    person_id   INT REFERENCES person (id)
);

CREATE TABLE IF NOT EXISTS gift
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(256) NOT NULL,
    description VARCHAR(256),
    date        DATE         NOT NULL,
    person_id   INT REFERENCES person (id)
);

CREATE TABLE IF NOT EXISTS tag
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL,
    description VARCHAR,
    importance  INT          NOT NULL,
    color_hash  VARCHAR      NOT NULL
);

CREATE TABLE IF NOT EXISTS story_tag
(
    id_story INT REFERENCES story (id),
    id_tag   INT REFERENCES tag (id),
    PRIMARY KEY (id_story, id_tag)
);

CREATE TABLE IF NOT EXISTS gift_tag
(
    id_gift INT REFERENCES gift (id),
    id_tag  INT REFERENCES tag (id),
    PRIMARY KEY (id_gift, id_tag)
);-- CREATE DATABASE storyteller;

CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(128) NOT NULL,
    patr_name VARCHAR(128),
    surname   VARCHAR(128) NOT NULL,
    role      VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(128)              NOT NULL,
    patr_name  VARCHAR(128),
    surname    VARCHAR(128)              NOT NULL,
    picture    VARCHAR(128),
    importance INT                       NOT NULL,
    origin     INT REFERENCES users (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS personal_profile
(
    id          INT REFERENCES person (id) PRIMARY KEY,
    birthday    DATE NOT NULL,
    nationality VARCHAR(128),
    religion    VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS scope
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS professional_profile
(
    id       INT REFERENCES person (id) PRIMARY KEY,
    position VARCHAR(512)                         NOT NULL,
    hired    DATE,
    scope    INT REFERENCES scope (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS private_profile
(
    id      INT REFERENCES person (id) PRIMARY KEY,
    wedding DATE
);

CREATE TABLE IF NOT EXISTS story
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL,
    description VARCHAR(256) NOT NULL,
    start_date  DATE         NOT NULL,
    end_date    DATE NOT NULL ,
    person_id   INT REFERENCES person (id)
);

CREATE TABLE IF NOT EXISTS gift
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(256) NOT NULL,
    description VARCHAR(256),
    date        DATE         NOT NULL,
    person_id   INT REFERENCES person (id)
);

CREATE TABLE IF NOT EXISTS tag
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL,
    description VARCHAR,
    importance  INT          NOT NULL,
    color_hash  VARCHAR      NOT NULL
);

CREATE TABLE IF NOT EXISTS story_tag
(
    id_story INT REFERENCES story (id),
    id_tag   INT REFERENCES tag (id),
    PRIMARY KEY (id_story, id_tag)
);

CREATE TABLE IF NOT EXISTS gift_tag
(
    id_gift INT REFERENCES gift (id),
    id_tag  INT REFERENCES tag (id),
    PRIMARY KEY (id_gift, id_tag)
);

INSERT INTO users(name, patr_name, surname, role)
VALUES ('Andrey', null, 'Andreev', 'Admin');

INSERT INTO person(name, patr_name, surname, picture, importance, origin)
VALUES ('Petr', 'Petrovich', 'Petrov', 'absent', 5, 1);

INSERT INTO personal_profile(id, birthday, nationality, religion)
VALUES (1, '1980-02-03', 'Russian', 'Othodox');

INSERT INTO scope(name)
VALUES ('MID');

INSERT INTO professional_profile(id, position, hired, scope)
VALUES (1, 'Deputy minister', '1995-05-15', 1);

INSERT INTO private_profile(id, wedding)
VALUES (1, '1990-01-17');

INSERT INTO story(name, description, start_date, end_date, person_id)
VALUES ('New position', 'Becoming deputy minister', '1995-05-15', '2000-05-15', 1);

INSERT INTO gift(name, description, date, person_id)
VALUES ('Bible', 'Leather cover', '1995-05-15', 1);

INSERT INTO tag(name, description, importance, color_hash)
VALUES ('orthodox', 'book', 2, 3);

INSERT INTO story_tag(id_story, id_tag)
VALUES (1, 1);

INSERT INTO gift_tag(id_gift, id_tag)
VALUES (1, 1);
