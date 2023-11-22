CREATE SCHEMA IF NOT EXISTS 1DB_FranjoSeminErnes;

USE 1DB_FranjoSeminErnes;

CREATE TABLE IF NOT EXISTS users (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(50) NOT NULL,
                                     email VARCHAR(50) NOT NULL,
                                     address VARCHAR(100) NOT NULL,
                                     password VARCHAR(100) NOT NULL,
                                     role SMALLINT NOT NULL
);

-- Create the products table
CREATE TABLE IF NOT EXISTS products (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        name VARCHAR(50) NOT NULL,
                                        description VARCHAR(255) NOT NULL,
                                        imagePath VARCHAR(255),
                                        user_id INT,
                                        FOREIGN KEY (user_id) REFERENCES users(id)
);