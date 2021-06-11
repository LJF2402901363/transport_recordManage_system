/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : q_dms

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/06/2021 20:05:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gather_logrec
-- ----------------------------
DROP TABLE IF EXISTS `gather_logrec`;
CREATE TABLE `gather_logrec`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `time` datetime(0) NULL DEFAULT NULL,
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logtype` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gather_logrec
-- ----------------------------
INSERT INTO `gather_logrec` VALUES (33, '2019-12-12 19:38:28', 'C', 2, 'C', 'C', 1);
INSERT INTO `gather_logrec` VALUES (34, '2019-12-12 19:38:25', 'C', 2, 'C', 'C', 0);
INSERT INTO `gather_logrec` VALUES (35, '2019-12-12 19:38:43', 'D', 2, 'D', 'D', 1);
INSERT INTO `gather_logrec` VALUES (36, '2019-12-12 19:38:46', 'D', 2, 'D', 'D', 0);
INSERT INTO `gather_logrec` VALUES (64, '2019-12-20 01:40:49', 'ee', 2, 'ee', 'ee', 1);
INSERT INTO `gather_logrec` VALUES (65, '2019-12-20 01:40:52', 'ee', 2, 'ee', 'ee', 0);
INSERT INTO `gather_logrec` VALUES (68, '2019-12-21 22:03:37', '1ddd', 2, 'dddd', 'dddd', 1);
INSERT INTO `gather_logrec` VALUES (69, '2019-12-21 22:03:39', '1ddd', 2, 'dddd', 'dddd', 0);
INSERT INTO `gather_logrec` VALUES (70, '2019-12-22 18:37:30', 'd', 2, 'dddd', 'dd', 1);
INSERT INTO `gather_logrec` VALUES (71, '2019-12-22 18:37:33', 'd', 2, 'dddd', 'dd', 0);
INSERT INTO `gather_logrec` VALUES (72, '2019-12-26 18:56:27', 'fff', 2, 'ff', 'rrr', 1);
INSERT INTO `gather_logrec` VALUES (73, '2019-12-26 18:56:30', 'fff', 2, 'ff', 'rrr', 0);
INSERT INTO `gather_logrec` VALUES (74, '2020-10-23 11:20:57', '127.0.0.1', 2, '张三', '127.0.0.2', 1);
INSERT INTO `gather_logrec` VALUES (75, '2020-10-23 11:21:27', '武汉', 2, '张三', '127.0.0.2', 0);
INSERT INTO `gather_logrec` VALUES (76, '2020-10-23 11:21:24', '武汉', 2, '张三', '127.0.0.2', 1);
INSERT INTO `gather_logrec` VALUES (77, '2020-10-23 11:21:27', '武汉', 2, '张三', '127.0.0.2', 0);
INSERT INTO `gather_logrec` VALUES (78, '2020-10-23 11:21:45', '广东', 2, '张三', '127.0.0.8', 1);
INSERT INTO `gather_logrec` VALUES (79, '2020-10-23 11:21:52', '广东', 2, '张三', '127.0.0.8', 0);

-- ----------------------------
-- Table structure for gather_transport
-- ----------------------------
DROP TABLE IF EXISTS `gather_transport`;
CREATE TABLE `gather_transport`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `time` datetime(0) NULL DEFAULT NULL,
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reciver` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transporttype` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gather_transport
-- ----------------------------
INSERT INTO `gather_transport` VALUES (38, '2019-12-12 19:39:20', 'W', 2, 'G', 'T', 1);
INSERT INTO `gather_transport` VALUES (39, '2019-12-12 19:39:16', 'W', 2, 'G', 'T', 2);
INSERT INTO `gather_transport` VALUES (40, '2019-12-12 19:39:13', 'W', 2, 'G', 'T', 3);
INSERT INTO `gather_transport` VALUES (44, '2019-12-19 22:55:35', 'e', 2, 'e', 'e', 1);
INSERT INTO `gather_transport` VALUES (45, '2019-12-19 22:55:38', 'e', 2, 'e', 'e', 2);
INSERT INTO `gather_transport` VALUES (46, '2019-12-19 22:55:41', 'e', 2, 'e', 'e', 3);
INSERT INTO `gather_transport` VALUES (53, '2019-12-26 18:56:37', 'w', 2, 'w', 'w', 1);
INSERT INTO `gather_transport` VALUES (54, '2019-12-26 18:56:41', 'w', 2, 'w', 'w', 2);
INSERT INTO `gather_transport` VALUES (55, '2019-12-26 18:56:45', 'w', 2, 'w', 'w', 3);
INSERT INTO `gather_transport` VALUES (56, '2019-12-26 18:56:48', 'w', 2, 'w', 'w', 1);
INSERT INTO `gather_transport` VALUES (57, '2019-12-26 18:56:41', 'w', 2, 'w', 'w', 2);
INSERT INTO `gather_transport` VALUES (58, '2019-12-26 18:56:45', 'w', 2, 'w', 'w', 3);

-- ----------------------------
-- Table structure for matched_logrec
-- ----------------------------
DROP TABLE IF EXISTS `matched_logrec`;
CREATE TABLE `matched_logrec`  (
  `loginid` int(11) NULL DEFAULT NULL,
  `logoutid` int(11) NULL DEFAULT NULL,
  UNIQUE INDEX `loginid`(`loginid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of matched_logrec
-- ----------------------------
INSERT INTO `matched_logrec` VALUES (33, 34);
INSERT INTO `matched_logrec` VALUES (35, 36);
INSERT INTO `matched_logrec` VALUES (53, 54);
INSERT INTO `matched_logrec` VALUES (55, 56);
INSERT INTO `matched_logrec` VALUES (57, 58);
INSERT INTO `matched_logrec` VALUES (59, 60);
INSERT INTO `matched_logrec` VALUES (1, 1);
INSERT INTO `matched_logrec` VALUES (64, 65);
INSERT INTO `matched_logrec` VALUES (68, 69);
INSERT INTO `matched_logrec` VALUES (70, 71);
INSERT INTO `matched_logrec` VALUES (72, 73);
INSERT INTO `matched_logrec` VALUES (74, 75);
INSERT INTO `matched_logrec` VALUES (76, 77);
INSERT INTO `matched_logrec` VALUES (78, 79);

-- ----------------------------
-- Table structure for matched_transport
-- ----------------------------
DROP TABLE IF EXISTS `matched_transport`;
CREATE TABLE `matched_transport`  (
  `sendid` int(11) NULL DEFAULT NULL,
  `transid` int(11) NULL DEFAULT NULL,
  `receiveid` int(11) NULL DEFAULT NULL,
  UNIQUE INDEX `sendid`(`sendid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of matched_transport
-- ----------------------------
INSERT INTO `matched_transport` VALUES (38, 39, 40);
INSERT INTO `matched_transport` VALUES (44, 45, 46);
INSERT INTO `matched_transport` VALUES (53, 54, 55);
INSERT INTO `matched_transport` VALUES (56, 57, 58);

-- ----------------------------
-- Table structure for userdetails
-- ----------------------------
DROP TABLE IF EXISTS `userdetails`;
CREATE TABLE `userdetails`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `hobby` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `degree` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userdetails
-- ----------------------------
INSERT INTO `userdetails` VALUES (23, 'bb', '62120A4D76BFE19DF4EA42A0203349BE', 1, '阅读,上网', '请输入新的地址', '小学');
INSERT INTO `userdetails` VALUES (27, 'aa', 'A264135F3F234FA0E573F5B7768C949B', 1, '上网,游泳', '武汉理工大学', '本科');
INSERT INTO `userdetails` VALUES (28, 'cc', 'A264135F3F234FA0E573F5B7768C949B', 0, '上网,游泳', '武汉理工大学', '本科');

SET FOREIGN_KEY_CHECKS = 1;
