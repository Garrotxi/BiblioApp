CREATE DATABASE  IF NOT EXISTS `biblioapp` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
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
-- Table structure for table `llibres`
--

DROP TABLE IF EXISTS `llibres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `llibres` (
  `id_llibre` int(11) NOT NULL AUTO_INCREMENT,
  `copies_disponibles` int(11) DEFAULT NULL,
  `data_publicacio` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `titul` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_llibre`),
  KEY `idx_llibre_idllibre` (`id_llibre`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `llibres`
--

LOCK TABLES `llibres` WRITE;
/*!40000 ALTER TABLE `llibres` DISABLE KEYS */;
/*!40000 ALTER TABLE `llibres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rols`
--

DROP TABLE IF EXISTS `rols`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rols` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol_nom` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rols`
--

LOCK TABLES `rols` WRITE;
/*!40000 ALTER TABLE `rols` DISABLE KEYS */;
/*!40000 ALTER TABLE `rols` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuari`
--

DROP TABLE IF EXISTS `usuari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuari` (
  `id_usuari` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_bin NOT NULL,
  `nom_usuari` varchar(255) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `contrasenya` varchar(255) COLLATE utf8_bin NOT NULL,
  `cognoms` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `telefon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_usuari`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuari`
--

LOCK TABLES `usuari` WRITE;
/*!40000 ALTER TABLE `usuari` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuari` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuari_rol`
--

DROP TABLE IF EXISTS `usuari_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuari_rol` (
  `id_usuari` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL,
  PRIMARY KEY (`id_usuari`,`rol_id`),
  KEY `FKhk3fnrrrnu0opmfvcm2cssbaa` (`rol_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuari_rol`
--

LOCK TABLES `usuari_rol` WRITE;
/*!40000 ALTER TABLE `usuari_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuari_rol` ENABLE KEYS */;
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

-- Dump completed on 2021-11-23 19:09:02
