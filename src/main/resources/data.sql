-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: localhost    Database: facemash
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'Susan',10),(2,'Miranda',3),(3,'Naomi',30),(4,'Isabelle',110),(5,'Nadia',23),(6,'Eleanor',3),(7,'Maryjane',53),(8,'Mercedes',40),(9,'Ashtyn',21),(10,'Priscilla',134),(11,'Marianna',32),(12,'Caroline',22),(13,'Mary',43),(14,'Abbie',54),(15,'Adrianna',39),(16,'Josephine',96),(17,'Marley',453),(18,'Shea',111),(19,'Justine',222),(20,'Alexa',242),(21,'Justice',354),(22,'Aspen',253),(23,'Mikayla',873),(24,'Marissa',543),(25,'Emely',175),(26,'Gretchen',765),(27,'Teagan',24),(28,'Maci',54),(29,'Natalia',44),(30,'Madalynn',129),(31,'Riley',68),(32,'Bethany',67),(33,'Priscilla',2),(34,'Dylan',1),(35,'Dayanara',43),(36,'Yaritza',33),(37,'Rayne',653),(38,'Tara',7543),(39,'Kyra',34),(40,'Shyann',412),(41,'Logan',544),(42,'Morgan',342),(43,'Anna',754),(44,'Yuliana',452),(45,'Kayla',342),(46,'Kaydence',334),(47,'Valerie',124),(48,'Kathy',234);
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_REGULAR');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'$2a$10$GWCPKw3xFjhTfUHTSWP1Juq8OQcOHSRIFDvUqki4gw.WH5H9C7QJS','test'),(2,'$2a$10$4Wzn7OrvvqV2vG447qJz1egeXyEGf0GsSjY3uRj5xCXOM.62ZVdd2','Adam9898');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `users_people`
--

LOCK TABLES `users_people` WRITE;
/*!40000 ALTER TABLE `users_people` DISABLE KEYS */;
INSERT INTO `users_people` VALUES (1,1),(2,2),(1,3),(1,4),(1,12),(2,12),(1,14),(1,31);
/*!40000 ALTER TABLE `users_people` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;