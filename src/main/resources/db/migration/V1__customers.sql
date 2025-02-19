
CREATE TABLE patient (
     id INTEGER PRIMARY KEY AUTO_INCREMENT,
     last_name VARCHAR(255) NOT NULL,
     full_name VARCHAR(255) NOT NULL,
     birth_date DATE NOT NULL
);


INSERT INTO patient (last_name, full_name, birth_date) VALUES
 ('Ivanov', 'Ivan Ivanov', '1985-05-12'),
 ('Petrov', 'Petr Petrov', '1990-07-24'),
 ('Sidorov', 'Sergey Sidorov', '1978-11-03'),
 ('Smirnov', 'Alexey Smirnov', '1995-01-17'),
 ('Kuznetsov', 'Dmitry Kuznetsov', '1982-09-30'),
 ('Popov', 'Nikolay Popov', '2000-03-08');
