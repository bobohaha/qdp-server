-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: tester
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tester_device_info`
--

DROP TABLE IF EXISTS `tester_device_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tester_device_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deviceid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` int NOT NULL DEFAULT '0',
  `version` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_device_info`
--

LOCK TABLES `tester_device_info` WRITE;
/*!40000 ALTER TABLE `tester_device_info` DISABLE KEYS */;
INSERT INTO `tester_device_info` VALUES (13,'CLB0219523000125','HUAWEI P20','huaweip20',0,'10',1,'邹芳波','2020-10-15 13:52:15','2020-09-03 02:56:39'),(14,'test','test','test',0,'10',1,NULL,'2020-11-12 12:48:47','2020-11-12 12:48:47');
/*!40000 ALTER TABLE `tester_device_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tester_ibanyu_business`
--

DROP TABLE IF EXISTS `tester_ibanyu_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tester_ibanyu_business` (
  `id` int unsigned NOT NULL,
  `business` varchar(255) NOT NULL,
  `businessname` varchar(255) NOT NULL,
  `branchname` varchar(255) NOT NULL,
  `isfinish` int NOT NULL DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_ibanyu_business`
--

LOCK TABLES `tester_ibanyu_business` WRITE;
/*!40000 ALTER TABLE `tester_ibanyu_business` DISABLE KEYS */;
INSERT INTO `tester_ibanyu_business` VALUES (1,'junior','伴鱼少儿英语','junitor',0,NULL),(2,'huiben','伴鱼绘本','huiben',0,NULL),(3,'teaching','教学中台','teaching',1,NULL);
/*!40000 ALTER TABLE `tester_ibanyu_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tester_ibanyu_business_model`
--

DROP TABLE IF EXISTS `tester_ibanyu_business_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tester_ibanyu_business_model` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `modelid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `model` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_ibanyu_business_model`
--

LOCK TABLES `tester_ibanyu_business_model` WRITE;
/*!40000 ALTER TABLE `tester_ibanyu_business_model` DISABLE KEYS */;
INSERT INTO `tester_ibanyu_business_model` VALUES (1,'AI','精读课','junitor',1,NULL),(2,'test','test','test',1,NULL);
/*!40000 ALTER TABLE `tester_ibanyu_business_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tester_quality_report`
--

DROP TABLE IF EXISTS `tester_quality_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tester_quality_report` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `type` int NOT NULL DEFAULT '0',
  `site` varchar(255) NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_quality_report`
--

LOCK TABLES `tester_quality_report` WRITE;
/*!40000 ALTER TABLE `tester_quality_report` DISABLE KEYS */;
INSERT INTO `tester_quality_report` VALUES (1,'test',0,'wwww.baidu.com',1,'2020-11-05 13:53:01','2020-11-05 13:53:01'),(2,'test1',0,'wwww.baidu.com',1,'2020-11-09 13:16:42','2020-11-09 13:16:42'),(3,'HUAWEI P20 UI Auto Test Report',0,'http://localhost:8081/2020-11-09-21:52/report',1,'2020-11-09 13:52:24','2020-11-09 13:52:24'),(4,'HUAWEI P20 UI Auto Test Report',0,'http://localhost:8081/2020-11-10-10-35/report',1,'2020-11-10 02:35:29','2020-11-10 02:35:29'),(5,'HUAWEI P20 UI Auto Test Report',0,'http://localhost:8081/2020-11-10-10-47/report',1,'2020-11-10 02:47:19','2020-11-10 02:47:19'),(6,'HUAWEI P20 UI Auto Test Report',0,'http://localhost:8081/report/2020-11-10-10-56',1,'2020-11-10 02:56:08','2020-11-10 02:56:08');
/*!40000 ALTER TABLE `tester_quality_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tester_quality_statistics`
--

DROP TABLE IF EXISTS `tester_quality_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tester_quality_statistics` (
  `id` int NOT NULL,
  `business` int NOT NULL,
  `week` int NOT NULL DEFAULT '1',
  `requirenum` int NOT NULL,
  `allbugnum` int NOT NULL,
  `majorbugnum` int NOT NULL,
  `generalbugnum` int NOT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_quality_statistics`
--

LOCK TABLES `tester_quality_statistics` WRITE;
/*!40000 ALTER TABLE `tester_quality_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `tester_quality_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tester_user`
--

DROP TABLE IF EXISTS `tester_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tester_user` (
  `uid` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `password` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_user`
--

LOCK TABLES `tester_user` WRITE;
/*!40000 ALTER TABLE `tester_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tester_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-11 15:50:51
