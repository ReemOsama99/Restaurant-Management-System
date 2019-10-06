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
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_info` (
  `order_id` int(11) NOT NULL,
  `order_cutomerName` varchar(45) NOT NULL,
  `order_deliveryTime` varchar(45) DEFAULT NULL,
  `order_deliveryBoy` varchar(45) NOT NULL,
  `order_receivedStatus` varchar(45) NOT NULL,
  `order_complainMessage` varchar(45) DEFAULT NULL,
  `order_menuItemList` varchar(200) NOT NULL,
  `order_StartTime` varchar(45) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES (1,'Reem Osama','4:11 PM','11','1',NULL,' Creamy Chicken Mashroom Soup#1& Seafood Soup#2','4:10 PM'),(2,'Reem Osama','6:34 PM','22','1',NULL,' Pizza Burger #1','6:33 PM'),(3,'Nayera Mohamed','6:54 PM','22','1',NULL,' Pepperoni Pizza #1','6:54 PM'),(4,'Nayera Mohamed','6:58 PM','22','1',NULL,' Pizza Burger #1& Pinklemonade Juice #1','6:58 PM'),(5,'Reem Osama','7:58 PM','22','1',NULL,' Seafood Soup#1','7:58 PM'),(6,'Nayera Mohamed','8:01 PM','22','1',NULL,' Pizza Burger #1','8:01 PM'),(7,'nourhan','9:42 PM','22','1',NULL,' Icecream Roll #1&Cinnabon #1& Salmon Lasagna #1','9:42 PM'),(8,'Mariam Moubarak','9:48 PM','22','1',NULL,' Icecream Roll #1& Meat lovers Pizza #1','9:48 PM'),(9,'Reem Osama','12:54 PM','22','1',NULL,' Cheese Burger #1& Molten Cake #2','12:54 PM'),(10,'Nayera Mohamed','1:25 PM','21','1',NULL,' Icecream Roll #1& Garlic Bread #1','1:25 PM'),(11,'Nayera Mohamed','1:34 PM','21','1',NULL,' Oreo Milkshake #1& Fettuccine Chicken pasta #1','1:34 PM'),(12,'Heba Sarhan','1:41 PM','21','1',NULL,' Salmon Lasagna #1','1:41 PM'),(13,'Reem Osama','2:45 PM','21','1',NULL,' Chicken Lasagna #1','2:45 PM'),(14,'Nour Fayed','3:30 PM','21','0',NULL,' Pepperoni Pizza #10','3:30 PM'),(15,'Nour Fayed','3:49 PM','21','1',NULL,' Seafood Pizza #1& Seafood Soup#2& Pinklemonade Juice #1','3:49 PM'),(16,'Reem Osama','4:38 PM','21','0',NULL,' Fettuccine Chicken pasta #1','4:38 PM'),(17,'Reem Osama','4:41 PM','21','0',NULL,' Veal Noodles #1&Cinnabon #1','4:41 PM'),(18,'Demiana Emil','5:07 PM','21','0',NULL,' Caesar Salad #4&Cinnabon #1','5:07 PM'),(19,'Reem Osama','5:12 PM','21','0',NULL,' Cheese Burger #1','5:12 PM'),(20,'Reem Osama','5:12 PM','21','0',NULL,' Pepperoni Pizza #1','5:12 PM');
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-14 17:15:38
