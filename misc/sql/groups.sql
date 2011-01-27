/*
MySQL Data Transfer
Source Host: localhost
Source Database: demo
Target Host: localhost
Target Database: demo
Date: 2011-1-13 14:59:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for groups
-- ----------------------------
CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL auto_increment,
  `group_name` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `groups` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `groups` VALUES ('2', 'ROLE_USER');
