-- MySQL dump 10.13  Distrib 5.6.13, for osx10.8 (x86_64)
--
-- Host: localhost    Database: ccs
-- ------------------------------------------------------
-- Server version	5.6.13

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
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `studentName` varchar(50) DEFAULT NULL,
  `attendComments` varchar(255) DEFAULT NULL,
  `behaviorComments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discipline`
--

LOCK TABLES `discipline` WRITE;
/*!40000 ALTER TABLE `discipline` DISABLE KEYS */;
INSERT INTO `discipline` VALUES (1,1,'Olivia Collins',NULL,'talking to neighbor during self study'),(2,1,'Olivia Collins','absent with excuse 10-29-13',NULL),(3,2,'Olivia Collins',NULL,'talking to neighbor during self study'),(4,2,'Olivia Collins','absent with excuse 10-29-13',NULL);
/*!40000 ALTER TABLE `discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gradeBook`
--

DROP TABLE IF EXISTS `gradeBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gradeBook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `studentName` varchar(50) DEFAULT NULL,
  `subject` varchar(20) DEFAULT NULL,
  `courseLevel` varchar(5) DEFAULT NULL,
  `assignment` varchar(20) DEFAULT NULL,
  `dueDate` date DEFAULT NULL,
  `totPts` double(5,2) DEFAULT NULL,
  `studentPts` double(5,2) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradeBook`
--

LOCK TABLES `gradeBook` WRITE;
/*!40000 ALTER TABLE `gradeBook` DISABLE KEYS */;
INSERT INTO `gradeBook` VALUES (1,1,'Olivia Collins','math','3rd','math practice','2013-10-31',25.00,23.50,'practice x2, x3, x4 tables'),(2,1,'Olivia Collins','math','3rd','math test','2013-11-08',100.00,85.00,'test over x2, x3, x4 tables'),(3,1,'Olivia Collins','reading','3rd','reading practice','2013-11-03',10.00,10.00,'read and complete worksheet to learn analysis and critical thinking skills'),(4,1,'Olivia Collins','reading','3rd','reading test','2013-11-10',25.00,24.00,'test over understanding of concepts in story'),(5,2,'Olivia Collins','math','3rd','math practice','2013-10-31',25.00,23.50,'practice x2, x3, x4 tables'),(6,2,'Olivia Collins','math','3rd','math test','2013-11-08',100.00,85.00,'test over x2, x3, x4 tables'),(7,2,'Olivia Collins','reading','3rd','reading practice','2013-11-03',10.00,10.00,'read and complete worksheet to learn analysis and critical thinking skills'),(8,2,'Olivia Collins','reading','3rd','reading test','2013-11-10',25.00,24.00,'test over understanding of concepts in story');
/*!40000 ALTER TABLE `gradeBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentInfo`
--

DROP TABLE IF EXISTS `studentInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentInfo` (
  `userId` int(11) NOT NULL,
  `studentName` varchar(50) DEFAULT NULL,
  `schoolName` varchar(100) DEFAULT NULL,
  `gradeLevel` varchar(10) DEFAULT NULL,
  `teacherName` varchar(50) DEFAULT NULL,
  `roomNum` int(3) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentInfo`
--

LOCK TABLES `studentInfo` WRITE;
/*!40000 ALTER TABLE `studentInfo` DISABLE KEYS */;
INSERT INTO `studentInfo` VALUES (1,'Olivia Collins','Harter Elementary','3rd','Stillwell',221),(2,'Olivia Collins','Clarendon Elementary','3rd','Stillwell',221);
/*!40000 ALTER TABLE `studentInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'diana','ccc7ae93ffd78ae7e4a89ef10696dc04'),(2,'david','04fbb1893040e770d99fc807eb081413');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-25 13:23:35
