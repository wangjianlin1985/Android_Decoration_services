/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : wash_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2019-04-30 13:45:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for `mealevaluate`
-- ----------------------------
DROP TABLE IF EXISTS `mealevaluate`;
CREATE TABLE `mealevaluate` (
  `evaluateId` int(11) NOT NULL auto_increment,
  `washMealObj` int(11) default NULL,
  `evaluateContent` varchar(2000) default NULL,
  `userObj` varchar(20) default NULL,
  `evaluateTime` varchar(20) default NULL,
  PRIMARY KEY  (`evaluateId`),
  KEY `FK38DB059C107609D9` (`washMealObj`),
  KEY `FK38DB059CC80FC67` (`userObj`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mealevaluate
-- ----------------------------
INSERT INTO `mealevaluate` VALUES ('1', '1', '装修的不错，是我要的风格', 'user1', '2019-04-30');
INSERT INTO `mealevaluate` VALUES ('2', '1', '价格便宜，下次再来', 'user1', '2019-04-30');

-- ----------------------------
-- Table structure for `orderinfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderId` int(11) NOT NULL auto_increment,
  `washMealObj` int(11) default NULL,
  `orderCount` int(11) default NULL,
  `userObj` varchar(20) default NULL,
  `telephone` varchar(20) default NULL,
  `orderTime` varchar(20) default NULL,
  `orderState` int(11) default NULL,
  `memo` varchar(20) default NULL,
  PRIMARY KEY  (`orderId`),
  KEY `FK601628FC107609D9` (`washMealObj`),
  KEY `FK601628FCE83E3F68` (`orderState`),
  KEY `FK601628FCC80FC67` (`userObj`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('1', '1', '2', 'user1', '13958342342', '2019-04-30', '3', '我要北欧风格，设计的好一点的哦我的房子');
INSERT INTO `orderinfo` VALUES ('2', '2', '2', 'user1', '13983423432', '2019-04-30', '1', '装修的细心一点');

-- ----------------------------
-- Table structure for `orderstate`
-- ----------------------------
DROP TABLE IF EXISTS `orderstate`;
CREATE TABLE `orderstate` (
  `orderStateId` int(11) NOT NULL auto_increment,
  `stateName` varchar(20) default NULL,
  PRIMARY KEY  (`orderStateId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderstate
-- ----------------------------
INSERT INTO `orderstate` VALUES ('1', '用户已下单');
INSERT INTO `orderstate` VALUES ('2', '装修工作进行中');
INSERT INTO `orderstate` VALUES ('3', '装修工作已经完成好');
INSERT INTO `orderstate` VALUES ('4', '交易完成');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) default NULL,
  `name` varchar(20) default NULL,
  `sex` varchar(4) default NULL,
  `birthDate` datetime default NULL,
  `userPhoto` varchar(50) default NULL,
  `telephone` varchar(20) default NULL,
  `address` varchar(80) default NULL,
  `latitude` float(9,6) default NULL,
  `longitude` float(9,6) default NULL,
  `regTime` varchar(20) default NULL,
  PRIMARY KEY  (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('user1', '123', '双鱼林', '男', '2019-04-08 00:00:00', 'upload/90F9147F8E1CD47E4F40FA953E3D17EC.jpg', '13573598343', '成都理工大学理工东苑13号', '30.688467', '104.159599', '2019-4-29 14:15:15');
INSERT INTO `userinfo` VALUES ('bhgb', '155', 'vv', 'vg', '2019-04-05 00:00:00', 'upload/noimage.jpg', 'ggnd', 'hg', '30.687792', '104.146172', '--');

-- ----------------------------
-- Table structure for `washclass`
-- ----------------------------
DROP TABLE IF EXISTS `washclass`;
CREATE TABLE `washclass` (
  `classId` int(11) NOT NULL auto_increment,
  `className` varchar(50) default NULL,
  `classDesc` varchar(2000) default NULL,
  PRIMARY KEY  (`classId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of washclass
-- ----------------------------
INSERT INTO `washclass` VALUES ('1', '中型装修公司', '中型装修公司');
INSERT INTO `washclass` VALUES ('2', '小型装修公司', '小型装修公司');
INSERT INTO `washclass` VALUES ('3', '大型装修公司', '大型装修公司');

-- ----------------------------
-- Table structure for `washmeal`
-- ----------------------------
DROP TABLE IF EXISTS `washmeal`;
CREATE TABLE `washmeal` (
  `mealId` int(11) NOT NULL auto_increment,
  `mealName` varchar(60) default NULL,
  `introduce` varchar(2000) default NULL,
  `price` float default NULL,
  `mealPhoto` varchar(50) default NULL,
  `publishDate` datetime default NULL,
  `washShopObj` varchar(20) default NULL,
  PRIMARY KEY  (`mealId`),
  KEY `FK22DE172253C3D8F9` (`washShopObj`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of washmeal
-- ----------------------------
INSERT INTO `washmeal` VALUES ('1', '全包', '一、材料：水泥、沙砖、水电管、电线、防水涂料、开关面板，厨卫吊顶、鞋柜、背景墙、客厅布局吊顶、石材、窗台石、配电箱；地砖、墙砖、房门、厨柜、洁具、开关面板、衣书柜等（由装修公司购买）；\r\n\r\n二、施工安装所用人工，管理费用。对照装修半包材料有：一般装修公司购买基础隐蔽材料：水泥、沙砖、水电管、电线、防水涂料，施工安装所用人工，管理费用。现在的全包还发展到配套齐全的家具、家电、软装饰品、开荒清洁等。', '25', 'upload/63518EFC4DA71B7ED3B7A9402373F5CA.jpg', '2019-04-30 00:00:00', 'shop001');
INSERT INTO `washmeal` VALUES ('2', '半包', '半包，又名清工辅料，是指介于清包和全包之间的一种方式，施工方负责施工和辅料的采购，价值较高的主料自己采购可以控制费用的大头，种类繁杂价值较低的辅料业主不容易搞得清，由施工方采购比较省心点。\r\n半包主料由业主采购，如地板、洁具、瓷砖、地砖、涂料、橱具、锁具、五金件等，其他一些辅料有施工队来安排，如沙子、钉子、水泥等。 ', '20', 'upload/noimage.jpg', '2019-04-06 00:00:00', 'shop002');
INSERT INTO `washmeal` VALUES ('3', '整包', '包括瓷砖油漆家居家电', '33', 'upload/noimage.jpg', '2019-04-05 00:00:00', 'shop001');

-- ----------------------------
-- Table structure for `washshop`
-- ----------------------------
DROP TABLE IF EXISTS `washshop`;
CREATE TABLE `washshop` (
  `shopUserName` varchar(20) NOT NULL,
  `password` varchar(20) default NULL,
  `shopName` varchar(50) default NULL,
  `washClassObj` int(11) default NULL,
  `shopPhoto` varchar(50) default NULL,
  `telephone` varchar(20) default NULL,
  `addDate` datetime default NULL,
  `address` varchar(80) default NULL,
  `latitude` float(9,6) default NULL,
  `longitude` float(9,6) default NULL,
  PRIMARY KEY  (`shopUserName`),
  KEY `FK22E0DE5561A5FA75` (`washClassObj`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of washshop
-- ----------------------------
INSERT INTO `washshop` VALUES ('shop001', '123', '宜家装修公司', '1', 'upload/72A7EA6C495A070AD58D78A1FD244AB6.jpg', '028-83439834', '2019-04-30 00:00:00', '成都三环外富森家具附近', '30.681574', '104.152695');
INSERT INTO `washshop` VALUES ('shop002', '123', '和信家居装饰公司', '2', 'upload/C77B8F88CFB729EF901556E3F15B1B7D.jpg', '028-83493224', '2019-04-06 00:00:00', '成都武侯区', '30.679602', '104.158546');
