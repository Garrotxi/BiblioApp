CREATE DATABASE  IF NOT EXISTS `biblioapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `biblioapp`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioapp
-- ------------------------------------------------------
-- Server version	5.6.51

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
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(1,2),(2,1),(3,1),(3,2),(4,1),(5,1),(5,2),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuaris`
--

DROP TABLE IF EXISTS `usuaris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuaris` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_usuari` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `contrasenya` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `telefon` int(11) DEFAULT NULL,
  `data_registre` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT '1' COMMENT 'STATUS - Camp per a definir el estat del usuari.\\n1 = Actiu\\n0 = Inactiu/Desactivat',
  `cognoms` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuaris`
--

LOCK TABLES `usuaris` WRITE;
/*!40000 ALTER TABLE `usuaris` DISABLE KEYS */;
INSERT INTO `usuaris` VALUES (9,'234567','$2a$10$JJDwP0KQJHtmhSOmO8IFkes2xR.TOvHiUytRMWhHdP0GDC5bEHyHe',NULL,'123456@mail.com',NULL,'2021-11-08 20:05:18',1,NULL),(10,'sdfsdfsdf','$2a$10$lF0WCkVWiKi/7/cq4NvMWuEEOR96EAvUb6yXvH/VPPLFqvlfzFhVS',NULL,'32467dcgv@mail.com',NULL,'2021-11-08 20:05:18',1,NULL),(11,'asdyy54','$2a$10$7YQjDLBwjwi6rGJWdjTjNeQ9WatRXjQwyYYmisHeGLWAiY.HBhv0W',NULL,'asdcvhrt@mail.com',NULL,'2021-11-08 20:05:18',1,NULL),(12,'asdtbs','$2a$10$7lMQdO3XUgJMmtUHqZqEeeCMN0wBeJS0bTgMKQJXYoesn1HViHkuS',NULL,'ewrzxchw@mail.com',NULL,'2021-11-08 20:05:18',1,NULL),(13,'asdgb vbky','$2a$10$tE0b9bzBrDLfkvkD4DDRX.9vDRSBWUY.Wgjnl7Zp44oq57M8ct.Wy',NULL,'123dsfcvcbv45@mail.com',NULL,'2021-11-08 20:05:18',1,NULL),(14,'asasdcc gb vbky','$2a$10$e6Y387J6GVkDdLefFW90/OmHqG3h2Z8d81dLlIJTpQwaVnZS5LEXe',NULL,'asxc trfue@mail.com',NULL,'2021-11-08 20:05:18',1,NULL),(15,'qwerx gb vbky','$2a$10$5JiSi9.8UnylNQSKBp2ZeepL0cFFAvrXx4Z5fM1jOiSrr5lIqTipi',NULL,'sdafggh trfue@mail.com',NULL,'2021-11-08 20:05:36',1,NULL);
/*!40000 ALTER TABLE `usuaris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'biblioapp'
--

--
-- Dumping routines for database 'biblioapp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-08 21:05:52
