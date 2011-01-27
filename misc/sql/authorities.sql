/*
MySQL Data Transfer
Source Host: localhost
Source Database: demo
Target Host: localhost
Target Database: demo
Date: 2011-1-13 14:59:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for authorities
-- ----------------------------
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) collate utf8_unicode_ci NOT NULL,
  `authority` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_authorities` (`username`),
  CONSTRAINT `fk_authorities` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `authorities` VALUES ('1', 'admin', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('2', 'admin', 'ROLE_USER');
INSERT INTO `authorities` VALUES ('3', 'user', 'ROLE_USER');
