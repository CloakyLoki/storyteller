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

INSERT INTO story_tag(story_id, tag_id)
VALUES (1, 1);

INSERT INTO gift_tag(gift_id, tag_id)
VALUES (1, 1);