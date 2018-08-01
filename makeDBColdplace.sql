CREATE DATABASE `coldplace`;

USE `coldplace`;

CREATE TABLE `city` (`id` bigint(20) NOT NULL AUTO_INCREMENT, `name` varchar(100) NOT NULL, `min` int(5) NOT NULL,
`max` int(5) NOT NULL, `url` varchar(255) NOT NULL, PRIMARY KEY (`id`), UNIQUE KEY `city_name_unique` (`name`))
ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE coldplace.cityTemp (`id` bigint(20) NOT NULL AUTO_INCREMENT, `city` varchar(100) NOT NULL, `temp` INT(10)
NOT NULL,`date` date NOT NULL, CONSTRAINT id PRIMARY KEY (id))
ENGINE=InnoDB DEFAULT CHARSET=utf8;

