/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : studentsystem

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2021-03-28 16:25:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `Classid` int(11) NOT NULL AUTO_INCREMENT,
  `Classname` varchar(20) NOT NULL,
  `Classnumber` int(11) NOT NULL,
  PRIMARY KEY (`Classid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', 'One', '30');
INSERT INTO `classes` VALUES ('3', 'Four', '75');
INSERT INTO `classes` VALUES ('4', '经济1班', '45');
INSERT INTO `classes` VALUES ('5', '经济2班', '55');
INSERT INTO `classes` VALUES ('6', '经济3班', '45');
INSERT INTO `classes` VALUES ('7', '软件工程1班', '45');
INSERT INTO `classes` VALUES ('8', '经济10班', '56');
INSERT INTO `classes` VALUES ('9', '经济11班', '56');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(30) NOT NULL,
  `major` varchar(20) NOT NULL,
  `grade` varchar(20) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'Java', '计算机', '第一学期');
INSERT INTO `course` VALUES ('2', 'C', '计算机', '第一学期');
INSERT INTO `course` VALUES ('3', 'C++', '计算机', '第二学期');
INSERT INTO `course` VALUES ('4', 'English', '英语', '第一学期');
INSERT INTO `course` VALUES ('5', 'USEnglish', '英语', '第一学期');
INSERT INTO `course` VALUES ('6', 'English1', '英语', '第一学期');
INSERT INTO `course` VALUES ('7', 'UI', '计算机', '第二学期');
INSERT INTO `course` VALUES ('8', 'Htnl5', '计算机', '第三学期');
INSERT INTO `course` VALUES ('12', '金融学', '商贸', '第一学期');
INSERT INTO `course` VALUES ('13', '人类简史', '计算机', '第一学期');
INSERT INTO `course` VALUES ('14', '资本论', '商贸', '第一学期');

-- ----------------------------
-- Table structure for crousestatus
-- ----------------------------
DROP TABLE IF EXISTS `crousestatus`;
CREATE TABLE `crousestatus` (
  `status` char(2) NOT NULL,
  `test` char(2) NOT NULL,
  PRIMARY KEY (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crousestatus
-- ----------------------------
INSERT INTO `crousestatus` VALUES ('1', '1');

-- ----------------------------
-- Table structure for governor
-- ----------------------------
DROP TABLE IF EXISTS `governor`;
CREATE TABLE `governor` (
  `governorid` int(11) NOT NULL AUTO_INCREMENT,
  `pwd` varchar(20) NOT NULL,
  `truename` varchar(30) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  PRIMARY KEY (`governorid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of governor
-- ----------------------------
INSERT INTO `governor` VALUES ('1', 'abc', 'tzs', '1');
INSERT INTO `governor` VALUES ('2', 'abc', 'tzs', '3');
INSERT INTO `governor` VALUES ('3', 'lebron', 'tzs', '5');

-- ----------------------------
-- Table structure for photoverification
-- ----------------------------
DROP TABLE IF EXISTS `photoverification`;
CREATE TABLE `photoverification` (
  `pid` int(200) NOT NULL AUTO_INCREMENT,
  `describes` varchar(30) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photoverification
-- ----------------------------
INSERT INTO `photoverification` VALUES ('0', '菊花', 'Code/1.jpg');
INSERT INTO `photoverification` VALUES ('1', '下雨', 'Code/4.jpg');
INSERT INTO `photoverification` VALUES ('2', '下雪', 'Code/3.jpg');
INSERT INTO `photoverification` VALUES ('3', '喷火龙', 'Code/2.jpg');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `stuid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `status` char(2) NOT NULL,
  KEY `stuid` (`stuid`),
  KEY `cid` (`cid`),
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`stuid`) REFERENCES `student` (`stuid`) ON DELETE CASCADE,
  CONSTRAINT `sc_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('6', '4', 'One', '1');
INSERT INTO `sc` VALUES ('8', '4', 'One', '1');
INSERT INTO `sc` VALUES ('12', '4', 'One', '1');
INSERT INTO `sc` VALUES ('2', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('2', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('3', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('3', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('4', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('4', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('7', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('7', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('11', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('11', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('13', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('13', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('16', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('16', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('18', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('18', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('19', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('19', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('22', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('22', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('23', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('23', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('2', '4', '第一学期', '0');
INSERT INTO `sc` VALUES ('2', '12', '第一学期', '0');
INSERT INTO `sc` VALUES ('28', '1', '第一学期', '1');
INSERT INTO `sc` VALUES ('28', '2', '第一学期', '1');
INSERT INTO `sc` VALUES ('28', '13', '第一学期', '1');
INSERT INTO `sc` VALUES ('25', '1', '第二学期', '0');

-- ----------------------------
-- Table structure for schoolroom
-- ----------------------------
DROP TABLE IF EXISTS `schoolroom`;
CREATE TABLE `schoolroom` (
  `Scid` int(11) NOT NULL AUTO_INCREMENT,
  `RoomAddress` varchar(30) NOT NULL,
  `Management` varchar(20) NOT NULL,
  PRIMARY KEY (`Scid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schoolroom
-- ----------------------------
INSERT INTO `schoolroom` VALUES ('4', '教学楼二楼', '小红');
INSERT INTO `schoolroom` VALUES ('5', '教学楼二楼', '小白');

-- ----------------------------
-- Table structure for scope
-- ----------------------------
DROP TABLE IF EXISTS `scope`;
CREATE TABLE `scope` (
  `scope` int(11) DEFAULT NULL,
  `cid` int(11) NOT NULL,
  `grade` varchar(20) NOT NULL,
  `stuid` int(11) NOT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `status` int(2) NOT NULL,
  UNIQUE KEY `cts` (`cid`,`stuid`) USING BTREE,
  KEY `teacherid` (`teacherid`),
  KEY `stuid` (`stuid`),
  KEY `cid` (`cid`) USING BTREE,
  CONSTRAINT `scope_ibfk_1` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`),
  CONSTRAINT `scope_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`),
  CONSTRAINT `scope_ibfk_3` FOREIGN KEY (`stuid`) REFERENCES `student` (`stuid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scope
-- ----------------------------
INSERT INTO `scope` VALUES ('65', '1', 'One', '6', '1', '5');
INSERT INTO `scope` VALUES ('78', '1', 'Two', '7', '1', '5');
INSERT INTO `scope` VALUES ('100', '1', 'One', '8', '1', '5');
INSERT INTO `scope` VALUES ('76', '1', 'Two', '11', '1', '5');
INSERT INTO `scope` VALUES ('54', '1', 'One', '12', '1', '5');
INSERT INTO `scope` VALUES ('35', '1', 'Two', '13', '1', '5');
INSERT INTO `scope` VALUES ('76', '1', 'Two', '16', '1', '5');
INSERT INTO `scope` VALUES ('60', '1', '第一学期', '18', '10', '1');
INSERT INTO `scope` VALUES ('0', '1', '第一学期', '19', '10', '0');
INSERT INTO `scope` VALUES ('78', '2', 'One', '18', '1', '1');
INSERT INTO `scope` VALUES ('86', '2', 'One', '19', '1', '0');
INSERT INTO `scope` VALUES ('78', '3', 'One', '18', '1', '1');
INSERT INTO `scope` VALUES ('60', '8', '第一学期', '25', '12', '5');
INSERT INTO `scope` VALUES ('80', '14', '第二学期', '25', '8', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sex` char(2) NOT NULL,
  `age` date NOT NULL,
  `Nation` varchar(10) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `phone` bigint(50) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `Classid` int(11) DEFAULT NULL,
  `major` varchar(30) NOT NULL,
  `grade` varchar(30) DEFAULT NULL,
  `Enrolmenttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `photo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`stuid`),
  KEY `Classid` (`Classid`),
  CONSTRAINT `Classid` FOREIGN KEY (`Classid`) REFERENCES `classes` (`Classid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2', 'tzs', '男', '1997-02-25', '汉族', '900150983cd24fb0d6963f7d28e17f72null1100001', '18060572723', '大专', '1', '计算机', '第一学期', '2018-10-06 20:00:55', 'zzz.PNG');
INSERT INTO `student` VALUES ('3', 'kobe', '男', '1997-02-25', '汉族', 'kobe', null, '大专', '1', '计算机', '第一学期', '2018-10-06 22:09:03', null);
INSERT INTO `student` VALUES ('4', 'yk', '男', '1997-01-01', '汉族', 'abc', null, '大专', '1', '计算机', '第一学期', '2018-10-06 23:43:10', null);
INSERT INTO `student` VALUES ('6', 'Lin', '女', '1997-02-25', '汉族', '900150983cd24fb0d6963f7d28e17f72', null, '大专', '1', '英语', '第一学期', '2018-10-07 22:49:29', null);
INSERT INTO `student` VALUES ('7', 'Lebron', '男', '1984-01-01', '汉族', '900150983cd24fb0d6963f7d28e17f72', null, '大专', '1', '计算机', '第一学期', '2018-10-08 22:24:46', null);
INSERT INTO `student` VALUES ('8', 'AD', '女', '1997-02-01', '汉族', '900150983cd24fb0d6963f7d28e17f72', null, '大专', '1', '英语', '第一学期', '2018-10-08 22:26:34', null);
INSERT INTO `student` VALUES ('11', 'LA', '女', '1996-01-01', '汉族', 'LA', null, '高中', '1', '计算机', '第一学期', '2018-10-15 15:56:16', null);
INSERT INTO `student` VALUES ('12', 'king', '女', '1997-01-01', 'other', '900150983cd24fb0d6963f7d28e17f72', null, '高中', '1', '英语', '第一学期', '2018-10-16 14:50:29', null);
INSERT INTO `student` VALUES ('13', 'lin', '女', '1995-01-01', '汉族', 'c93169f1eb9be7246f990690b5e66b2d', '18060572723', '高中', '1', '计算机', '第一学期', '2018-10-16 15:04:03', 'uploadStudentRight.jpg');
INSERT INTO `student` VALUES ('16', 'lm', '男', '1927-01-08', '汉族', '900150983cd24fb0d6963f7d28e17f72null1100001', '18650380163', '大专', '1', '计算机', '第一学期', '2018-10-23 19:40:59', null);
INSERT INTO `student` VALUES ('18', 'tzs', '男', '1997-03-01', '汉族', '86fb9662aa9114866fdfc30a8616c313null1110100', null, '大专', '3', '计算机', '第一学期', '2018-11-08 23:21:42', null);
INSERT INTO `student` VALUES ('19', 'kg', '女', '1987-06-07', '汉族', '900150983cd24fb0d6963f7d28e17f72null1100001', null, '大专', '3', '计算机', '第一学期', '2018-11-08 23:22:10', null);
INSERT INTO `student` VALUES ('22', '天天', '女', '1997-01-02', '汉族', '900150983cd24fb0d6963f7d28e17f72null1100001', null, '大专', '1', '计算机', '第一学期', '2018-12-05 23:59:30', null);
INSERT INTO `student` VALUES ('23', '勒布朗', '男', '1984-06-21', '汉族', '900150983cd24fb0d6963f7d28e17f72null1100001', null, '大专', '1', '计算机', '第一学期', '2018-12-06 00:03:33', null);
INSERT INTO `student` VALUES ('25', '小黑', '女', '1987-01-06', '其他', '86fb9662aa9114866fdfc30a8616c313null1110100', '15394403038', '高中', '7', '商贸', '第二学期', '2018-12-23 14:19:20', '3.jpg');
INSERT INTO `student` VALUES ('28', '小白', '男', '1987-01-06', '汉族', '900150983cd24fb0d6963f7d28e17f72null1100001', null, '大专', '8', '计算机', '第一学期', '2019-04-17 10:54:39', null);

-- ----------------------------
-- Table structure for studydata
-- ----------------------------
DROP TABLE IF EXISTS `studydata`;
CREATE TABLE `studydata` (
  `teacherid` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacherid` (`teacherid`),
  CONSTRAINT `studydata_ibfk_1` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studydata
-- ----------------------------
INSERT INTO `studydata` VALUES ('10', 'D:\\data\\Ajax.pptx', '3', 'Ajax.pptx');
INSERT INTO `studydata` VALUES ('1', 'D:\\data\\zzz.PNG', '4', 'zzz.PNG');
INSERT INTO `studydata` VALUES ('1', 'D:\\data\\demo.zip', '6', 'demo.zip');
INSERT INTO `studydata` VALUES ('12', 'D:\\data\\动态sql1.PNG', '7', '动态sql1.PNG');
INSERT INTO `studydata` VALUES ('12', 'D:\\data\\3.jpg', '8', '3.jpg');

-- ----------------------------
-- Table structure for tc
-- ----------------------------
DROP TABLE IF EXISTS `tc`;
CREATE TABLE `tc` (
  `teacherid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `Classid` int(11) DEFAULT NULL,
  UNIQUE KEY `ctc` (`teacherid`,`cid`,`Classid`) USING BTREE,
  KEY `Classid` (`Classid`),
  KEY `teacherid` (`teacherid`),
  KEY `cid` (`cid`),
  CONSTRAINT `tc_ibfk_1` FOREIGN KEY (`Classid`) REFERENCES `classes` (`Classid`) ON DELETE CASCADE,
  CONSTRAINT `tc_ibfk_2` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`) ON DELETE CASCADE,
  CONSTRAINT `tc_ibfk_3` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc
-- ----------------------------
INSERT INTO `tc` VALUES ('1', '2', '3');
INSERT INTO `tc` VALUES ('2', '4', '3');
INSERT INTO `tc` VALUES ('10', '1', '3');
INSERT INTO `tc` VALUES ('10', '12', '5');
INSERT INTO `tc` VALUES ('12', '8', '6');
INSERT INTO `tc` VALUES ('12', '8', '7');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(15) NOT NULL,
  `tpwd` varchar(150) NOT NULL,
  `sal` int(11) NOT NULL,
  `Entrytime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `major` varchar(30) NOT NULL,
  `QQ` bigint(50) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `phone` bigint(50) NOT NULL,
  PRIMARY KEY (`teacherid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('0', '机器人', 'abc', '0', '2018-10-06 21:52:15', '无', '0', '0', '0');
INSERT INTO `teacher` VALUES ('1', '小红', '86fb9662aa9114866fdfc30a8616c313null1110100', '7000', '2018-10-06 21:52:15', '计算机', '1085185624', 'teacher1.jpg', '18060572723');
INSERT INTO `teacher` VALUES ('2', 'jj', 'abc', '5900', '2018-10-06 21:59:30', '计算机', null, 'teacher?????????.JPG', '15394403038');
INSERT INTO `teacher` VALUES ('3', 'king', '900150983cd24fb0d6963f7d28e17f72null1100001', '6000', '2018-10-12 14:18:31', '英语', null, 'teacher3.jpg', '15394403038');
INSERT INTO `teacher` VALUES ('6', 'lx', 'lx', '1000', '2018-10-13 00:27:23', '英语', null, 'teacher7.jpg', '15394403038');
INSERT INTO `teacher` VALUES ('8', 'ha', 'ha', '3200', '2018-10-13 00:36:28', '计算机', null, 'teacher1.jpg', '15394403038');
INSERT INTO `teacher` VALUES ('9', 'NN', '8cc2e7240164328fdc3f0e5e21032c56', '5000', '2018-10-20 23:57:55', '计算机', null, 'teacher3.jpg', '17778878');
INSERT INTO `teacher` VALUES ('10', 'lhy', '86fb9662aa9114866fdfc30a8616c313null1110100', '8000', '2018-10-29 12:40:02', '计算机', '2371331494', 'teacher3.jpg', '15394403038');
INSERT INTO `teacher` VALUES ('11', 'kig', '041f18d4e2e27cd97f4404c2c67cb362null1101011', '9000', '2018-10-31 22:17:49', '计算机', '23713314949', 'teacher5.jpg', '18060572723');
INSERT INTO `teacher` VALUES ('12', '檀钟盛', '900150983cd24fb0d6963f7d28e17f72null1100001', '9000', '2018-12-06 00:11:40', '计算机', '2371331494', 'teacherzzz.PNG', '15394403038');
INSERT INTO `teacher` VALUES ('15', '小王', '900150983cd24fb0d6963f7d28e17f72null1100001', '5000', '2019-04-17 08:59:18', '计算机', null, null, '15394403030');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `testname` varchar(255) NOT NULL,
  `option0` varchar(200) NOT NULL,
  `option1` varchar(200) NOT NULL,
  `option2` varchar(200) NOT NULL,
  `option3` varchar(200) NOT NULL,
  `answer` varchar(200) NOT NULL,
  `cid` int(11) NOT NULL,
  `id` int(30) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('    for(int i=0;i<5;i++){\r\n    	System.out.println(i);\r\n    }请问最后i值为多少', '0', '8', '3', '4', '4', '1', '1');
INSERT INTO `test` VALUES ('int类型的包装类是以下哪个', 'Integer', 'integer', 'Long', 'Int', 'Integer', '1', '2');
INSERT INTO `test` VALUES ('下列什么是循环语句', 'for语句', 'if语句', 'switch语句', 'static关键字', 'for语句', '1', '3');
INSERT INTO `test` VALUES ('怎么获得数组的长度', 'size属性', 'size方法', 'length方法', 'length属性', 'length方法', '1', '4');
INSERT INTO `test` VALUES ('怎么获得集合的长度', 'size属性', 'size方法', 'length方法', 'length属性', 'size方法', '1', '5');
