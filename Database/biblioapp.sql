CREATE DATABASE  IF NOT EXISTS `biblioapp` /*!40100 DEFAULT CHARACTER SET latin1 */;
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
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `id_autor` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'DEFAULT');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'DEFAULT');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `llibres`
--

DROP TABLE IF EXISTS `llibres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `llibres` (
  `id_llibre` int(11) NOT NULL AUTO_INCREMENT,
  `copies_disponibles` int(11) DEFAULT NULL,
  `data_publicacio` varchar(255) DEFAULT NULL,
  `descripcio` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `titul` varchar(255) DEFAULT NULL,
  `id_autor` int(11) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_llibre`),
  KEY `idx_llibre_idllibre` (`id_llibre`),
  KEY `fk_idAutor_idx` (`id_autor`),
  KEY `fk_idCategoria_idx` (`id_categoria`),
  CONSTRAINT `fk_idAutor` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_autor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idCategoria` FOREIGN KEY (`id_categoria`) REFERENCES `categories` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `llibres`
--

LOCK TABLES `llibres` WRITE;
/*!40000 ALTER TABLE `llibres` DISABLE KEYS */;
/*!40000 ALTER TABLE `llibres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestecs`
--

DROP TABLE IF EXISTS `prestecs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestecs` (
  `id_prestec` int(11) NOT NULL AUTO_INCREMENT,
  `data_devolucio` varchar(255) DEFAULT NULL,
  `data_devolucio_prevista` varchar(255) DEFAULT NULL,
  `data_prestec` varchar(255) NOT NULL,
  `id_llibre` int(11) NOT NULL,
  `id_usuari` int(11) NOT NULL,
  PRIMARY KEY (`id_prestec`),
  KEY `fk_idUsuari_idx` (`id_usuari`),
  KEY `fk_idLlibre_idx` (`id_llibre`),
  CONSTRAINT `fk_idLlibre` FOREIGN KEY (`id_llibre`) REFERENCES `llibres` (`id_llibre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUsuari` FOREIGN KEY (`id_usuari`) REFERENCES `usuari` (`id_usuari`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestecs`
--

LOCK TABLES `prestecs` WRITE;
/*!40000 ALTER TABLE `prestecs` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestecs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rols`
--

DROP TABLE IF EXISTS `rols`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rols` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol_nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rols`
--

LOCK TABLES `rols` WRITE;
/*!40000 ALTER TABLE `rols` DISABLE KEYS */;
INSERT INTO `rols` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
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
  `cognoms` varchar(255) DEFAULT NULL,
  `contrasenya` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `nom_usuari` varchar(255) NOT NULL,
  `telefon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuari`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuari`
--

LOCK TABLES `usuari` WRITE;
/*!40000 ALTER TABLE `usuari` DISABLE KEYS */;
INSERT INTO `usuari` VALUES (1,'test_cognom_admin1','$2a$10$DO9w5iXmFLulcBtm5XmAFO3ixTZGAQ172LM.QdByhrGVUAxIJl3wS','test_admin1@ioc.cat','test_admin1','test_admin1','+34 666 666 666'),(4,'roige','$2a$10$ZSZ0kaPyYmt3YGue9wMG5uxubpPJ.9k6urMhQeSeXV2n83OL0k.SC','lluis@mail.com','lluis antoni','lluis','9399923467');
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
  KEY `FKhk3fnrrrnu0opmfvcm2cssbaa` (`rol_id`),
  KEY `FK63s7x24avk2cwyrvcy35xqrk7` (`id_usuari`),
  CONSTRAINT `FK63s7x24avk2cwyrvcy35xqrk7` FOREIGN KEY (`id_usuari`) REFERENCES `usuari` (`id_usuari`),
  CONSTRAINT `FKhk3fnrrrnu0opmfvcm2cssbaa` FOREIGN KEY (`rol_id`) REFERENCES `rols` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuari_rol`
--

LOCK TABLES `usuari_rol` WRITE;
/*!40000 ALTER TABLE `usuari_rol` DISABLE KEYS */;
INSERT INTO `usuari_rol` VALUES (1,2),(1,1),(4,2);
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

-- Dump completed on 2021-12-19 17:41:59
