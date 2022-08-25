/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : hospital

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 24/08/2022 18:34:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(15) NOT NULL,
  `rid` int(6) NULL DEFAULT NULL,
  `jobid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `cfid` int(30) NULL DEFAULT NULL,
  `finishedTime` datetime(0) NULL DEFAULT NULL,
  `isCompleted` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isDeleted` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `fk_pid3`(`pid`) USING BTREE,
  INDEX `fk_cfid3`(`cfid`) USING BTREE,
  INDEX `fk_jobid1`(`jobid`) USING BTREE,
  INDEX `fk_rid`(`rid`) USING BTREE,
  CONSTRAINT `fk_cfid3` FOREIGN KEY (`cfid`) REFERENCES `prescription` (`cfid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_jobid1` FOREIGN KEY (`jobid`) REFERENCES `doctor` (`jobid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pid3` FOREIGN KEY (`pid`) REFERENCES `patient` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_rid` FOREIGN KEY (`rid`) REFERENCES `registration` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (1, 4409001, 8, NULL, NULL, NULL, '0', '0');
INSERT INTO `bill` VALUES (2, 4409001, NULL, NULL, 100171, NULL, '0', '0');

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`  (
  `did` int(15) NOT NULL AUTO_INCREMENT,
  `cfid` int(30) NOT NULL,
  `mid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `quantity` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `usage` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `isDeleted` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`did`) USING BTREE,
  INDEX `Loid`(`mid`) USING BTREE,
  INDEX `Listid`(`cfid`) USING BTREE,
  CONSTRAINT `fk_cfid` FOREIGN KEY (`cfid`) REFERENCES `prescription` (`cfid`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_mid` FOREIGN KEY (`mid`) REFERENCES `medicine` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 313 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES (199, 100151, 'E301', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (200, 100152, 'E301', '2', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (202, 100158, 'E301', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (203, 100159, 'E301', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (205, 100160, 'E301', '1', 'dfjddddddddddd', '0');
INSERT INTO `detail` VALUES (207, 100162, 'E301', '1', '口服，每日三次，每次10mg', '0');
INSERT INTO `detail` VALUES (208, 100163, 'E301', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (210, 100171, 'E301', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (211, 100171, 'C201', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (212, 100171, 'E301', '2', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (214, 100176, 'E301', '12', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (215, 100177, 'E301', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (223, 100180, 'E301', '1', 'sgggdggggggg', '0');
INSERT INTO `detail` VALUES (297, 100254, 'E301', '1', '口服，每日一次，每次10mg', '0');
INSERT INTO `detail` VALUES (298, 100176, 'E301', '1', '1222', '0');
INSERT INTO `detail` VALUES (299, 100176, 'E301', '2', '22', '0');
INSERT INTO `detail` VALUES (300, 100176, 'E301', '4', '44', '1');
INSERT INTO `detail` VALUES (301, 100269, 'C201', '1', '222', '0');
INSERT INTO `detail` VALUES (302, 100270, 'C201', '1', '222', '0');
INSERT INTO `detail` VALUES (303, 100271, 'C201', '1', '问题他', '0');
INSERT INTO `detail` VALUES (304, 100272, 'C201', '1', '问题他', '0');
INSERT INTO `detail` VALUES (305, 100273, 'C201', '1', '问题他', '0');
INSERT INTO `detail` VALUES (306, 100274, 'C201', '1', '问题他', '0');
INSERT INTO `detail` VALUES (307, 100275, 'C201', '1', '34', '0');
INSERT INTO `detail` VALUES (308, 100276, 'C201', '1', '是德国', '1');
INSERT INTO `detail` VALUES (309, 100277, 'C201', '1', ' 和', '0');
INSERT INTO `detail` VALUES (310, 100278, 'C201', '1', '速度', '0');
INSERT INTO `detail` VALUES (311, 100279, 'C201', '1', '速度', '0');
INSERT INTO `detail` VALUES (312, 100280, 'C201', '12', '无', '0');

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `jobid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sex` char(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `department` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `position` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `isDeleted` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `identityCard` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`jobid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('18555', 'test', '男', 'test', 'test', '13541564111', '0', '2022-08-02', '440821212121212');
INSERT INTO `doctor` VALUES ('19001', '医一', '女', '内科', '副主任医师', '1831831831', '0', '1994-12-16', '440821212121212');
INSERT INTO `doctor` VALUES ('19002', '医二', '女', '内科', '副主任医师', '1831831831', '0', '2000-01-18', '440821212121212');
INSERT INTO `doctor` VALUES ('19003', '医三', '男', '内科', '主治医师', '1831831831', '0', '2000-01-18', '440821212121212');
INSERT INTO `doctor` VALUES ('19004', '医四', '女', '内科', '副主任医师', '1831831831', '0', '2000-01-18', '440821212121212');
INSERT INTO `doctor` VALUES ('19005', '医五', '男', '内科', '主任医师', '1831831831', '0', '2000-01-18', '440821212121212');
INSERT INTO `doctor` VALUES ('19006', '医六', '男', '内科', '主治医师', '1831831831', '0', '2000-01-18', '440821212121212');
INSERT INTO `doctor` VALUES ('19007', '医七', '男', '内科', '主任医师', '1831831831', '0', '2000-01-18', '440821212121212');
INSERT INTO `doctor` VALUES ('45665', '65++6+', '男', '45666', '4156456', '56++', '0', '2022-08-01', '440821212121212');
INSERT INTO `doctor` VALUES ('63156311', '土土土土土', '女', '131533', '14122', '32154564646', '0', '2022-08-01', '440821212121212');
INSERT INTO `doctor` VALUES ('admin', 'admin', 'admin', 'admin', 'admin', 'admin', '0', '1790-01-01', '440821212121212');
INSERT INTO `doctor` VALUES ('分解机', '房管局 ', '男', '法家', '房管局', '分开看', '0', '2022-08-02', '440821212121212');
INSERT INTO `doctor` VALUES ('对方哈哈哈', '地方经济', '男', '复活节', '法家', '法家', '0', '2022-08-02', '440821212121212');
INSERT INTO `doctor` VALUES ('的环境', '法家', '女', '放骨灰盒', '灌灌灌灌', '法国急急急就', '0', '2022-08-02', '440821212121212');
INSERT INTO `doctor` VALUES ('返回急急急', '分级基金', '男', '都很积极', '梵蒂冈哈哈', '分解机', '0', '2022-08-01', '440821212121212');

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine`  (
  `mid` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `drugName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `quantity` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `entertime` datetime(0) NOT NULL,
  `drugPrice` float(10, 2) NOT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medicine
-- ----------------------------
INSERT INTO `medicine` VALUES ('A101', '桂林西瓜霜', '222', '中成药', '2022-03-07 14:09:15', 10.00);
INSERT INTO `medicine` VALUES ('A102', '桂林西瓜霜', '999', '中成药', '2022-03-30 14:07:00', 10.00);
INSERT INTO `medicine` VALUES ('B104', '富马酸比索洛尔片', '888', '心血管系统', '2022-03-30 14:08:30', 10.00);
INSERT INTO `medicine` VALUES ('C201', '异维A酸', '444', '皮肤病', '2022-03-07 14:10:38', 10.00);
INSERT INTO `medicine` VALUES ('D501', '莲花清瘟胶囊', '777', '中成药', '2022-03-16 14:15:16', 10.00);
INSERT INTO `medicine` VALUES ('E301', '扑热息痛', '2222', '非甾体抗炎药', '2022-03-15 14:20:51', 10.00);

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `pid` int(15) NOT NULL AUTO_INCREMENT,
  `identityCard` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pname` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sex` char(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `isDeleted` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `phone`(`phone`(20)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4409011 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (4409001, '44092111111111', '患者一', '男', '2001-06-23 02:00:00', '1831831831', '0');
INSERT INTO `patient` VALUES (4409002, '44092111111111', '患者二', '女', '2001-01-01 00:00:00', '1831831831', '0');
INSERT INTO `patient` VALUES (4409003, '44092111111111', '患者三', '男', '2022-03-16 00:00:00', '1831831831', '0');
INSERT INTO `patient` VALUES (4409004, '44092111111111', '患者四', '女', '2022-03-08 00:00:00', '1831831831', '0');
INSERT INTO `patient` VALUES (4409005, '44092111111111', '患者五', '男', '2022-03-30 00:00:00', '1831831831', '0');
INSERT INTO `patient` VALUES (4409006, '44092111111111', '患者六', '男', '2022-03-30 00:00:00', '1831831831', '0');
INSERT INTO `patient` VALUES (4409007, '44092111111111', '七七七', '女', '2022-05-31 00:00:00', '1831831831', '0');
INSERT INTO `patient` VALUES (4409008, '44092111111111', 'wtagg', '男', '2004-08-03 08:00:00', '123', '0');
INSERT INTO `patient` VALUES (4409009, '4409440844012', 'eee', '女', '2022-08-01 08:00:00', '3111111', '0');
INSERT INTO `patient` VALUES (4409010, '12346', 'sb', '男', '2022-08-02 08:00:00', '12355667', '0');

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription`  (
  `cfid` int(30) NOT NULL AUTO_INCREMENT,
  `pid` int(15) NOT NULL,
  `jobid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `opentime` datetime(0) NOT NULL,
  `isDeleted` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `isCompleted` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `totalPrice` float(10, 2) NOT NULL,
  PRIMARY KEY (`cfid`) USING BTREE,
  INDEX `fk_jobid`(`jobid`) USING BTREE,
  INDEX `fk_pid`(`pid`) USING BTREE,
  CONSTRAINT `fk_jobid` FOREIGN KEY (`jobid`) REFERENCES `doctor` (`jobid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pid` FOREIGN KEY (`pid`) REFERENCES `patient` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100281 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prescription
-- ----------------------------
INSERT INTO `prescription` VALUES (100150, 4409001, '19001', '无', '2022-02-01 21:12:29', '1', '0', 100.00);
INSERT INTO `prescription` VALUES (100151, 4409003, '19001', '无', '2022-05-24 21:25:51', '0', '0', 20.50);
INSERT INTO `prescription` VALUES (100152, 4409005, '19001', '无', '2022-05-24 22:21:54', '0', '0', 11.00);
INSERT INTO `prescription` VALUES (100158, 4409001, '19002', '无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无无', '2022-05-31 21:41:54', '0', '0', 21.00);
INSERT INTO `prescription` VALUES (100159, 4409002, '19002', '无', '2022-05-31 21:52:02', '0', '0', 12.00);
INSERT INTO `prescription` VALUES (100160, 4409001, '19001', '无', '2022-06-13 19:46:11', '0', '0', 21.00);
INSERT INTO `prescription` VALUES (100162, 4409002, '19001', '无', '2022-06-13 19:53:16', '0', '0', 12.00);
INSERT INTO `prescription` VALUES (100163, 4409003, '19001', '无', '2022-06-13 19:56:28', '0', '0', 21.00);
INSERT INTO `prescription` VALUES (100171, 4409001, '19001', '无', '2022-06-13 00:00:00', '0', '0', 21.00);
INSERT INTO `prescription` VALUES (100176, 4409001, '19001', '无', '2021-12-01 00:00:00', '0', '0', 21.00);
INSERT INTO `prescription` VALUES (100177, 4409001, '19001', '无', '2022-06-13 00:00:00', '0', '0', 12.00);
INSERT INTO `prescription` VALUES (100180, 4409003, '19001', '无', '2022-06-13 00:00:00', '0', '0', 12.00);
INSERT INTO `prescription` VALUES (100212, 4409001, '19002', '1', '2022-06-13 00:00:00', '0', '0', 12.00);
INSERT INTO `prescription` VALUES (100254, 4409002, '19001', 'null', '2022-06-14 00:00:00', '0', '0', 2.00);
INSERT INTO `prescription` VALUES (100268, 4409001, '19001', '22', '2022-08-23 19:08:33', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100269, 4409001, '19001', '22', '2022-08-23 19:09:44', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100270, 4409001, '19001', '22', '2022-08-23 19:11:29', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100271, 4409001, '19001', '电话', '2022-08-23 20:00:32', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100272, 4409001, '19001', '电话', '2022-08-23 20:01:58', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100273, 4409001, '19001', '电话', '2022-08-23 20:02:40', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100274, 4409001, '19001', '电话', '2022-08-23 20:03:11', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100275, 4409001, '19001', '2356他', '2022-08-23 20:08:39', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100276, 4409001, '19001', '电话', '2022-08-23 20:10:22', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100277, 4409001, '19001', '和', '2022-08-23 20:15:37', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100278, 4409002, '19001', 'd和', '2022-08-23 20:17:10', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100279, 4409002, '19001', 'd和', '2022-08-23 20:20:11', '0', '0', 10.00);
INSERT INTO `prescription` VALUES (100280, 4409001, '19001', '无', '2022-08-24 18:30:34', '0', '0', 10.00);

-- ----------------------------
-- Table structure for registration
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration`  (
  `rid` int(6) NOT NULL AUTO_INCREMENT,
  `Pid` int(15) NOT NULL,
  `jobid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `price` float NOT NULL,
  `visitTime` datetime(0) NOT NULL,
  `isCompleted` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cfid` int(30) NULL DEFAULT NULL,
  `isDeleted` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `fk_pid2`(`Pid`) USING BTREE,
  INDEX `fk_jid`(`jobid`) USING BTREE,
  INDEX `fk_cfid2`(`cfid`) USING BTREE,
  CONSTRAINT `fk_cfid2` FOREIGN KEY (`cfid`) REFERENCES `prescription` (`cfid`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_jid` FOREIGN KEY (`jobid`) REFERENCES `doctor` (`jobid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pidd` FOREIGN KEY (`Pid`) REFERENCES `patient` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of registration
-- ----------------------------
INSERT INTO `registration` VALUES (3, 4409003, '19001', 17, '2022-06-15 21:16:41', '0', 100171, '0');
INSERT INTO `registration` VALUES (8, 4409001, '19001', 200, '2022-06-15 15:36:06', '0', 100176, '0');
INSERT INTO `registration` VALUES (9, 4409001, '19001', 14, '2022-06-15 15:36:18', '0', NULL, '0');
INSERT INTO `registration` VALUES (10, 4409001, '19001', 10.5, '2022-06-15 22:10:44', '0', NULL, '0');
INSERT INTO `registration` VALUES (11, 4409001, '19003', 1, '2022-06-15 22:11:32', '0', NULL, '0');
INSERT INTO `registration` VALUES (12, 4409001, '19001', 1, '2022-06-15 22:11:46', '0', NULL, '0');
INSERT INTO `registration` VALUES (13, 4409001, '19001', 1, '2022-06-15 22:12:01', '0', NULL, '0');
INSERT INTO `registration` VALUES (14, 4409002, '19001', 1, '2022-06-14 23:05:32', '0', 100279, '0');
INSERT INTO `registration` VALUES (15, 4409001, '19001', 103, '2022-08-21 20:21:08', '0', 100277, '0');
INSERT INTO `registration` VALUES (16, 4409001, '19001', 103, '2022-08-21 20:21:08', '0', 100276, '0');
INSERT INTO `registration` VALUES (17, 4409001, '19001', 103, '2022-08-21 20:21:08', '1', 100275, '0');
INSERT INTO `registration` VALUES (18, 4409001, '19001', 103, '2022-08-21 20:21:08', '0', NULL, '0');
INSERT INTO `registration` VALUES (19, 4409001, '19001', 103, '2022-08-21 20:21:08', '0', NULL, '0');
INSERT INTO `registration` VALUES (20, 4409001, '19001', 103, '2022-08-21 20:21:08', '0', NULL, '0');
INSERT INTO `registration` VALUES (21, 4409001, '19001', 103, '2022-08-21 20:21:08', '0', NULL, '0');
INSERT INTO `registration` VALUES (22, 4409001, '19001', 103, '2022-08-21 20:21:08', '0', NULL, '0');
INSERT INTO `registration` VALUES (23, 4409003, '19003', 110, '2022-08-24 17:49:14', '0', NULL, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `jobid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `isManager` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isDelete` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '19001', '110110110', '111', '123@abc.com', '2022-01-24 14:41:10', '普通用户', '0');
INSERT INTO `user` VALUES (2, 'admin', '119119119', '000', '123@abc.com', '2022-04-01 14:41:19', '管理员', '0');
INSERT INTO `user` VALUES (4, '19002', '119119119', '2222', '123@abc.com', '2022-08-24 14:41:25', '普通用户', '0');
INSERT INTO `user` VALUES (5, '19003', '119119119', '222', '123@abc.com', '2022-06-01 14:41:27', '管理员', '0');
INSERT INTO `user` VALUES (6, '19004', '119119119', '111222', '123@abc.com', '2022-07-01 14:41:30', '普通用户', '0');
INSERT INTO `user` VALUES (7, '19005', '119119119', '11122', '123@abc.com', '2022-02-01 14:41:45', '普通用户', '0');
INSERT INTO `user` VALUES (8, '19006', '119119119', '111', '123@abc.com', '2022-06-01 14:41:59', '普通用户', '0');
INSERT INTO `user` VALUES (9, '1234566', '111111', '134567', '12355', '2022-08-24 14:42:06', '普通用户', '0');
INSERT INTO `user` VALUES (10, '1345', '111111', '123456', '1234@244', '2022-05-01 14:42:10', '管理员', '0');
INSERT INTO `user` VALUES (11, NULL, '1900111', '111', NULL, '2022-04-01 14:42:14', '普通用户', '0');
INSERT INTO `user` VALUES (12, NULL, '1900111', '111', NULL, '2022-08-24 14:42:20', '管理员', '0');
INSERT INTO `user` VALUES (13, NULL, '1900111', '111', NULL, '2022-08-24 14:42:23', '管理员', '0');
INSERT INTO `user` VALUES (14, NULL, '1900111', '111', NULL, '2022-01-24 14:41:10', '管理员', '0');
INSERT INTO `user` VALUES (15, '199998', '1831288', '123456', '2541@q.com', '2022-01-24 14:41:10', '普通用户', '0');
INSERT INTO `user` VALUES (16, '199999', '1831288', '254156566', NULL, '2022-01-24 14:41:10', '普通用户', '0');
INSERT INTO `user` VALUES (17, NULL, '123', '111321', NULL, '2022-01-24 14:41:10', '就诊人', '0');
INSERT INTO `user` VALUES (18, NULL, '123', '111321', NULL, '2022-01-24 14:41:10', '就诊人', '0');
INSERT INTO `user` VALUES (19, NULL, '123', '111321', NULL, '2022-01-24 14:41:10', '就诊人', '0');
INSERT INTO `user` VALUES (20, '12345567', '123457', '123456', '1245@的风格', '2022-01-24 14:41:10', '普通用户', '0');
INSERT INTO `user` VALUES (21, '12355', '1234', '123456', '2356', '2022-08-24 14:42:38', '普通用户', '0');
INSERT INTO `user` VALUES (22, '123466', '123456', '123456', '256677', '2022-06-01 14:42:41', '普通用户', '0');
INSERT INTO `user` VALUES (23, NULL, '191216', '1312', '3516356', '2022-08-24 15:16:54', '就诊人', '0');
INSERT INTO `user` VALUES (24, NULL, '123456', '13214556', '1351@', '2022-08-01 15:16:57', '就诊人', '0');
INSERT INTO `user` VALUES (25, NULL, '13456', '1354564', '653321', '2022-05-10 15:17:00', '就诊人', '0');
INSERT INTO `user` VALUES (26, NULL, '1265468', '64161231', '616531', '2022-04-01 15:17:07', '就诊人', '0');
INSERT INTO `user` VALUES (27, NULL, '1234567899', '123456', '456+', '2022-03-01 15:17:13', '普通用户', '0');
INSERT INTO `user` VALUES (28, '18000', '12345678998', '123456', '12346', '2022-08-24 17:52:05', '普通用户', '0');

-- ----------------------------
-- Table structure for worktime
-- ----------------------------
DROP TABLE IF EXISTS `worktime`;
CREATE TABLE `worktime`  (
  `wid` varchar(122) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `jobid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `edition` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `date` datetime(0) NOT NULL,
  `location` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`wid`) USING BTREE,
  INDEX `jobid`(`jobid`) USING BTREE,
  CONSTRAINT `jobid` FOREIGN KEY (`jobid`) REFERENCES `doctor` (`jobid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worktime
-- ----------------------------
INSERT INTO `worktime` VALUES ('1', '19001', '2201', '2022-01-05 14:12:29', '1楼');
INSERT INTO `worktime` VALUES ('2', '19001', '2202', '2022-02-01 14:12:46', '1楼');
INSERT INTO `worktime` VALUES ('3', '19001', '2203', '2022-03-01 14:11:37', '1楼');
INSERT INTO `worktime` VALUES ('4', '19002', '2203', '2022-03-02 14:12:05', '1楼');

SET FOREIGN_KEY_CHECKS = 1;
