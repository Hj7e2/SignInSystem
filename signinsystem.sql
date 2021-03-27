/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : signinsystem

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/07/2020 11:28:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 学生
-- ----------------------------
DROP TABLE IF EXISTS `学生`;
CREATE TABLE `学生`  (
  `账号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `学号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `姓名` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `性别` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `班级` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `学院` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `专业` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `年级` int(255) NULL DEFAULT NULL,
  `出生日期` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `联系方式` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `个性签名` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`学号`) USING BTREE,
  INDEX `学生`(`账号`) USING BTREE,
  CONSTRAINT `学生` FOREIGN KEY (`账号`) REFERENCES `用户` (`账号`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 学生
-- ----------------------------
INSERT INTO `学生` VALUES ('4', '1', '7e2寒假', '男', '软件171', '计算机工程学院', '软件工程', 17, '1998-11-25', '15190350992', '没有期待就不会失望，没有羁绊就不会受伤');
INSERT INTO `学生` VALUES ('5', '2', '解决', '女', '软件171', '计算机工程学院', '软件工程', 17, '1998-11-25', '15190350992', '哈哈分');

-- ----------------------------
-- Table structure for 打卡情况
-- ----------------------------
DROP TABLE IF EXISTS `打卡情况`;
CREATE TABLE `打卡情况`  (
  `账号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `日期` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `今日体温` double(255, 1) NOT NULL,
  `联系方式` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `异常情况` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`账号`, `日期`) USING BTREE,
  CONSTRAINT `打卡情况_ibfk_1` FOREIGN KEY (`账号`) REFERENCES `用户` (`账号`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 打卡情况
-- ----------------------------
INSERT INTO `打卡情况` VALUES ('1', '2020-7-7', 1.0, '', '体温异常 ');
INSERT INTO `打卡情况` VALUES ('4', '2020-7-2', 38.0, '15190350992', '体温异常 ');
INSERT INTO `打卡情况` VALUES ('4', '2020-7-3', 37.0, '15190350992', '无');
INSERT INTO `打卡情况` VALUES ('4', '2020-7-4', 38.0, '15190350992', '体温异常 ');
INSERT INTO `打卡情况` VALUES ('4', '2020-7-5', 37.6, '15190350992', '体温异常 ');
INSERT INTO `打卡情况` VALUES ('5', '2020-7-2', 39.0, '15190350992', '体温异常 已确诊，未隔离 ');

-- ----------------------------
-- Table structure for 提醒
-- ----------------------------
DROP TABLE IF EXISTS `提醒`;
CREATE TABLE `提醒`  (
  `账号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `事件` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `日期` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `批语` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`账号`, `事件`, `日期`) USING BTREE,
  CONSTRAINT `提醒_ibfk_1` FOREIGN KEY (`账号`) REFERENCES `用户` (`账号`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 提醒
-- ----------------------------
INSERT INTO `提醒` VALUES ('3', '打卡', '1', '');

-- ----------------------------
-- Table structure for 教师
-- ----------------------------
DROP TABLE IF EXISTS `教师`;
CREATE TABLE `教师`  (
  `账号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `工号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `姓名` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `性别` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `学院` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `专业` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `年级` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `出生日期` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `联系方式` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `个性签名` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`工号`) USING BTREE,
  INDEX `教师`(`账号`) USING BTREE,
  CONSTRAINT `教师` FOREIGN KEY (`账号`) REFERENCES `用户` (`账号`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 教师
-- ----------------------------
INSERT INTO `教师` VALUES ('1', '1', '张三', '男', '', '', '', '', '', '');
INSERT INTO `教师` VALUES ('2', '2', '李四', '女', '计算机工程学院', '', '', '', '', '');
INSERT INTO `教师` VALUES ('3', '3', '王五', '男', '计算机工程学院', '软件工程', '17', '2020-02-05', '', '');

-- ----------------------------
-- Table structure for 用户
-- ----------------------------
DROP TABLE IF EXISTS `用户`;
CREATE TABLE `用户`  (
  `账号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `密码` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `身份` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `院系` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `头像` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `封面` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`账号`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 用户
-- ----------------------------
INSERT INTO `用户` VALUES ('1', '1', '校长', NULL, '', '');
INSERT INTO `用户` VALUES ('2', '2', '院长', '计算机工程学院', '', NULL);
INSERT INTO `用户` VALUES ('3', '3', '教师', '计算机工程学院', NULL, '');
INSERT INTO `用户` VALUES ('4', '4', '学生', '计算机工程学院', '', '');
INSERT INTO `用户` VALUES ('5', '5', '学生', '计算机工程学院', '', '');

-- ----------------------------
-- Table structure for 请假情况
-- ----------------------------
DROP TABLE IF EXISTS `请假情况`;
CREATE TABLE `请假情况`  (
  `账号` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `请假日期` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `请假时间` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `请假原因` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `是否处理` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`账号`, `请假日期`) USING BTREE,
  CONSTRAINT `请假情况_ibfk_1` FOREIGN KEY (`账号`) REFERENCES `用户` (`账号`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 请假情况
-- ----------------------------
INSERT INTO `请假情况` VALUES ('2', '2020-7-4', '1天', '无', '是');
INSERT INTO `请假情况` VALUES ('4', '2020-7-4', '1天', '', '是');

SET FOREIGN_KEY_CHECKS = 1;
