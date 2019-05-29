CREATE DATABASE `WorkerDB` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

CREATE TABLE `address` (
  `idAddress` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) CHARACTER SET latin2 NOT NULL,
  `street` varchar(45) CHARACTER SET latin2 NOT NULL,
  `number` varchar(5) COLLATE latin2_croatian_ci NOT NULL,
  PRIMARY KEY (`idAddress`),
  UNIQUE KEY `unique_address` (`city`,`street`,`number`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `company` (
  `idCompany` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin2_croatian_ci NOT NULL,
  `idAddress` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCompany`),
  UNIQUE KEY `unique_company_address` (`name`,`idAddress`),
  KEY `fk_company_address_idx` (`idAddress`),
  CONSTRAINT `fk_company_address` FOREIGN KEY (`idAddress`) REFERENCES `address` (`idAddress`) ON DELETE SET NULL ON UPDATE CASCADE
  
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `worker` (
  `JMBG` char(13) COLLATE latin2_croatian_ci NOT NULL,
  `firstName` varchar(45) COLLATE latin2_croatian_ci NOT NULL,
  `lastName` varchar(45) COLLATE latin2_croatian_ci NOT NULL,
  `birthDate` date NOT NULL,
  `idCompany` int(13) DEFAULT NULL,
  `idAddress` int(13) DEFAULT NULL,
  PRIMARY KEY (`JMBG`),
  KEY `fk_worker_address_idx` (`idAddress`),
  KEY `fk_worker_company_idx` (`idCompany`),
  CONSTRAINT `fk_worker_address` FOREIGN KEY (`idAddress`) REFERENCES `address` (`idAddress`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_worker_company` FOREIGN KEY (`idCompany`) REFERENCES `company` (`idCompany`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

