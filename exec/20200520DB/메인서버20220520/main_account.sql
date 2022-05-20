-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: k6s1011.p.ssafy.io    Database: main
-- ------------------------------------------------------
-- Server version	5.7.38

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `method` int(11) DEFAULT NULL,
  `permit_endpoint` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj6btq2syee7yt3jsl2f511171` (`client_id`),
  CONSTRAINT `FKj6btq2syee7yt3jsl2f511171` FOREIGN KEY (`client_id`) REFERENCES `api_key` (`client_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (3,1,'/','1234'),(4,0,'/1234','1234'),(5,1,'/deposit','1234'),(6,1,'/withdraw','1234'),(7,1,'/history','1234'),(8,1,'/balance','1234'),(9,1,'/list','1234'),(10,0,'/balance','1234'),(11,1,'/history','3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278'),(12,1,'/','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(13,1,'/deposit','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(14,1,'/remit','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(15,1,'/withdraw','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(16,1,'/','3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278'),(17,1,'/balance','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(18,1,'/list','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(19,1,'/history','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(20,1,'/balance','3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278'),(21,1,'/','1111'),(22,1,'/balance','1111'),(23,1,'/list','1111'),(24,1,'/withdraw','3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278'),(25,1,'/','7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451'),(26,1,'/list','7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451'),(27,1,'/history','7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451'),(28,1,'/balance','7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451'),(29,1,'/deposit','7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451'),(30,1,'/withdraw','7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451'),(31,1,'/list','19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7'),(32,1,'/balance','19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7'),(33,1,'/history','19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7'),(34,1,'/deposit','19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7'),(35,1,'/withdraw','19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7'),(36,1,'/deposit','e7f6c011776e8db7cd330b54174fd76f7d0216b612387a5ffcfb81e6f0919683'),(37,1,'/withdraw','4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8'),(38,1,'/deposit','3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278'),(39,1,'/history','3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20  2:27:37
