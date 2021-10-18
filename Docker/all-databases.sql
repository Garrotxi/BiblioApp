CREATE DATABASE  IF NOT EXISTS `BiblioApp` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `BiblioApp`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: BiblioApp
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
-- Table structure for table `Autor`
--

DROP TABLE IF EXISTS `Autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Autor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `COGNOMS` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Autor`
--

LOCK TABLES `Autor` WRITE;
/*!40000 ALTER TABLE `Autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categoria` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORIA` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Llibre_autor`
--

DROP TABLE IF EXISTS `Llibre_autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Llibre_autor` (
  `ID_LLIBRE` int(11) DEFAULT NULL,
  `ID_AUTOR` int(11) DEFAULT NULL,
  KEY `FK_ID_LLIBRE_idx` (`ID_LLIBRE`),
  KEY `FK_ID_AUTOR_idx` (`ID_AUTOR`),
  CONSTRAINT `FK_ID_AUTOR` FOREIGN KEY (`ID_AUTOR`) REFERENCES `Autor` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ID_LLIBRE` FOREIGN KEY (`ID_LLIBRE`) REFERENCES `Llibres` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Llibre_autor`
--

LOCK TABLES `Llibre_autor` WRITE;
/*!40000 ALTER TABLE `Llibre_autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Llibre_autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Llibres`
--

DROP TABLE IF EXISTS `Llibres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Llibres` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITUL` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ID_CATEGORIA` int(11) DEFAULT NULL,
  `DATA_PUBLICACIO` date DEFAULT NULL,
  `COPIES_DISPONIBLES` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `FK_ID_CATEGORIA_idx` (`ID_CATEGORIA`),
  CONSTRAINT `FK_ID_CATEGORIA` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `Categoria` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Llibres`
--

LOCK TABLES `Llibres` WRITE;
/*!40000 ALTER TABLE `Llibres` DISABLE KEYS */;
/*!40000 ALTER TABLE `Llibres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prestec`
--

DROP TABLE IF EXISTS `Prestec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prestec` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_LLIBRE` int(11) NOT NULL,
  `ID_USUARI` int(11) NOT NULL,
  `DATA_PRESTEC` datetime DEFAULT NULL,
  `DATA_RETORN` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `ID_LLIBRE_idx` (`ID_LLIBRE`),
  KEY `ID_USUARI_idx` (`ID_USUARI`),
  CONSTRAINT `ID_LLIBRE` FOREIGN KEY (`ID_LLIBRE`) REFERENCES `Llibres` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ID_USUARI` FOREIGN KEY (`ID_USUARI`) REFERENCES `Usuaris` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prestec`
--

LOCK TABLES `Prestec` WRITE;
/*!40000 ALTER TABLE `Prestec` DISABLE KEYS */;
/*!40000 ALTER TABLE `Prestec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuaris`
--

DROP TABLE IF EXISTS `Usuaris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuaris` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `COGNOMS` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `EMAIL` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TELEFON` int(11) DEFAULT NULL,
  `DATA_REGISTRE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'STATUS - Camp per a definir el estat del usuari.\n1 = Actiu\n0 = Inactiu/Desactivat',
  `ADMIN` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ADMIN- Camp per a definir el rol del usuari.\n1 - Administrador\n0 - Usuari',
  `CONTRASENYA` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuaris`
--

LOCK TABLES `Usuaris` WRITE;
/*!40000 ALTER TABLE `Usuaris` DISABLE KEYS */;
INSERT INTO `Usuaris` VALUES (1,'Lluis Antoni','Roigé Higueras','lluis@mail.com',NULL,'2021-10-13 19:38:21',1,1,'root'),(2,'John','Doe','jdoe@mail.com',NULL,'2021-10-13 19:38:21',0,0,'user1');
/*!40000 ALTER TABLE `Usuaris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'BiblioApp'
--

--
-- Dumping routines for database 'BiblioApp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-18 22:05:27
