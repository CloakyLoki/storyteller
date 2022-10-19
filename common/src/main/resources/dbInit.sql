CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(128) NOT NULL,
    login      VARCHAR(128) NOT NULL UNIQUE,
    password   VARCHAR(128) NOT NULL,
    patronymic VARCHAR(128),
    surname    VARCHAR(128) NOT NULL,
    role       VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(128)              NOT NULL,
    patronymic  VARCHAR(128),
    surname     VARCHAR(128)              NOT NULL,
    birthday    DATE                      NOT NULL,
    nationality VARCHAR(128),
    religion    VARCHAR(128),
    importance  INT                       NOT NULL,
    createdBy     INT REFERENCES users (id) NOT NULL
);


CREATE TABLE IF NOT EXISTS scope
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS professional_profile
(
    id       INT REFERENCES person (id) PRIMARY KEY,
    position VARCHAR(512)              NOT NULL,
    hired_since    DATE,
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
    date        DATE         NOT NULL,
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
    importance  INT          NOT NULL
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

INSERT INTO users(name, login, password, patronymic, surname, role)
VALUES ('Andrey', 'andrey1@gmail.com', 'password', null, 'Andreev', 'Admin');

INSERT INTO person(name, patronymic, surname, birthday, nationality, religion, importance, createdBy)
VALUES ('Petr', 'Petrovich', 'Petrov', '1980-02-03', 'Russian', 'Orthodox', 2, 1);

INSERT INTO scope(name)
VALUES ('MID');

INSERT INTO professional_profile(id, position, hired_since, scope)
VALUES (1, 'Deputy minister', '1995-05-15', 1);

INSERT INTO private_profile(id, wedding)
VALUES (1, '1990-01-17');

INSERT INTO story(name, description, date, person_id)
VALUES ('New position', 'Becoming deputy minister', '1995-05-15', 1);

INSERT INTO gift(name, description, date, person_id)
VALUES ('Bible', 'Leather cover', '1995-05-15', 1);

INSERT INTO tag(name, description, importance)
VALUES ('orthodox', 'book', 2);

INSERT INTO story_tag(id_story, id_tag)
VALUES (1, 1);

INSERT INTO gift_tag(id_gift, id_tag)
VALUES (1, 1);