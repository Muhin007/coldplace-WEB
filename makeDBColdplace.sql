CREATE DATABASE `coldplace`;

USE `coldplace`;

CREATE TABLE `city` (`id` bigint(20) NOT NULL AUTO_INCREMENT, `name` varchar(100) NOT NULL, `mintemp` int(5) NOT NULL,
`maxtemp` int(5) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost';
FLUSH PRIVILEGES;
