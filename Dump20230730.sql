-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: socialmedia
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `_user`
--

DROP TABLE IF EXISTS `_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_user` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `serial_version_uid` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user`
--

LOCK TABLES `_user` WRITE;
/*!40000 ALTER TABLE `_user` DISABLE KEYS */;
INSERT INTO `_user` VALUES (1,'rbailey@fake.com','Richard','Bailey','$2a$10$zHQuuUIw1r8Ud6Ay.poe/ubGyJjkzp0tcw.72Sb2cfYXS5tdjcBCS','USER',1234),(2,'jsampson@fake.com','Jeremiah','Sampson','$2a$10$eLunAOUhOjO5/3jb2gvMXem7NXioFwLk.ms0eZzoihTWMBe3aftIe','USER',1234);
/*!40000 ALTER TABLE `_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_user_seq`
--

DROP TABLE IF EXISTS `_user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user_seq`
--

LOCK TABLES `_user_seq` WRITE;
/*!40000 ALTER TABLE `_user_seq` DISABLE KEYS */;
INSERT INTO `_user_seq` VALUES (101);
/*!40000 ALTER TABLE `_user_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block` (
  `block_id` int NOT NULL AUTO_INCREMENT,
  `blocked_user_id` int NOT NULL,
  `blocker_user_id` int NOT NULL,
  PRIMARY KEY (`block_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `text` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (5,13,'richards reply to jeremiahs first post on richards page',1),(4,11,'jeremiahs first reply to jeremiahs first post',2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend` (
  `friend_id` int NOT NULL AUTO_INCREMENT,
  `from_user_id` int NOT NULL,
  `status` smallint NOT NULL,
  `to_user_id` int NOT NULL,
  PRIMARY KEY (`friend_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `author_user_id` int NOT NULL,
  `profile_user_id` int NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (15,1,2,'richards first post on jeremiahs page'),(14,1,1,'richards first post on richards page'),(13,2,1,'Jeremiahs first post on Richards page'),(12,2,2,'jeremiahs second post on jeremiahs page'),(11,2,2,'jeremiahs first post on jeremiahs page');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiblu4cjwvyntq3ugo31klp1c6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDE0MjcyLCJleHAiOjE2OTA0MTU3MTJ9.A_dGF1Bl8FeK5Sz4t4xlftAou0Quu54Oxbsb3Ipzwv0','BEARER',1),(2,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQxNTE1MSwiZXhwIjoxNjkwNDE2NTkxfQ.SKD0I0iKXg37nBZr_BPCA85u92AEf1QyQY607CSuN8k','BEARER',2),(52,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQxNjMwMSwiZXhwIjoxNjkwNDE3NzQxfQ.hOimJltbWAWG1XMuxg-Mjx6AW8L-h_JBoYrDhYfI-I4','BEARER',2),(102,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQxNzI4OSwiZXhwIjoxNjkwNDE4NzI5fQ.yjrbKHgiR3hQvhhKY3UW4PbPpmvpwvxOM4icICkd8xE','BEARER',2),(103,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDE5NDg2LCJleHAiOjE2OTA0MjA5MjZ9.X2TDtfz5WqbifbrM2aHSSKiUg41B14N06BhuXR6mvFY','BEARER',1),(152,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQyMTE4MCwiZXhwIjoxNjkwNDIyNjIwfQ.JPNWKfy-aOwbOoDkASXouGM1zCAxLxLtv1GustddhN8','BEARER',2),(202,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ1MjYyOCwiZXhwIjoxNjkwNDU0MDY4fQ.K7R-aTYP66lS_O77qEyhkwp4bTVJMVaHK13xaVgQu5M','BEARER',2),(203,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDY3MzIyLCJleHAiOjE2OTA0Njg3NjJ9.juSGeaePi-_U_gE-NdZBH29BhPfl4NvAyyZwEbGH2_k','BEARER',1),(204,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ2NzQyMiwiZXhwIjoxNjkwNDY4ODYyfQ.TIKTkzOn5huMnkqKf1E38tnT53m7UhVvYO-MtysClDQ','BEARER',2),(205,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDY3NDczLCJleHAiOjE2OTA0Njg5MTN9.HKX5t9NOTwrpxVabWP4RrkkA5S4HuZJUy1QFCRaCF6E','BEARER',1),(206,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ2NzU3NSwiZXhwIjoxNjkwNDY5MDE1fQ.u05dAl1ZuSlcFXV-M_k3O0qlqUhkH2kiLFiygTNejWM','BEARER',2),(207,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ2ODAxMywiZXhwIjoxNjkwNDY5NDUzfQ.K9ozEnVb6bZD4ltYnuyaJZzkFf5HiAjF73Zcrh3pWPw','BEARER',2),(208,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDY4MDY3LCJleHAiOjE2OTA0Njk1MDd9.SVi5GQQY9mclrwuJAgbP1dr2zKWs5htkoWhIcx-Clzw','BEARER',1),(209,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ3NjI1MCwiZXhwIjoxNjkwNDc3NjkwfQ.2iSWJHRyEXRmKOWxvUud3z7gsr5N_J0TZhhaEmsZECk','BEARER',2),(210,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDc2NTg3LCJleHAiOjE2OTA0NzgwMjd9.2rI-ZOJIw7PEPn8WTq717dfadR7bBWGG_9oko55esTk','BEARER',1),(211,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ3NjY1MCwiZXhwIjoxNjkwNDc4MDkwfQ.E8mOv7sE0yg0rpMmHMNkaugTEQyNtRiRIuYTbzX_JGI','BEARER',2),(212,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDc2NjkxLCJleHAiOjE2OTA0NzgxMzF9.Ak8kNKL20rGa1_Cu56WUOK1k93DHYUgSxp6yuY4pZ_c','BEARER',1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_seq`
--

DROP TABLE IF EXISTS `token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_seq`
--

LOCK TABLES `token_seq` WRITE;
/*!40000 ALTER TABLE `token_seq` DISABLE KEYS */;
INSERT INTO `token_seq` VALUES (301);
/*!40000 ALTER TABLE `token_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-30  9:46:54
