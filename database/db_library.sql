/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 8.0.31 : Database - db_library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_library`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminId` int NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `adminName` char(10) NOT NULL COMMENT '用户名',
  `adminPassword` char(10) NOT NULL COMMENT '密码',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `admin` */

insert  into `admin`(`adminId`,`adminName`,`adminPassword`) values 
(1,'zhy','17'),
(2,'tyx','38'),
(3,'zhf','28');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookId` int NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `bookName` char(10) NOT NULL COMMENT '书名',
  `author` char(10) NOT NULL COMMENT '作者',
  `category` char(10) NOT NULL COMMENT '类别',
  `state` char(10) NOT NULL COMMENT '状态',
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

insert  into `book`(`bookId`,`bookName`,`author`,`category`,`state`) values 
(1,'百年孤独','加西亚·马尔克斯','外国文学','借阅'),
(2,'查拉图斯特拉如是说','尼采','哲学','在馆'),
(3,'人类简史','尤瓦尔·赫拉利','历史','在馆'),
(4,'1984','乔治·奥威尔','外国文学','借阅'),
(5,'我与地坛','史铁生','中国文学','借阅');

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `readerID` int NOT NULL AUTO_INCREMENT COMMENT '读者编号',
  `readerName` varchar(10) NOT NULL DEFAULT '' COMMENT '读者姓名',
  `readerLimit` int NOT NULL DEFAULT '3' COMMENT '借书限额',
  `readerPassword` varchar(10) NOT NULL COMMENT '用户密码',
  `readerLend` int NOT NULL COMMENT '已借图书编号',
  PRIMARY KEY (`readerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `reader` */

insert  into `reader`(`readerID`,`readerName`,`readerLimit`,`readerPassword`,`readerLend`) values 
(1,'徐明轩',1,'123',1),
(2,'李如月',1,'321',5),
(3,'方文玉',1,'235',4),
(4,'张壬之',1,'268',3),
(5,'苏蓉蓉',0,'894',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
