/*
MySQL Data Transfer
Source Host: localhost
Source Database: demo
Target Host: localhost
Target Database: demo
Date: 2011-1-13 14:59:06
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for group_authorities
-- ----------------------------
CREATE TABLE `group_authorities` (
  `group_id` bigint(20) NOT NULL,
  `authority` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`group_id`,`authority`),
  CONSTRAINT `fk_group_authorities_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `group_authorities` VALUES ('1', 'PERM_ACCESS_ADMIN');
INSERT INTO `group_authorities` VALUES ('1', 'PERM_ACCESS_USER');
INSERT INTO `group_authorities` VALUES ('2', 'PERM_ACCESS_USER');
