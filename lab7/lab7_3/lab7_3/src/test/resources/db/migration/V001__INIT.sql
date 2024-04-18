CREATE TABLE employee
(

    id          SERIAL         NOT NULL PRIMARY KEY,
    name     VARCHAR(255)   NOT NULL,
    age         INT            NOT NULL,
    salary      INT            NOT NULL,
    jobposition VARCHAR(255)   NOT NULL
);

INSERT INTO employee (id, name, age, salary, jobposition) VALUES (1, 'Foo', 28, 4000, 'Manager');
INSERT INTO employee (id, name, age, salary, jobposition) VALUES (2, 'Bar', 30, 1900, 'Dev');