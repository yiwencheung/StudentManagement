/*
 Navicat Premium Data Transfer

 Source Server         : 42.193.16.11
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 42.193.16.11:3307
 Source Schema         : custom

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 04/05/2023 09:06:02
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`     bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
    `parent_id`   bigint NULL DEFAULT 0 COMMENT '父菜单ID',
    `order_num`   int NULL DEFAULT 0 COMMENT '显示顺序',
    `path`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
    `query`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由参数',
    `is_frame`    int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
    `is_cache`    int NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2035 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2023-04-25 12:34:45', '',
        NULL, '系统管理目录');
INSERT INTO `sys_menu`
VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user',
        'admin', '2023-04-25 12:34:45', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu`
VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples',
        'admin', '2023-04-25 12:34:45', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu`
VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table',
        'admin', '2023-04-25 12:34:45', 'admin', '2023-04-30 19:36:28', '菜单管理菜单');
INSERT INTO `sys_menu`
VALUES (1000, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1001, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1002, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1003, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1004, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1005, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1006, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1007, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1008, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1009, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1010, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1011, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1012, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1013, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1014, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (1015, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin',
        '2023-04-25 12:34:45', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (2027, '文件转文字', 0, 4, 'fileToText', 'system/fileToText/index', NULL, 1, 0, 'C', '0', '0',
        'system:fileToText:list', 'excel', 'admin', '2023-04-30 23:56:21', 'admin', '2023-05-01 20:28:56', '');
INSERT INTO `sys_menu`
VALUES (2028, '转换记录', 1, 1, 'myRecord', 'system/myRecord/index', NULL, 1, 0, 'C', '0', '0', 'system:myRecord:list',
        '#', 'admin', '2023-05-01 00:53:34', '', NULL, '转换记录菜单');
INSERT INTO `sys_menu`
VALUES (2029, '转换记录查询', 2028, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:myRecord:query', '#', 'admin',
        '2023-05-01 00:53:34', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (2030, '转换记录新增', 2028, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:myRecord:add', '#', 'admin',
        '2023-05-01 00:53:34', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (2031, '转换记录修改', 2028, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:myRecord:edit', '#', 'admin',
        '2023-05-01 00:53:34', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (2032, '转换记录删除', 2028, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:myRecord:remove', '#', 'admin',
        '2023-05-01 00:53:34', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (2033, '转换记录导出', 2028, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'system:myRecord:export', '#', 'admin',
        '2023-05-01 00:53:34', '', NULL, '');
INSERT INTO `sys_menu`
VALUES (2034, '录音转文字', 0, 6, 'record', 'system/record/index', NULL, 1, 0, 'C', '0', '0', '', '#', 'admin',
        '2023-05-01 12:53:34', 'admin', '2023-05-01 20:28:50', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`           varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '角色名称',
    `role_key`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限字符串',
    `role_sort`           int                                                           NOT NULL COMMENT '显示顺序',
    `data_scope`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
    `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
    `status`              char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NOT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag`            char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`         datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`              varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2023-04-25 12:34:45', '', NULL, '超级管理员');
INSERT INTO `sys_role`
VALUES (2, '普通角色', 'common', 2, '2', 0, 0, '0', '0', 'admin', '2023-04-25 12:34:45', 'admin', '2023-04-30 16:13:44',
        '普通角色');
INSERT INTO `sys_role`
VALUES (3, '用户(用户注册后默认角色)', 'customer', 3, '1', 1, 1, '0', '0', '', NULL, '', NULL, NULL);
INSERT INTO `sys_role`
VALUES (100, '打工仔', 'dagongzai', 0, '1', 0, 0, '0', '0', 'admin', '2023-04-30 15:26:13', 'admin',
        '2023-05-01 20:48:19', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (2, 1);
INSERT INTO `sys_role_menu`
VALUES (2, 100);
INSERT INTO `sys_role_menu`
VALUES (2, 101);
INSERT INTO `sys_role_menu`
VALUES (2, 102);
INSERT INTO `sys_role_menu`
VALUES (2, 1000);
INSERT INTO `sys_role_menu`
VALUES (2, 1001);
INSERT INTO `sys_role_menu`
VALUES (2, 1002);
INSERT INTO `sys_role_menu`
VALUES (2, 1003);
INSERT INTO `sys_role_menu`
VALUES (2, 1004);
INSERT INTO `sys_role_menu`
VALUES (2, 1005);
INSERT INTO `sys_role_menu`
VALUES (2, 1006);
INSERT INTO `sys_role_menu`
VALUES (2, 1007);
INSERT INTO `sys_role_menu`
VALUES (2, 1008);
INSERT INTO `sys_role_menu`
VALUES (2, 1009);
INSERT INTO `sys_role_menu`
VALUES (2, 1010);
INSERT INTO `sys_role_menu`
VALUES (2, 1011);
INSERT INTO `sys_role_menu`
VALUES (2, 1012);
INSERT INTO `sys_role_menu`
VALUES (2, 1013);
INSERT INTO `sys_role_menu`
VALUES (2, 1014);
INSERT INTO `sys_role_menu`
VALUES (2, 1015);
INSERT INTO `sys_role_menu`
VALUES (3, 2034);
INSERT INTO `sys_role_menu`
VALUES (100, 1);
INSERT INTO `sys_role_menu`
VALUES (100, 100);
INSERT INTO `sys_role_menu`
VALUES (100, 1000);
INSERT INTO `sys_role_menu`
VALUES (100, 1001);
INSERT INTO `sys_role_menu`
VALUES (100, 1002);
INSERT INTO `sys_role_menu`
VALUES (100, 1003);
INSERT INTO `sys_role_menu`
VALUES (100, 1004);
INSERT INTO `sys_role_menu`
VALUES (100, 1005);
INSERT INTO `sys_role_menu`
VALUES (100, 1006);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`     bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_name`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
    `nick_name`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
    `user_type`   varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户邮箱',
    `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号码',
    `sex`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像地址',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '密码',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', 'Ysir', '00', 'ysir@163.com', '18918918918', '0',
        '/profile/avatar/2023/04/30/blob_1652690097477255168.',
        '$2a$10$2bXKM4SmGcCONOfbEqvm2.uStFeUpOUwgXJ.hLTXZ.x7FziK9avze', '0', '0', '127.0.0.1', '2023-04-28 17:16:15',
        'admin', '2023-04-25 12:34:45', '', '2023-04-30 23:03:37', '管理员');
INSERT INTO `sys_user`
VALUES (2, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-04-25 12:34:45',
        'admin', '2023-04-25 12:34:45', '', NULL, '测试员');
INSERT INTO `sys_user`
VALUES (100, '王医生', '王医生', '00', '', '', '0', '', '$2a$10$dVQnr8i0FDcgVRQn7K/A4O7Pc.jqVNZkLi2x4/vXXehdZnwGA8.LK',
        '0', '0', '', NULL, 'admin', '2023-04-28 15:21:31', '', NULL, NULL);
INSERT INTO `sys_user`
VALUES (101, '李医生', '李医生', '00', '', '', '0', '', '$2a$10$vXHVOFDxPsvtboo8fFPabuDx7e3ElmuhCHQ/MBzNpKpqZ2L.rJdua',
        '1', '0', '', NULL, 'admin', '2023-04-28 15:21:44', 'admin', '2023-04-30 14:48:13', NULL);
INSERT INTO `sys_user`
VALUES (102, '小张', '小张', '00', '', '', '0', '', '$2a$10$Qvc6P3mV.15OcYQ.UbBbne16I4pb/4HsTGcz45SZDubEbWICUmf22', '0',
        '0', '', NULL, 'admin', '2023-04-28 15:21:59', 'admin', '2023-04-29 20:52:16', NULL);
INSERT INTO `sys_user`
VALUES (103, '小王', '小王', '00', '', '', '0', '', '$2a$10$9KT8G.LoVi3K9c/03AqhO.vfJ7aZ3gNYa3PZoEpiZJQ27j2.WvP2m', '1',
        '0', '', NULL, 'admin', '2023-04-28 15:22:06', 'liqiaoqiao', '2023-04-30 16:13:13', NULL);
INSERT INTO `sys_user`
VALUES (106, 'liqiaoqiao', '李巧巧是猪儿虫', '00', '', '', '0', '',
        '$2a$10$1/6IWbS7p/biRIdbv/sJY.04/Z/x5UnNvrLMdNRSN84nMuofrqTM2', '1', '0', '', NULL, 'admin',
        '2023-04-29 21:17:21', 'liqiaoqiao', '2023-04-30 16:13:11', NULL);
INSERT INTO `sys_user`
VALUES (107, 'ysir', 'ysir', '00', '', '18818867888', '0', '',
        '$2a$10$fE635IkxDx0cIKZ4DRLdfufsfTdZHoEycLthBtWoQBk.4sV5kJ2zy', '1', '2', '', NULL, 'admin',
        '2023-04-30 14:46:22', '', NULL, NULL);
INSERT INTO `sys_user`
VALUES (108, 'yyyy', 'yyyy', '00', '', '', '0', '', '$2a$10$n/KB5hnjR8Yuu3yP6VNKNOw.rUuWqrzodFa./8VrG2oNT1fkLSlQe', '0',
        '0', '', NULL, '', '2023-05-01 20:20:53', '', NULL, NULL);
INSERT INTO `sys_user`
VALUES (109, 'yyy', 'yyy', '00', '', '', '0', '', '$2a$10$Iq/gbDTzV9r4d7MBKxufuuVFHqNh3d5yKEbUb25IX7DdWxgd4VCZu', '0',
        '0', '', NULL, '', '2023-05-01 20:30:21', '', NULL, NULL);
INSERT INTO `sys_user`
VALUES (110, 'qqq', 'qqq', '00', '', '', '0', '', '$2a$10$8oCJwFkEvtSbPe9GkLeGQ.cdpI2iNYbeuWR53qIIjl8Oh4slPPUze', '0',
        '0', '', NULL, '', '2023-05-01 20:34:09', '', NULL, NULL);
INSERT INTO `sys_user`
VALUES (111, 'aaa', 'aaa', '00', '', '', '0', '', '$2a$10$K7.0I/rX.JukHpRxC5Qyl.uG3XiOW2ZKvj6uDs2oxuFOET2uuU9iK', '0',
        '0', '', NULL, '', '2023-05-01 20:37:00', 'admin', '2023-05-01 20:40:11', NULL);
INSERT INTO `sys_user`
VALUES (112, 'bbb', 'bbb', '00', '', '', '0', '', '$2a$10$neTKv/tRXnmCyKnvseP7b.H.WKczZl6zqRSPeVXPPpBm5SivfhXLG', '0',
        '0', '', NULL, '', '2023-05-01 20:49:20', '', NULL, NULL);
INSERT INTO `sys_user`
VALUES (113, 'sss', 'sss', '00', '', '', '0', '', '$2a$10$Ti3Isxe7D.FuHlJxX.TFcOtHQSMZKZct2qif6LzxNNy.jiXiWtyne', '0',
        '0', '', NULL, '', '2023-05-01 21:48:02', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1);
INSERT INTO `sys_user_role`
VALUES (2, 1);
INSERT INTO `sys_user_role`
VALUES (2, 2);
INSERT INTO `sys_user_role`
VALUES (2, 100);
INSERT INTO `sys_user_role`
VALUES (100, 2);
INSERT INTO `sys_user_role`
VALUES (100, 100);
INSERT INTO `sys_user_role`
VALUES (111, 3);
INSERT INTO `sys_user_role`
VALUES (112, 3);
INSERT INTO `sys_user_role`
VALUES (113, 3);

SET
FOREIGN_KEY_CHECKS = 1;
