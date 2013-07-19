# MySQL-Front 5.1  (Build 3.57)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: vote
# ------------------------------------------------------
# Server version 5.1.39-community

#
# Source for table candidate
#

DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET gb2312 DEFAULT NULL,
  `sex` varchar(8) CHARACTER SET gb2312 DEFAULT '男' COMMENT '性别，默认 男',
  `idNo` varchar(30) CHARACTER SET gb2312 NOT NULL DEFAULT '' COMMENT '学号',
  `password` varchar(50) CHARACTER SET gb2312 NOT NULL DEFAULT '',
  `voteNum` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '获得的票数',
  `photoPath` varchar(100) CHARACTER SET gb2312 DEFAULT 'photo/photo.png',
  `tel` varchar(20) CHARACTER SET gb2312 DEFAULT NULL,
  `nation` varchar(20) CHARACTER SET gb2312 DEFAULT NULL,
  `poliStatus` varchar(20) CHARACTER SET gb2312 DEFAULT NULL,
  `workPlace` varchar(50) CHARACTER SET gb2312 DEFAULT NULL,
  `resume` varchar(1000) CHARACTER SET gb2312 DEFAULT NULL,
  `other` varchar(1000) CHARACTER SET gb2312 DEFAULT NULL,
  `eduBgd` varchar(20) CHARACTER SET gb2312 DEFAULT NULL,
  `position` varchar(50) CHARACTER SET gb2312 DEFAULT NULL,
  `birthday` varchar(20) CHARACTER SET gb2312 NOT NULL DEFAULT '1990-11-11',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='候选人表';

#
# Dumping data for table candidate
#
LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;

/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table manager
#

DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) CHARACTER SET gb2312 NOT NULL DEFAULT 'administrator',
  `password` varchar(50) CHARACTER SET gb2312 NOT NULL DEFAULT '123',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='the manger table of vote system';

#
# Dumping data for table manager
#
LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;

INSERT INTO `manager` VALUES (3,'admin','admin');
INSERT INTO `manager` VALUES (4,'upc_vote_admin','back_acount_upc2011');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table voter
#

DROP TABLE IF EXISTS `voter`;
CREATE TABLE `voter` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `idNo` varchar(20) CHARACTER SET gb2312 NOT NULL DEFAULT '0' COMMENT '存放身份证信息',
  `isVote` int(2) DEFAULT '0' COMMENT '是否已经投票,0表示没有，1表示已投',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=gbk COMMENT='存放投票人的身份证信息';

#
# Dumping data for table voter
#
LOCK TABLES `voter` WRITE;
/*!40000 ALTER TABLE `voter` DISABLE KEYS */;

/*!40000 ALTER TABLE `voter` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
