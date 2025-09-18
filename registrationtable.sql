CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  phone VARCHAR(20),
  role VARCHAR(20),
  location VARCHAR(100),
  skills TEXT,
  password VARCHAR(100)
);
