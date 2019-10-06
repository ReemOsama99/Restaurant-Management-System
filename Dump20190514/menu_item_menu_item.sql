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
-- Table structure for table `menu_item`
--

DROP TABLE IF EXISTS `menu_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menu_item` (
  `idmenu_item` int(11) NOT NULL,
  `namemenu_item` varchar(45) NOT NULL,
  `rankmenu_item` int(11) NOT NULL,
  `pricemenu_item` int(11) NOT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`idmenu_item`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_item`
--

LOCK TABLES `menu_item` WRITE;
/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` VALUES (1,' Creamy Chicken Mashroom Soup',1,35,'Appetizers'),(2,' Seafood Soup',1,40,'Appetizers'),(3,' Caesar Salad ',3,40,'Appetizers'),(4,' Grilled Chicken with Avocado Salad ',3,40,'Appetizers'),(5,' French Fries ',5,20,'Appetizers'),(6,' Garlic Bread ',0,20,'Appetizers'),(7,' Chicken Lasagna ',5,60,'Pasta'),(8,' Fettuccine Chicken pasta ',1,65,'Pasta'),(9,' Salmon Lasagna ',0,70,'Pasta'),(10,' Veal Noodles ',0,65,'Pasta'),(11,' Chicken Pesto Pasta ',3,60,'Pasta'),(12,'Mac & Cheese Chicken',3,60,'Pasta'),(13,' Classic Chicken Burger ',4,60,'Burger'),(14,' Pizza Burger ',1,60,'Burger'),(15,' Cheese Burger ',1,55,'Burger'),(16,' Crispy Chicken Sandwich ',2,55,'Burger'),(17,' Fried Shrimp Sandwich ',4,60,'Burger'),(18,' Roast Beef club ',5,40,'Burger'),(19,' Seafood Pizza ',2,70,'Pizza'),(20,' Meat lovers Pizza ',0,60,'Pizza'),(21,' Pepperoni Pizza ',0,55,'Pizza'),(22,' Vegetarian Pizza ',2,50,'Pizza'),(23,' Cheese lovers Pizza',4,50,'Pizza'),(24,'Chicken Ranch',2,60,'Pizza'),(25,' Iced Tea ',3,30,'Drinks'),(26,' Kiwi Smoothie ',5,35,'Drinks'),(27,' Oreo Milkshake ',2,40,'Drinks'),(28,' Pinklemonade Juice ',3,40,'Drinks'),(29,' Blueberry Mojito ',2,40,'Drinks'),(30,'Espresso',3,40,'Drinks'),(31,' Cheesecake ',2,45,'Desserts'),(32,' Chocolate Fudge ',4,50,'Desserts'),(33,' Icecream Roll ',1,35,'Desserts'),(34,'Cinnabon ',4,60,'Desserts'),(35,' Molten Cake ',1,50,'Desserts'),(36,' Brownie Icecream ',5,55,'Desserts');
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;
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
