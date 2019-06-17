CREATE DATABASE `WorkersDB` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

CREATE TABLE `WorkersDB`.`address`
(
    `id_address` int(11)                        NOT NULL AUTO_INCREMENT,
    `city`       varchar(45) CHARACTER SET utf8 NOT NULL,
    `street`     varchar(45) CHARACTER SET utf8 NOT NULL,
    `number`     varchar(10) CHARACTER SET utf8 NOT NULL,
    PRIMARY KEY (`id_address`),
    UNIQUE KEY `unique_address` (`city`, `street`, `number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `WorkersDB`.`company`
(
    `id_company` int(11)                                                NOT NULL AUTO_INCREMENT,
    `name`       varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `id_address` int(11) DEFAULT NULL,
    PRIMARY KEY (`id_company`),
    UNIQUE KEY `unique_company_address` (`name`, `id_address`),
    KEY `fk_company_address_idx` (`id_address`),
    CONSTRAINT `fk_company_address` FOREIGN KEY (`id_address`) REFERENCES `address` (`id_address`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `WorkersDB`.`worker`
(
    `id`         int(11)                        NOT NULL AUTO_INCREMENT,
    `jmbg`       char(13) CHARACTER SET utf8    NOT NULL,
    `first_name` varchar(45) CHARACTER SET utf8 NOT NULL,
    `last_name`  varchar(45) CHARACTER SET utf8 NOT NULL,
    `birth_date` date                           NOT NULL,
    `id_company` int(13) DEFAULT NULL,
    `id_address` int(13) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_worker_address_idx` (`id_address`),
    KEY `fk_worker_company_idx` (`id_company`),
    CONSTRAINT `fk_worker_address` FOREIGN KEY (`id_address`) REFERENCES `address` (`id_address`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_worker_company` FOREIGN KEY (`id_company`) REFERENCES `company` (`id_company`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `WorkersDB`.`user`
(
    `id`         int(11)                             NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    `password`   char(80) COLLATE utf8_unicode_ci    NOT NULL,
    `first_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    `last_name`  varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    `email`      varchar(50) COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;


CREATE TABLE `WorkersDB`.`role`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `WorkersDB`.`users_roles`
(
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY `FK_ROLE_idx` (`role_id`),
    CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;