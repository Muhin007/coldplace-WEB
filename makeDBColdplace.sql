CREATE DATABASE `coldplace`;

USE `coldplace`;

CREATE TABLE `city` (`id` bigint(20) NOT NULL AUTO_INCREMENT, `name` varchar(100) NOT NULL, `min` int(5) NOT NULL,
`max` int(5) NOT NULL, `url` varchar(255) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost';
FLUSH PRIVILEGES;
