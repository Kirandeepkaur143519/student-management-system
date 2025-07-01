CREATE DATABASE  IF NOT EXISTS `studentdb`;
USE `studentdb`;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `roll_no` varchar(45) DEFAULT NULL,
  `course` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `students`
--

INSERT INTO `students` VALUES 
	(1,'Leslie','BS21BCS001','B.Tech CSE 4TH year'),
	(2,'Leslie','BS21BCS002','B.Tech CSE 4TH year'),
	(3,'Leslie','BS21BCS004','B.Tech CSE 4TH year'),
	(4,'Leslie','BS21BCS007','B.Tech CSE 4TH year'),
	(5,'Leslie','BS21BCS009','B.Tech CSE 4TH year');
	

