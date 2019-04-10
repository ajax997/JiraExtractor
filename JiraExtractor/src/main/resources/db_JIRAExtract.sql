CREATE DATABASE  IF NOT EXISTS `jira_extract` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jira_extract`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jira_extract
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `accountId` varchar(45) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `emailAddress` varchar(45) DEFAULT NULL,
  `avatarUrl` varchar(1000) DEFAULT NULL,
  `displayName` varchar(100) DEFAULT NULL,
  `active` binary(1) DEFAULT NULL,
  `self` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dashboard`
--

DROP TABLE IF EXISTS `dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dashboard` (
  `idDashboard` INT NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `_view` text,
  `self` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idDashboard`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dashboard`
--

LOCK TABLES `dashboard` WRITE;
/*!40000 ALTER TABLE `dashboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `dashboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `idIssue` INT NOT NULL,
  `_key` varchar(45) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `issuetype` INT DEFAULT NULL,
  `parent` INT DEFAULT NULL,
  `project` INT DEFAULT NULL,
  `fixVersions` INT DEFAULT NULL,
  `assignee` varchar(45) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `reporter` varchar(45) DEFAULT NULL,
  `sprint` INT DEFAULT NULL,
  `self` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idIssue`),
  KEY `fk_Issue_IssueType_idx` (`issuetype`),
  KEY `fk_Issue_IssueParent_idx` (`parent`),
  KEY `fk_Issue_Project_idx` (`project`),
  KEY `fk_Issue_Version_idx` (`fixVersions`),
  KEY `fk_Issue_Account_Assignee_idx` (`assignee`),
  KEY `fk_Issue_Account_Creator_idx` (`creator`),
  KEY `fk_Issue_Reposter_idx` (`reporter`),
  KEY `fk_Issue_Sprint_idx` (`sprint`),
  CONSTRAINT `fk_Issue_Account_Assignee` FOREIGN KEY (`assignee`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_Issue_Account_Creator` FOREIGN KEY (`creator`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_Issue_IssueParent` FOREIGN KEY (`parent`) REFERENCES `issue` (`idIssue`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_Issue_IssueType` FOREIGN KEY (`issuetype`) REFERENCES `issuetype` (`idIssuetype`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Issue_Project` FOREIGN KEY (`project`) REFERENCES `projectdetail` (`idProject`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Issue_Reposter` FOREIGN KEY (`reporter`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_Issue_Sprint` FOREIGN KEY (`sprint`) REFERENCES `sprint` (`idSprint`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_Issue_Version` FOREIGN KEY (`fixVersions`) REFERENCES `version` (`idVersion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuetype`
--

DROP TABLE IF EXISTS `issuetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issuetype` (
  `idIssuetype` INT NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `iconUrl` varchar(1000) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `subtask` binary(1) DEFAULT NULL,
  `self` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idIssuetype`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuetype`
--

LOCK TABLES `issuetype` WRITE;
/*!40000 ALTER TABLE `issuetype` DISABLE KEYS */;
/*!40000 ALTER TABLE `issuetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `idProject` INT NOT NULL,
  `_key` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `avatarUrl` varchar(1000) CHARACTER SET latin1 DEFAULT NULL,
  `projectTypeKey` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `isPrivate` binary(1) DEFAULT NULL,
  `self` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idProject`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectdetail`
--

DROP TABLE IF EXISTS `projectdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectdetail` (
  `idProject` INT NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `lead` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProject`),
  KEY `fk_Project_Account_lead_idx` (`lead`),
  CONSTRAINT `fk_Detail_Project` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Project_Account_lead` FOREIGN KEY (`lead`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectdetail`
--

LOCK TABLES `projectdetail` WRITE;
/*!40000 ALTER TABLE `projectdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `projectdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint`
--

DROP TABLE IF EXISTS `sprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprint` (
  `idSprint` INT NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `startDate` varchar(45) DEFAULT NULL,
  `endDate` varchar(45) DEFAULT NULL,
  `project` INT DEFAULT NULL,
  `self` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idSprint`),
  KEY `fk_Sprint_Project_idx` (`project`),
  CONSTRAINT `fk_Sprint_Project` FOREIGN KEY (`project`) REFERENCES `projectdetail` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint`
--

LOCK TABLES `sprint` WRITE;
/*!40000 ALTER TABLE `sprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `sprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `version` (
  `idVersion` INT NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `archived` binary(1) DEFAULT NULL,
  `released` binary(1) DEFAULT NULL,
  `startDate` varchar(45) DEFAULT NULL,
  `releaseDate` varchar(45) DEFAULT NULL,
  `projectId` INT DEFAULT NULL,
  `self` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idVersion`),
  KEY `fk_Vesion_Project_idx` (`projectId`),
  CONSTRAINT `fk_Vesion_Project` FOREIGN KEY (`projectId`) REFERENCES `projectdetail` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version`
--

LOCK TABLES `version` WRITE;
/*!40000 ALTER TABLE `version` DISABLE KEYS */;
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'jira_extract'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-04 15:57:36
