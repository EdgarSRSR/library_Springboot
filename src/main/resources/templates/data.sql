DROP TABLE IF EXISTS users;

CREATE TABLE users (
  userid INT AUTO_INCREMENT PRIMARY KEY,
  fio VARCHAR(50) NOT NULL,
  telephone VARCHAR(50),
  jobpos VARCHAR(250) NOT NULL,
  department VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  admin BOOLEAN NOT NULL
  );

DROP TABLE IF EXISTS books;

CREATE TABLE books (
    bookid INT AUTO_INCREMENT  PRIMARY KEY,
    title VARCHAR(60) NOT NULL,
    author VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    isbn VARCHAR(30) NOT NULL,
    yearpub int(4),
    publisher VARCHAR(50),
    availability BOOLEAN NOT NULL
    );

INSERT INTO users (fio, telephone, jobpos, department, email, admin) VALUES
  ('Ivanovich Ivan Ivanov', '899999999999', 'Developer', 'IT', 'ivanovich@gmail.com', true),
  ('Markechenko Anna Mikailova', '777777777777', 'Sales Analyst', 'Sales', 'm2019@hotmail.com', false),
  ('Sergeyev Sergey Sergeyevich', '666666666666', 'Recruiter', 'HR', 'neo_matrix@yandex.com',false);


INSERT INTO books (title, author, genre, isbn, yearpub, publisher, availability) VALUES
    ('Crime and Punishment', 'Dostoyevsky', 'Fiction', '978-0-86819-593-3', '1880', 'Books Inc.', true),
    ('War and Peace', 'Tolstoy', 'Fiction', '978-0-89919-593-4', '2005', 'Tomsk Publishing', false),
    ('Pride and Prejudice', 'Jane Austen', 'Fiction', '888-0-89919-593-4', '1978', 'Books Inc.', false);