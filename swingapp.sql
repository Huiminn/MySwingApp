/*
Navicat MySQL Data Transfer

Source Server         : localsql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : swingapp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-27 00:46:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('7', 'Harry Potter', 'J.K.Rowling', '2017-05-22 23:12:29', '2017-05-25 23:21:42', 'In');
INSERT INTO `book` VALUES ('11', 'ICT Infrastructure', 'Willian', '2017-05-22 23:12:48', '2017-05-24 16:50:46', 'In');
INSERT INTO `book` VALUES ('17', 'The Wonderful  Wizard of Oz', 'John', '2017-05-22 23:02:16', '2017-05-24 03:22:30', 'In');
INSERT INTO `book` VALUES ('18', 'The Little Prince', 'Antoine', '2017-05-22 23:02:16', '2017-05-24 03:22:49', 'In');

-- ----------------------------
-- Table structure for borrow_record
-- ----------------------------
DROP TABLE IF EXISTS `borrow_record`;
CREATE TABLE `borrow_record` (
  `borrowid` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `borrow_time` timestamp NULL DEFAULT NULL,
  `return_time` timestamp NULL DEFAULT NULL,
  `is_history_record` tinyint(10) DEFAULT NULL,
  PRIMARY KEY (`borrowid`),
  KEY `studentid` (`studentid`),
  KEY `bookid` (`bookid`),
  CONSTRAINT `borrow_record_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `borrow_record_ibfk_2` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_record
-- ----------------------------
INSERT INTO `borrow_record` VALUES ('60', '9', '11', 'returned', '2017-05-26 22:53:17', '2017-05-26 23:44:48', '1');
INSERT INTO `borrow_record` VALUES ('61', '9', '17', 'returned', '2017-05-26 22:53:20', '2017-05-26 22:54:47', '1');
INSERT INTO `borrow_record` VALUES ('62', '9', '7', 'returned', '2017-05-26 22:54:07', '2017-05-26 23:44:39', '1');
INSERT INTO `borrow_record` VALUES ('63', '9', '18', 'returned', '2017-05-26 22:54:09', '2017-05-26 22:54:52', '1');
INSERT INTO `borrow_record` VALUES ('64', '6', '7', 'returned', '2017-05-27 00:30:04', '2017-05-27 00:34:11', '1');
INSERT INTO `borrow_record` VALUES ('65', '6', '17', 'returned', '2017-05-27 00:30:06', '2017-05-27 00:44:23', '1');
INSERT INTO `borrow_record` VALUES ('66', '8', '11', 'returned', '2017-05-27 00:30:23', '2017-05-27 00:43:29', '1');
INSERT INTO `borrow_record` VALUES ('67', '8', '18', 'returned', '2017-05-27 00:30:25', '2017-05-27 00:44:03', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'Student');
INSERT INTO `role` VALUES ('2', 'Admin');

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `roleid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '6');
INSERT INTO `role_user` VALUES ('1', '11');
INSERT INTO `role_user` VALUES ('1', '12');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentid` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `studentNo` varchar(11) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2', 'Jarby', '142774', '+8615814496054', 'jiangbo720156@gmail.com', 'Tianhe district,Guangzhou city,China', '2017-05-23 11:59:31', '2017-05-26 10:09:04');
INSERT INTO `student` VALUES ('6', 'Rose', '142776', '18947832765', '7832676@qq.com', 'zhujiang new town,Guangzhou city,China', '2017-05-23 15:59:46', '2017-05-26 10:09:28');
INSERT INTO `student` VALUES ('7', 'Tom', '142778', '18947832098', '7832676@qq.com', 'zhujiang new town,Guangzhou city,China', '2017-05-23 16:00:03', '2017-05-23 16:00:03');
INSERT INTO `student` VALUES ('8', 'Adison', '142780', '189478323334', '7832676@qq.com', 'zhujiang new town,Guangzhou city,China', '2017-05-23 16:00:30', '2017-05-23 16:00:30');
INSERT INTO `student` VALUES ('9', 'Lucy', '142800', '13452768902', '86766734@qq.com', 'Shenzhen city,China', '2017-05-23 16:01:27', '2017-05-26 14:19:54');
INSERT INTO `student` VALUES ('10', 'Lilei', '142801', '13452768987', '867667784@qq.com', 'Chengguan villiage,Beijing city,China', '2017-05-23 16:02:02', '2017-05-23 16:02:02');
INSERT INTO `student` VALUES ('11', 'zhangsan', '142802', '13452768967', '867667784@qq.com', 'street,Changsha city,China', '2017-05-23 16:02:43', '2017-05-23 16:02:43');
INSERT INTO `student` VALUES ('12', 'Hui Liu', '142803', '13452768364', '86723784@qq.com', 'street,Turku,Finnand', '2017-05-23 16:03:41', '2017-05-23 16:03:41');
INSERT INTO `student` VALUES ('13', 'Kobe', '142804', '1345276897', '86723784@qq.com', 'Californior, US', '2017-05-23 16:04:33', '2017-05-23 16:04:33');
INSERT INTO `student` VALUES ('14', 'Jiangbo Dai', '178446', '15814496053', '794415244@qq.com', 'Jipu island,Tailand', '2017-05-23 16:08:39', '2017-05-23 16:08:39');
INSERT INTO `student` VALUES ('15', 'Lucas', '178447', '18956784325', '794415244@qq.com', 'Disney park, HongKong', '2017-05-23 16:10:19', '2017-05-23 16:10:19');
INSERT INTO `student` VALUES ('16', 'Andy', '178448', '18956790789', '794415244@qq.com', 'Tokyo,Japan', '2017-05-23 16:11:10', '2017-05-23 16:11:10');
INSERT INTO `student` VALUES ('17', 'Andy', '178449', '18956790789', '794415244@qq.com', 'Seoul,Korea', '2017-05-23 16:11:51', '2017-05-23 16:11:51');
INSERT INTO `student` VALUES ('18', 'Jackson', '178450', '18956796666', '794415244@qq.com', 'Porsgrunn,Oslo, Norway', '2017-05-23 16:12:31', '2017-05-23 16:12:31');
INSERT INTO `student` VALUES ('19', 'Jackson', '178451', '18956795555', '794415244@qq.com', 'Porsgrunn,Oslo, Norway', '2017-05-23 16:12:42', '2017-05-23 16:12:42');
INSERT INTO `student` VALUES ('20', 'Jackson', '178452', '18956795555', '794415244@qq.com', 'Porsgrunn,Oslo, Norway', '2017-05-23 16:12:46', '2017-05-23 16:12:46');
INSERT INTO `student` VALUES ('21', 'Jackson', '178453', '18956795555', '794415244@qq.com', 'Porsgrunn,Oslo, Norway', '2017-05-23 16:12:50', '2017-05-23 16:12:50');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `studentNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'Jarby', 'e10adc3949ba59abbe56e057f20f883e', 'Admin', null);
INSERT INTO `user` VALUES ('6', 'Lucy', 'e10adc3949ba59abbe56e057f20f883e', 'Student', '142800');
INSERT INTO `user` VALUES ('11', 'aaa', 'e10adc3949ba59abbe56e057f20f883e', 'Student', '142776');
INSERT INTO `user` VALUES ('12', 'uuu', 'e10adc3949ba59abbe56e057f20f883e', 'Student', '142780');
