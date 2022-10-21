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
    story_id INT REFERENCES story (id),
    tag_id   INT REFERENCES tag (id),
    PRIMARY KEY (story_id, tag_id)
);

CREATE TABLE IF NOT EXISTS gift_tag
(
    gift_id INT REFERENCES gift (id),
    tag_id  INT REFERENCES tag (id),
    PRIMARY KEY (gift_id, tag_id)
);