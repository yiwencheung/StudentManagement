/*
 Navicat MySQL Data Transfer

 Source Server         : 戴特
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 42.193.16.11:3307
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 29/04/2023 17:06:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-04-28 17:16:15', 'admin', '2023-04-25 12:34:45', '', '2023-04-28 17:16:14', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-04-25 12:34:45', 'admin', '2023-04-25 12:34:45', '', NULL, '测试员');
INSERT INTO `sys_user` VALUES (100, 100, '王医生', '王医生', '00', '', '', '0', '', '$2a$10$dVQnr8i0FDcgVRQn7K/A4O7Pc.jqVNZkLi2x4/vXXehdZnwGA8.LK', '0', '0', '', NULL, 'admin', '2023-04-28 15:21:31', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101, NULL, '李医生', '李医生', '00', '', '', '0', '', '$2a$10$vXHVOFDxPsvtboo8fFPabuDx7e3ElmuhCHQ/MBzNpKpqZ2L.rJdua', '0', '0', '', NULL, 'admin', '2023-04-28 15:21:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (102, NULL, '小张', '小张', '00', '', '', '0', '', '$2a$10$Qvc6P3mV.15OcYQ.UbBbne16I4pb/4HsTGcz45SZDubEbWICUmf22', '0', '0', '', NULL, 'admin', '2023-04-28 15:21:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (103, NULL, '小王', '小王', '00', '', '', '0', '', '$2a$10$9KT8G.LoVi3K9c/03AqhO.vfJ7aZ3gNYa3PZoEpiZJQ27j2.WvP2m', '0', '0', '', NULL, 'admin', '2023-04-28 15:22:06', '', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
