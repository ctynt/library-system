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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

insert  into `book`(`bookId`,`bookName`,`author`,`category`,`state`) values 
(1,'百年孤独','加西亚·马尔克斯','外国文学','借阅'),
(2,'查拉图斯特拉如是说','尼采','哲学','借阅'),
(3,'人类简史','尤瓦尔·赫拉利','历史','借阅'),
(4,'1984','乔治·奥威尔','外国文学','借阅'),
(5,'我与地坛','史铁生','中国文学','借阅'),
(6,'霍乱时期的爱情','加西亚·马尔克斯','外国文学','在馆'),
(7,'西游记','吴承恩','中国文学','借阅'),
(8,'月亮和六便士','毛姆','外国文学','在馆'),
(9,'牛虻','伏尼契','外国文学','在馆'),
(10,'平凡的世界','路遥','中国文学','在馆'),
(11,'悲剧的诞生','尼采','哲学','在馆'),
(12,'明朝那些事儿','当年明月','历史','在馆'),
(13,'万历十五年','黄仁宇','历史','在馆'),
(14,'骆驼祥子','老舍','中国文学','在馆'),
(15,'活着','余华','中国文学','在馆'),
(16,'法社会学','尼克拉斯·卢曼','法律','在馆'),
(17,'理想国','柏拉图','哲学','在馆'),
(18,'精神现象学','黑格尔','哲学','在馆'),
(19,'忏悔录','奥古斯都','哲学','在馆'),
(20,'道德经','老子','哲学','在馆'),
(21,'形而上学','亚里士多德','哲学','在馆'),
(22,'资治通鉴','司马光','历史','在馆'),
(23,'国史大纲','钱穆','历史','在馆'),
(24,'人间失格','太宰治','外国文学','在馆'),
(25,'包法利夫人','福楼拜','外国文学','在馆'),
(26,'理智与情感','简·奥斯汀','外国文学','在馆'),
(27,'麦田里的守望者','塞林格','外国文学','在馆'),
(28,'红与黑','司汤达','外国文学','在馆');

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `borrowId` int NOT NULL AUTO_INCREMENT COMMENT '借阅编号',
  `bookId` int DEFAULT NULL COMMENT '书籍编号',
  `bookName` char(10) DEFAULT NULL COMMENT '书名',
  `readerId` int DEFAULT NULL COMMENT '用户编号',
  `readerName` char(10) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`borrowId`),
  KEY `bookId` (`bookId`),
  KEY `readerId` (`readerId`),
  CONSTRAINT `bookId` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `borrow` */

insert  into `borrow`(`borrowId`,`bookId`,`bookName`,`readerId`,`readerName`) values 
(1,1,'百年孤独',1,'徐明轩'),
(2,4,'1984',2,'李如月'),
(3,5,'我与地坛',3,'方文玉'),
(4,7,'西游记',4,'张壬之'),
(5,3,'人类简史',5,'苏蓉蓉'),
(6,2,'査拉图斯特拉如是说',6,'李太白');

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `readerID` int NOT NULL AUTO_INCREMENT COMMENT '读者编号',
  `readerName` varchar(10) NOT NULL DEFAULT '' COMMENT '读者姓名',
  `readerPassword` varchar(10) NOT NULL COMMENT '用户密码',
  `readerLend` int DEFAULT NULL COMMENT '已借读书编号',
  PRIMARY KEY (`readerID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `reader` */

insert  into `reader`(`readerID`,`readerName`,`readerPassword`,`readerLend`) values 
(1,'徐明轩','123',1),
(2,'李如月','321',4),
(3,'方文玉','235',5),
(4,'张壬之','268',7),
(5,'苏蓉蓉','894',3),
(6,'李太白','666',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
