/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: pet
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `adoption_application`
--

DROP TABLE IF EXISTS `adoption_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `adoption_application` (
  `adoption_application_id` int NOT NULL AUTO_INCREMENT COMMENT '领养申请id',
  `adoption_id` int NOT NULL COMMENT '对应领养信息id',
  `applicant_id` int NOT NULL COMMENT '申请人id',
  `reason` text COMMENT '申请理由',
  `status` enum('pending','approved','rejected') DEFAULT 'pending' COMMENT '审核状态',
  `listed_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  `adopted_at` datetime DEFAULT NULL COMMENT '审核时间',
  `reviewed_by` int DEFAULT NULL COMMENT '审核人id',
  PRIMARY KEY (`adoption_application_id`),
  KEY `adoption_id` (`adoption_id`),
  KEY `applicant_id` (`applicant_id`),
  KEY `reviewed_by` (`reviewed_by`),
  CONSTRAINT `adoption_application_ibfk_1` FOREIGN KEY (`adoption_id`) REFERENCES `adoption_listing` (`adoption_id`),
  CONSTRAINT `adoption_application_ibfk_2` FOREIGN KEY (`applicant_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `adoption_application_ibfk_3` FOREIGN KEY (`reviewed_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adoption_application`
--

LOCK TABLES `adoption_application` WRITE;
/*!40000 ALTER TABLE `adoption_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `adoption_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adoption_listing`
--

DROP TABLE IF EXISTS `adoption_listing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `adoption_listing` (
  `adoption_id` int NOT NULL AUTO_INCREMENT COMMENT '领养发布id',
  `pet_id` int NOT NULL COMMENT '宠物id',
  `listed_by` int NOT NULL COMMENT '发布人id',
  `description` text COMMENT '附加描述',
  `status` enum('available','adopted') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'available' COMMENT '状态',
  `listed_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `adopted_at` datetime DEFAULT NULL COMMENT '被领养时间',
  PRIMARY KEY (`adoption_id`),
  KEY `pet_id` (`pet_id`),
  KEY `listed_by` (`listed_by`),
  CONSTRAINT `adoption_listing_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  CONSTRAINT `adoption_listing_ibfk_2` FOREIGN KEY (`listed_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adoption_listing`
--

LOCK TABLES `adoption_listing` WRITE;
/*!40000 ALTER TABLE `adoption_listing` DISABLE KEYS */;
INSERT INTO `adoption_listing` VALUES
(8,1,1,'1111111111111111','adopted','2025-04-27 15:02:42','2025-04-27 15:02:42'),
(12,1,1,'无','available','2025-04-27 23:44:49',NULL);
/*!40000 ALTER TABLE `adoption_listing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `pet_id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `breed` varchar(30) DEFAULT NULL,
  `gender` int DEFAULT NULL,
  PRIMARY KEY (`pet_id`),
  KEY `pet_user_FK` (`owner_id`),
  CONSTRAINT `pet_user_FK` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES
(1,1,'11','1',1);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `is_on` int DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
(1,'string','$2a$10$c/wxmEPFvkacIAI24sOHi.v4ENLqm.tpdPXXfM164TYFH2ynD9H72','string',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pet'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-04-30 11:06:06
