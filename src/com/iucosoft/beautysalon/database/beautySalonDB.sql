CREATE DATABASE  IF NOT EXISTS `salon` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `salon`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: salon
-- ------------------------------------------------------
-- Server version	5.7.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerid` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customerid`),
  UNIQUE KEY `id_UNIQUE` (`customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (6,'Liudmila','+37368552123',''),(7,'Ion','+373','rusanovschi1994@mail.ru'),(11,'Guranda Nikoleta','+37368552115','guranda@gmail.com'),(12,'Guranda Nikoleta','+37368552445','Guranga'),(13,'Cristian','+37368558223','cristian@mail.ru'),(15,'Ion','+37368225442','-'),(16,'Guranda Valeria','+37368554258','gurandaN@gmail.com'),(17,'Ionel','65464','dsfgd'),(18,'Fotachi Anastasia','+3736955213','fotachi@gmail.com'),(19,'Raducat Adrian','+373 68 002 554','raducan@gmail.com'),(20,'Test Test','+37368111255','test@gmail.com');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operations`
--

DROP TABLE IF EXISTS `operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operations` (
  `operid` int(11) NOT NULL AUTO_INCREMENT,
  `customerID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `opertype` varchar(45) DEFAULT NULL,
  `operstatus` varchar(45) DEFAULT NULL,
  `dateregistration` date DEFAULT NULL,
  PRIMARY KEY (`operid`),
  UNIQUE KEY `operid_UNIQUE` (`operid`),
  KEY `customerID_idx` (`customerID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `fk_customerID` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operations`
--

LOCK TABLES `operations` WRITE;
/*!40000 ALTER TABLE `operations` DISABLE KEYS */;
INSERT INTO `operations` VALUES (5,7,9,'Alungire','Inregistrat','2020-01-11'),(12,16,8,'Alungire','Inregistrat','2020-01-11'),(19,13,4,'Alungire','Inregistrat','2020-01-15'),(20,16,5,'Alungire','Inregistrat','2020-01-22'),(21,16,4,'Corectie','Inregistrat','2020-01-30'),(22,18,4,'Alungire','Amanat','2020-02-07'),(23,16,9,'Acoperire','Inregistrat','2020-02-12'),(24,12,5,'Clasica','Inregistrat','2020-02-25');
/*!40000 ALTER TABLE `operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  UNIQUE KEY `userid_UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'fsdgsdfgff','fdsfsd','1111','Administrator'),(4,'Crina Ratz','crina1994','222','Manager'),(5,'Neagu Marina','marina119','2223','Operator'),(8,'Luiza','luiza333','333','Administrator'),(9,'Cristian Rusanovschi','rusanovschi1994','2222','Operator');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersroles`
--

DROP TABLE IF EXISTS `usersroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usersroles` (
  `roleId` int(11) NOT NULL,
  `userRole` varchar(45) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleId_UNIQUE` (`roleId`),
  UNIQUE KEY `userRole_UNIQUE` (`userRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersroles`
--

LOCK TABLES `usersroles` WRITE;
/*!40000 ALTER TABLE `usersroles` DISABLE KEYS */;
INSERT INTO `usersroles` VALUES (1,'Administrator'),(2,'Manager'),(3,'Operator');
/*!40000 ALTER TABLE `usersroles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-17 13:57:30
