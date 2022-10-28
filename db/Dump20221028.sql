-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: pfm
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `accountdetails`
--

DROP TABLE IF EXISTS `accountdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accountdetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountdetails`
--

LOCK TABLES `accountdetails` WRITE;
/*!40000 ALTER TABLE `accountdetails` DISABLE KEYS */;
INSERT INTO `accountdetails` VALUES (1,'admin','dbit2022'),(2,'test','123');
/*!40000 ALTER TABLE `accountdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget` (
  `b_id` int NOT NULL AUTO_INCREMENT,
  `b_amount` int NOT NULL,
  `b_balance` int NOT NULL,
  `b_duration` varchar(20) NOT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget`
--

LOCK TABLES `budget` WRITE;
/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
INSERT INTO `budget` VALUES (1,1000,10,'Weekly');
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_master`
--

DROP TABLE IF EXISTS `cat_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cat_master` (
  `catId` int NOT NULL AUTO_INCREMENT,
  `catname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`catId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_master`
--

LOCK TABLES `cat_master` WRITE;
/*!40000 ALTER TABLE `cat_master` DISABLE KEYS */;
INSERT INTO `cat_master` VALUES (4,'Stationary'),(5,'Food and Beverage'),(6,'Transportation');
/*!40000 ALTER TABLE `cat_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goals`
--

DROP TABLE IF EXISTS `goals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goals` (
  `goal_id` int NOT NULL AUTO_INCREMENT,
  `g_name` varchar(20) NOT NULL,
  `g_amount` int NOT NULL,
  `g_period` varchar(10) DEFAULT NULL,
  `g_amtsaved` int DEFAULT NULL,
  PRIMARY KEY (`goal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goals`
--

LOCK TABLES `goals` WRITE;
/*!40000 ALTER TABLE `goals` DISABLE KEYS */;
INSERT INTO `goals` VALUES (1,'SMART WATCH',1700,'1 Month',500),(2,'LAPTOP',70000,'12 Months',1000),(3,'FOOTBALL',800,'3 WEEKS',0),(4,'MECHANICAL KEYBOARD',1500,'3 Months',200),(5,'cgfh',600,'Monthly',NULL),(6,'aa',1,'Monthly',NULL),(7,'sddf',1,'Monthly',NULL),(8,'ssad',22,'Monthly',NULL),(9,'gfg',1,'Monthly',NULL);
/*!40000 ALTER TABLE `goals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `t_id` int NOT NULL AUTO_INCREMENT,
  `t_type` varchar(8) NOT NULL,
  `t_category` varchar(30) NOT NULL,
  `t_date` datetime DEFAULT NULL,
  `t_amt` int DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'Debit','Grocery',NULL,500),(2,'Credit','School',NULL,10000),(3,'Debit','Food and Beverage',NULL,20),(4,'Debit','Transportation',NULL,100),(5,'Debit','Food and Beverage',NULL,20),(6,'Debit','Food and Beverage',NULL,20),(7,'Debit','Stationary',NULL,500),(8,'Debit','Stationary',NULL,50000),(9,'Debit','Stationary',NULL,500),(10,'Debit','Food and Beverage',NULL,900);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email_id` varchar(30) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('a','a','a@a.com','a'),('a123','a','a@gmail.com','a'),('ashish123','dbit','ashvarghese03@gmail.com','ashish'),('Ashvarghes','gg','ashvarghese03@gmail.com','Ashish'),('Ashvarghese','gg','ashvarghese03@gmail.com','Ashish'),('Ashvarghese03','gg','ashvarghese03@gmail.com','Ashish'),('AshVarghese2003','dbit','321ashish0046@dbit.in','Ashish Varghese'),('daddyiscoding','dbit','daddyiscoming@s.com','Chris gadekar'),('ilyas123','dbit','ilyas@gmail.com','ilyas'),('johan123','johan123','johan@.gmail.com','Johan Manoj'),('rohan123','dbit','rohan@gmail.com','Rohan gunge'),('sample123','dbit','sample@gmail.com','sample'),('sheeba08','dbit','s@gmail.com','Sheeba Varghese'),('sussy','aditya69','sussybaka@gmail.com','Aditya Chauhan');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-28 21:15:40
