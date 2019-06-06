CREATE DATABASE `WorkersDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `address` (
                           `id_address` int(11) NOT NULL AUTO_INCREMENT,
                           `city` varchar(45) NOT NULL,
                           `street` varchar(45) NOT NULL,
                           `number` varchar(10) NOT NULL,
                           PRIMARY KEY (`id_address`),
                           UNIQUE KEY `unique_address` (`city`,`street`,`number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `company` (
                           `id_company` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) NOT NULL,
                           `id_address` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id_company`),
                           UNIQUE KEY `unique_company_address` (`name`,`id_address`),
                           KEY `fk_company_address_idx` (`id_address`),
                           CONSTRAINT `fk_company_address` FOREIGN KEY (`id_address`) REFERENCES `address` (`id_address`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `worker` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `jmbg` char(13) NOT NULL,
                          `first_name` varchar(45) NOT NULL,
                          `last_name` varchar(45) NOT NULL,
                          `birth_date` date NOT NULL,
                          `id_company` int(13) DEFAULT NULL,
                          `id_address` int(13) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_worker_address_idx` (`id_address`),
                          KEY `fk_worker_company_idx` (`id_company`),
                          CONSTRAINT `fk_worker_address` FOREIGN KEY (`id_address`) REFERENCES `address` (`id_address`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                          CONSTRAINT `fk_worker_company` FOREIGN KEY (`id_company`) REFERENCES `company` (`id_company`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

