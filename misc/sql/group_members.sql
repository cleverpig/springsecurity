/*
MySQL Data Transfer
Source Host: localhost
Source Database: demo
Target Host: localhost
Target Database: demo
Date: 2011-1-13 14:59:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for group_members
-- ----------------------------
CREATE TABLE `group_members` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(50) collate utf8_unicode_ci NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_group_members_group` (`group_id`),
  CONSTRAINT `fk_group_members_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `group_members` VALUES ('1', 'admin', '1');
INSERT INTO `group_members` VALUES ('2', 'user', '2');
