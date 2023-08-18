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
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (8,18,'jeremiahs reply to jeremiahs post',1),(6,19,'troys reply to jeremiahs post',2),(9,20,'jeremiahs reply to troys first post',1),(10,18,'troys second reply to jeremiahs first post',2);
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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (5,1,0,1);
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
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (21,1,2,'jeremiahs second post on troys page'),(22,1,1,'jeremiahs second post on jeremiahs page'),(20,2,2,'troys first post on troys page'),(19,1,2,'jeremiahs first post on troys page'),(18,1,1,'jeremiahs first post on jeremiahs page');
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
INSERT INTO `token` VALUES (1,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDE0MjcyLCJleHAiOjE2OTA0MTU3MTJ9.A_dGF1Bl8FeK5Sz4t4xlftAou0Quu54Oxbsb3Ipzwv0','BEARER',1),(2,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQxNTE1MSwiZXhwIjoxNjkwNDE2NTkxfQ.SKD0I0iKXg37nBZr_BPCA85u92AEf1QyQY607CSuN8k','BEARER',2),(52,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQxNjMwMSwiZXhwIjoxNjkwNDE3NzQxfQ.hOimJltbWAWG1XMuxg-Mjx6AW8L-h_JBoYrDhYfI-I4','BEARER',2),(102,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQxNzI4OSwiZXhwIjoxNjkwNDE4NzI5fQ.yjrbKHgiR3hQvhhKY3UW4PbPpmvpwvxOM4icICkd8xE','BEARER',2),(103,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDE5NDg2LCJleHAiOjE2OTA0MjA5MjZ9.X2TDtfz5WqbifbrM2aHSSKiUg41B14N06BhuXR6mvFY','BEARER',1),(152,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQyMTE4MCwiZXhwIjoxNjkwNDIyNjIwfQ.JPNWKfy-aOwbOoDkASXouGM1zCAxLxLtv1GustddhN8','BEARER',2),(202,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ1MjYyOCwiZXhwIjoxNjkwNDU0MDY4fQ.K7R-aTYP66lS_O77qEyhkwp4bTVJMVaHK13xaVgQu5M','BEARER',2),(203,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDY3MzIyLCJleHAiOjE2OTA0Njg3NjJ9.juSGeaePi-_U_gE-NdZBH29BhPfl4NvAyyZwEbGH2_k','BEARER',1),(204,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ2NzQyMiwiZXhwIjoxNjkwNDY4ODYyfQ.TIKTkzOn5huMnkqKf1E38tnT53m7UhVvYO-MtysClDQ','BEARER',2),(205,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDY3NDczLCJleHAiOjE2OTA0Njg5MTN9.HKX5t9NOTwrpxVabWP4RrkkA5S4HuZJUy1QFCRaCF6E','BEARER',1),(206,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ2NzU3NSwiZXhwIjoxNjkwNDY5MDE1fQ.u05dAl1ZuSlcFXV-M_k3O0qlqUhkH2kiLFiygTNejWM','BEARER',2),(207,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ2ODAxMywiZXhwIjoxNjkwNDY5NDUzfQ.K9ozEnVb6bZD4ltYnuyaJZzkFf5HiAjF73Zcrh3pWPw','BEARER',2),(208,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDY4MDY3LCJleHAiOjE2OTA0Njk1MDd9.SVi5GQQY9mclrwuJAgbP1dr2zKWs5htkoWhIcx-Clzw','BEARER',1),(209,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ3NjI1MCwiZXhwIjoxNjkwNDc3NjkwfQ.2iSWJHRyEXRmKOWxvUud3z7gsr5N_J0TZhhaEmsZECk','BEARER',2),(210,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDc2NTg3LCJleHAiOjE2OTA0NzgwMjd9.2rI-ZOJIw7PEPn8WTq717dfadR7bBWGG_9oko55esTk','BEARER',1),(211,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MDQ3NjY1MCwiZXhwIjoxNjkwNDc4MDkwfQ.E8mOv7sE0yg0rpMmHMNkaugTEQyNtRiRIuYTbzX_JGI','BEARER',2),(212,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkwNDc2NjkxLCJleHAiOjE2OTA0NzgxMzF9.Ak8kNKL20rGa1_Cu56WUOK1k93DHYUgSxp6yuY4pZ_c','BEARER',1),(252,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MTc5MTEwNiwiZXhwIjoxNjkxNzkyNTQ2fQ.7g3Tx8qcyhIXV67sysFU1Btv_-PUSokEeJQtSjR9wzI','BEARER',2),(253,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYmFpbGV5QGZha2UuY29tIiwiaWF0IjoxNjkxNzkxMTYxLCJleHAiOjE2OTE3OTI2MDF9.TqFjAKzQoZMVm-jEFg6eKWImVlYiwm97otUjrxFVadk','BEARER',1),(302,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc2FtcHNvbkBmYWtlLmNvbSIsImlhdCI6MTY5MTc5MTI0NywiZXhwIjoxNjkxNzkyNjg3fQ.WA0U8zG3bh-a1RGgOCqBvkjQcTZoQGJT1Ica8xZzC-E','BEARER',2);
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
INSERT INTO `token_seq` VALUES (401);
/*!40000 ALTER TABLE `token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `serial_version_uid` bigint NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (2,'tmcclain@fake.com','Troy','Mcclain','$2a$10$CGfOOkao0X9qxqnchcyA8.UTj0i6Z3i5wwqpOcmernRXAO.94RN8C',1234,'USER'),(1,'jsampson@fake.com','Jeremiah','Sampson','$2a$10$6ezZnX8zcMCN0aHd8wDlluNHoScqj223uQtQS/GYZAv1FA4T8DEKO',1234,'USER'),(3,'uhelm@fake.com','Uriah','Helm','$2a$10$T7hDFz42fKg8kIp8BAYID.SZMd8oIqchHb3xwOOtAsT2YKvyqvhjq',1234,'USER'),(52,'twaffles@fakeemail.com','Terrance','Waffles','$2a$10$lsVAalSE/BiUd5kQdSVPEuTM.19ZP4JQWAIZAoqAV.bvt3GJekoim',1234,'USER');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo_seq`
--

DROP TABLE IF EXISTS `userinfo_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo_seq`
--

LOCK TABLES `userinfo_seq` WRITE;
/*!40000 ALTER TABLE `userinfo_seq` DISABLE KEYS */;
INSERT INTO `userinfo_seq` VALUES (151);
/*!40000 ALTER TABLE `userinfo_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 14:54:01
