
CREATE TABLE patient (
     id INTEGER PRIMARY KEY AUTO_INCREMENT,
     last_name VARCHAR(255) NOT NULL,
     full_name VARCHAR(255) NOT NULL,
     birth_date DATE NOT NULL
);


INSERT INTO patient (last_name, full_name, birth_date) VALUES
       ('Johnson', 'John Johnson', '1985-05-12'),
       ('Smith', 'Peter Smith', '1990-07-24'),
       ('Williams', 'James Williams', '1978-11-03'),
       ('Brown', 'Alex Brown', '1995-01-17'),
       ('Davis', 'Michael Davis', '1982-09-30'),
       ('Miller', 'Daniel Miller', '2000-03-08');

