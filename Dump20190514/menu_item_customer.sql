-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: menu_item
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(45) NOT NULL,
  `customer_mobile` varchar(45) NOT NULL,
  `customer_address` varchar(45) NOT NULL,
  `customer_listOfOrders` varchar(45) DEFAULT NULL,
  `customer_password` varchar(45) NOT NULL,
  `customer_username` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`,`customer_username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Demiana Emil','01234567890','elnozha','18','68232f1bf104b0b66859b78aeca0d6330e027408','demiana'),(1,'Nour Fayed','01234567891','fl sar7ara','18','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','nourfayed'),(1,'Salsabeel Moubarak','01256478543','New Cairo','18','594af9fcac5099921af3d76011b410c308d2d78f','salsabeel'),(2,'Mariam Moubarak','01128317848','New Cairo','8','66ced696490a94dc13ae7b148ffe77e386ed3cf8','mariam'),(3,'Reem Osama','01000666360','New Cairo','1/2/5/9/13/16/17/19/20','266b8c8334fe39ccacf08174d8cf0d58c26a31c8','reem'),(4,'Nayera Mohamed','01005583969','Heliopolis','3/4/6/10/11','9fe536523029dad1c8e9b1a228af3f56c760ef1e','nayera'),(5,'Alaa ElFaioumy','01211965282','Obour',NULL,'51e95a9c1f223e2426068800254ddc87aa99c29d','alaa'),(20,'aya','222222222222222','nasr city',NULL,'6ae67d87b78409ccca0cbc814e787e98884fe04f','aya'),(21,'nourhan','1111111111111','nasr city','7','994db92d5e3493be298a8a2fd2ff69209685750','nour'),(22,'salma','522222222222222','ss',NULL,'592b3308a5a9a46e632dc552a14a74bb6edbcfd','salma'),(23,'Heba Sarhan','01234567891','Maadi','12','31d139971b398c6f970b592aa2b0b8e6e7d9b769','heba');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-14 17:15:37
