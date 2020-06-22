/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.27 : Database - cinema
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cinema` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cinema`;

/*Table structure for table `card` */

CREATE TABLE `card` (
  `cardid` varchar(50) NOT NULL,
  `memberid` varchar(50) DEFAULT NULL,
  `balance` int(11) DEFAULT '0' COMMENT '人民币余额',
  `integral` int(11) DEFAULT '0' COMMENT '积分',
  `lose` int(1) DEFAULT '0' COMMENT '1为丢失，0为未丢失',
  PRIMARY KEY (`cardid`),
  KEY `username` (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `card` */

insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('0161c880e711464c9bbb5f699f292e23','567ced95cf1541bc94ccb3dfa767b53f',100,0,1);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('0967a8dd2cc04fc887cb69f35ae831fd','567ced95cf1541bc94ccb3dfa767b53f',6501,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('09961c29e8494649b29851388ef440fb','569b67f5c340462f9c343a98ab6e64db',1035,10025,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('0b5705e0ed8f4b6fafc4126147b7d6fc','4e0553653d27447896440c4a16eda042',999,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('4f59de19d3534abfb948f4c9a38e53dc','569b67f5c340462f9c343a98ab6e64db',1231,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('8b06e3dcd4334c368d666081f666b500','567ced95cf1541bc94ccb3dfa767b53f',2551,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('8c0328f28b60400f983a806d67f705cc','288bced48b0243bdaefb4d602fa30c62',4910,3934,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('9a6a2bfb4e95421d92774444c229ddfd','567ced95cf1541bc94ccb3dfa767b53f',100,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('9c535625f7cc477eb69921849a40871d','edabd56c406449a0ab105bb7bfe92346',1314250,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('a81128bc8be54aec84b0dadf0b1d6fe1','4e0553653d27447896440c4a16eda042',0,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('aa20dce977254b599508979d2e667884','567ced95cf1541bc94ccb3dfa767b53f',100,0,1);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('aaa3dcc485144a44ae779d614a808ad0','4e0553653d27447896440c4a16eda042',0,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('b27a7b32a0c44561abe8104977814152','edabd56c406449a0ab105bb7bfe92346',0,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('ba0977e470ba4f6fb48b8c157da98b4d','edc5c2070d154561b8d5aa94ded0ccd9',9900,25,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('cb09d3ef48f24a14a58cde8ab4bc0a6f','569b67f5c340462f9c343a98ab6e64db',0,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('d5369d546dd94b68a1ae652b54f55ff7','567ced95cf1541bc94ccb3dfa767b53f',0,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('f0cc8a64e9744fac89ec05c99b9a13e1','569b67f5c340462f9c343a98ab6e64db',0,0,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('f226a06fed11438882f0d3cffd0ece4f','567ced95cf1541bc94ccb3dfa767b53f',180,302,0);
insert  into `card`(`cardid`,`memberid`,`balance`,`integral`,`lose`) values ('fb0838a8d15e4306aff60bde281b2f27','94ce3d7b848649edb01a1e2de6b7e143',1314,0,0);

/*Table structure for table `manager` */

CREATE TABLE `manager` (
  `managerid` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `role` varchar(10) DEFAULT '操作员',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`managerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`managerid`,`password`,`name`,`role`,`time`) values ('admin','123123','张三','操作员','2020-06-11 11:06:23');

/*Table structure for table `member` */

CREATE TABLE `member` (
  `memberid` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('2525943c9c0f4f6a9530262628df56db','123123','梅超风','女','2020-06-01');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('288bced48b0243bdaefb4d602fa30c62','123123','小智','男','2020-03-05');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('39d86e911df04588bc2c75407318465f','123123','胖大海','男','2020-04-02');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('467e64ae0d1d440495be860179d5be78','123123','慕容复','男','2020-04-28');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('4e0553653d27447896440c4a16eda042','123123','令狐冲','男','2020-04-03');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('567ced95cf1541bc94ccb3dfa767b53f','123123','乔峰','男','2020-04-09');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('569b67f5c340462f9c343a98ab6e64db','123123','张三丰','男','2020-04-23');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('64bfda46ead04696978961f399c37e6d','123123','白晶晶','女','2020-04-30');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('94ce3d7b848649edb01a1e2de6b7e143','123123','段誉','男','2020-04-04');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('a15763ae3fd9431cb7c4542d2122d7a1','123123','至尊宝','男','2020-04-01');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('a58b8b60a698446292df3a79d16799c6','123123','风云无忌','男','2020-04-29');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('edabd56c406449a0ab105bb7bfe92346','123123','紫霞','女','2020-04-01');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('edc5c2070d154561b8d5aa94ded0ccd9','123123','宁采臣','男','2020-04-30');
insert  into `member`(`memberid`,`password`,`name`,`sex`,`birthday`) values ('fcac671e85354bbc8086a94102d8c170','123123','扫地僧','男','2020-01-01');

/*Table structure for table `movie` */

CREATE TABLE `movie` (
  `movieid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(49) DEFAULT NULL,
  `price` int(11) DEFAULT '0',
  `integral` int(11) DEFAULT '0',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`movieid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `movie` */

insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (1,'龙争虎斗',90,20,'2020-04-30 22:31:07');
insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (3,'雪国列车',99,99,'2020-04-25 16:49:02');
insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (4,'饥饿站台',1000,85,'2020-04-25 16:53:36');
insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (6,'西虹市首富',100,10,'2020-04-25 17:04:13');
insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (7,'让子弹飞',90,12,'2020-04-25 18:15:05');
insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (8,'雪山飞狐',50,10,'2020-04-29 20:55:11');
insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (9,'黄金甲',99,25,'2020-04-29 20:56:11');
insert  into `movie`(`movieid`,`name`,`price`,`integral`,`time`) values (11,'回到明朝当王爷',200,88,'2020-04-30 22:22:20');

/*Table structure for table `record` */

CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(50) DEFAULT NULL,
  `value` int(11) DEFAULT '0',
  `time` datetime DEFAULT NULL,
  `spendtype` int(1) DEFAULT '0' COMMENT '0为人民币，1为积分',
  PRIMARY KEY (`id`),
  KEY `cardid` (`cardid`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`cardid`) REFERENCES `card` (`cardid`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

/*Data for the table `record` */

insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (1,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-21 22:55:06',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (2,'f226a06fed11438882f0d3cffd0ece4f',1000,'2020-04-20 22:56:58',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (3,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-23 23:01:17',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (4,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-24 23:01:17',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (5,'f226a06fed11438882f0d3cffd0ece4f',100,'2020-04-25 23:01:38',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (6,'f226a06fed11438882f0d3cffd0ece4f',-1000,'2020-04-21 23:02:52',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (7,'f226a06fed11438882f0d3cffd0ece4f',85,'2020-04-20 23:02:52',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (8,'f226a06fed11438882f0d3cffd0ece4f',-1000,'2020-04-22 23:03:40',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (9,'f226a06fed11438882f0d3cffd0ece4f',85,'2020-04-24 23:03:40',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (10,'f226a06fed11438882f0d3cffd0ece4f',1000,'2020-04-25 23:03:51',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (11,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-22 23:10:03',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (12,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-24 23:10:03',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (13,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-21 23:11:26',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (14,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-20 23:11:26',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (15,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-22 23:12:29',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (16,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-25 23:12:29',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (17,'f226a06fed11438882f0d3cffd0ece4f',1000,'2020-04-24 23:14:02',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (18,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:14:06',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (19,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:14:06',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (20,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:14:25',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (21,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:14:25',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (22,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:14:39',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (23,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:14:39',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (24,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:15:15',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (25,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:15:15',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (26,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:15:37',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (27,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:15:37',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (28,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:16:25',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (29,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:16:25',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (30,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:20:03',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (31,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:20:03',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (32,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:21:41',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (33,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:21:41',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (34,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:22:52',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (35,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:22:52',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (36,'f226a06fed11438882f0d3cffd0ece4f',-90,'2020-04-29 23:23:27',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (37,'f226a06fed11438882f0d3cffd0ece4f',200,'2020-04-29 23:23:27',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (38,'f226a06fed11438882f0d3cffd0ece4f',1,'2020-04-30 00:00:52',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (39,'8b06e3dcd4334c368d666081f666b500',500,'2020-04-30 15:00:10',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (40,'8b06e3dcd4334c368d666081f666b500',-99,'2020-04-20 15:00:16',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (41,'8b06e3dcd4334c368d666081f666b500',99,'2020-04-30 15:00:16',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (42,'8b06e3dcd4334c368d666081f666b500',-99,'2020-04-30 17:06:11',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (43,'f226a06fed11438882f0d3cffd0ece4f',-2901,'2020-04-20 17:06:11',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (44,'8b06e3dcd4334c368d666081f666b500',-90,'2020-04-30 17:07:09',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (45,'8b06e3dcd4334c368d666081f666b500',200,'2020-04-30 17:07:09',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (46,'8b06e3dcd4334c368d666081f666b500',-90,'2020-04-30 17:07:18',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (47,'8b06e3dcd4334c368d666081f666b500',200,'2020-04-30 17:07:18',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (48,'8b06e3dcd4334c368d666081f666b500',-90,'2020-04-30 17:07:23',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (49,'8b06e3dcd4334c368d666081f666b500',200,'2020-04-21 17:07:23',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (50,'8b06e3dcd4334c368d666081f666b500',-90,'2020-04-30 17:07:32',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (51,'8b06e3dcd4334c368d666081f666b500',200,'2020-04-22 17:07:32',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (52,'8b06e3dcd4334c368d666081f666b500',5000,'2020-04-23 17:08:30',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (53,'8b06e3dcd4334c368d666081f666b500',-90,'2020-04-30 17:08:33',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (54,'8b06e3dcd4334c368d666081f666b500',200,'2020-04-30 17:08:34',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (55,'8b06e3dcd4334c368d666081f666b500',-1000,'2020-04-30 17:08:57',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (56,'f226a06fed11438882f0d3cffd0ece4f',-400,'2020-04-30 17:08:57',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (57,'8b06e3dcd4334c368d666081f666b500',-100,'2020-04-30 17:11:31',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (58,'8b06e3dcd4334c368d666081f666b500',10000,'2020-04-30 17:11:31',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (59,'8b06e3dcd4334c368d666081f666b500',-3000,'2020-04-30 17:11:39',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (60,'8b06e3dcd4334c368d666081f666b500',-500,'2020-04-30 17:14:01',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (61,'8b06e3dcd4334c368d666081f666b500',0,'2020-04-30 17:14:10',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (62,'8b06e3dcd4334c368d666081f666b500',-3000,'2020-04-30 17:14:44',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (63,'8b06e3dcd4334c368d666081f666b500',-100,'2020-04-30 17:15:01',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (64,'8b06e3dcd4334c368d666081f666b500',10000,'2020-04-30 17:15:01',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (65,'8b06e3dcd4334c368d666081f666b500',-500,'2020-04-21 17:15:08',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (66,'8b06e3dcd4334c368d666081f666b500',-500,'2020-04-30 17:16:08',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (67,'8b06e3dcd4334c368d666081f666b500',-500,'2020-04-30 17:16:28',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (68,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-22 17:16:59',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (69,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-23 17:23:13',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (70,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-24 17:23:32',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (71,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-30 17:23:44',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (72,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-30 17:23:47',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (73,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-30 17:23:54',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (74,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-30 17:24:42',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (75,'8b06e3dcd4334c368d666081f666b500',-1400,'2020-04-30 17:31:40',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (76,'8b06e3dcd4334c368d666081f666b500',-500,'2020-04-30 17:32:01',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (77,'0161c880e711464c9bbb5f699f292e23',100,'2020-04-30 17:33:59',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (78,'0967a8dd2cc04fc887cb69f35ae831fd',100,'2020-04-30 17:34:02',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (79,'9a6a2bfb4e95421d92774444c229ddfd',100,'2020-04-30 17:34:04',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (80,'aa20dce977254b599508979d2e667884',100,'2020-04-30 17:34:14',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (81,'ba0977e470ba4f6fb48b8c157da98b4d',9999,'2020-04-21 17:40:42',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (82,'ba0977e470ba4f6fb48b8c157da98b4d',-99,'2020-04-30 17:40:48',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (83,'ba0977e470ba4f6fb48b8c157da98b4d',25,'2020-04-30 17:40:48',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (84,'0b5705e0ed8f4b6fafc4126147b7d6fc',111,'2020-04-30 17:55:00',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (85,'09961c29e8494649b29851388ef440fb',1234,'2020-04-22 21:20:18',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (86,'09961c29e8494649b29851388ef440fb',-100,'2020-04-30 21:20:24',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (87,'09961c29e8494649b29851388ef440fb',10000,'2020-04-30 21:20:24',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (88,'09961c29e8494649b29851388ef440fb',-99,'2020-04-30 21:20:34',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (89,'09961c29e8494649b29851388ef440fb',25,'2020-04-30 21:20:34',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (91,'8b06e3dcd4334c368d666081f666b500',-1000,'2020-04-30 22:00:58',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (92,'8b06e3dcd4334c368d666081f666b500',85,'2020-04-30 22:00:58',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (93,'8b06e3dcd4334c368d666081f666b500',-1000,'2020-04-30 22:01:03',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (94,'8b06e3dcd4334c368d666081f666b500',85,'2020-04-30 22:01:03',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (96,'8b06e3dcd4334c368d666081f666b500',-10000,'2020-04-30 22:03:09',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (97,'8b06e3dcd4334c368d666081f666b500',50,'2020-04-30 22:03:09',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (98,'8b06e3dcd4334c368d666081f666b500',-10000,'2020-04-30 22:03:11',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (99,'8b06e3dcd4334c368d666081f666b500',50,'2020-04-30 22:03:11',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (100,'8b06e3dcd4334c368d666081f666b500',-10000,'2020-04-24 22:03:15',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (101,'8b06e3dcd4334c368d666081f666b500',50,'2020-04-30 22:03:15',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (102,'8b06e3dcd4334c368d666081f666b500',-10000,'2020-04-30 22:03:18',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (103,'8b06e3dcd4334c368d666081f666b500',50,'2020-04-30 22:03:18',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (104,'8b06e3dcd4334c368d666081f666b500',-10000,'2020-04-30 22:03:21',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (105,'8b06e3dcd4334c368d666081f666b500',50,'2020-04-30 22:03:21',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (106,'8b06e3dcd4334c368d666081f666b500',-200,'2020-04-30 22:22:28',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (107,'8b06e3dcd4334c368d666081f666b500',88,'2020-04-30 22:22:28',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (108,'fb0838a8d15e4306aff60bde281b2f27',1314,'2020-04-30 22:25:11',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (109,'8b06e3dcd4334c368d666081f666b500',0,'2020-04-30 22:29:52',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (110,'8c0328f28b60400f983a806d67f705cc',5000,'2020-04-30 22:30:33',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (111,'8c0328f28b60400f983a806d67f705cc',-90,'2020-04-30 22:30:41',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (112,'8c0328f28b60400f983a806d67f705cc',20000,'2020-04-30 22:30:41',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (113,'8c0328f28b60400f983a806d67f705cc',-16066,'2020-04-30 22:30:51',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (114,'8c0328f28b60400f983a806d67f705cc',0,'2020-04-30 22:31:20',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (115,'0967a8dd2cc04fc887cb69f35ae831fd',6000,'2020-04-30 22:33:18',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (116,'0967a8dd2cc04fc887cb69f35ae831fd',-99,'2020-05-01 00:44:52',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (117,'0967a8dd2cc04fc887cb69f35ae831fd',25,'2020-05-01 00:44:52',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (118,'0967a8dd2cc04fc887cb69f35ae831fd',-25,'2020-05-01 10:47:44',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (119,'8b06e3dcd4334c368d666081f666b500',-475,'2020-05-01 10:47:44',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (120,'0967a8dd2cc04fc887cb69f35ae831fd',500,'2020-05-01 13:37:18',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (121,'4f59de19d3534abfb948f4c9a38e53dc',1231,'2020-05-01 13:37:30',0);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (122,'0967a8dd2cc04fc887cb69f35ae831fd',0,'2020-06-11 11:12:14',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (123,'8b06e3dcd4334c368d666081f666b500',-333,'2020-06-11 11:12:14',1);
insert  into `record`(`id`,`cardid`,`value`,`time`,`spendtype`) values (124,'f226a06fed11438882f0d3cffd0ece4f',-167,'2020-06-11 11:12:14',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
