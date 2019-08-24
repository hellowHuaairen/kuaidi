/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-24 15:42:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kuaidi
-- ----------------------------
DROP TABLE IF EXISTS `kuaidi`;
CREATE TABLE `kuaidi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '收件人姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '收件人电话',
  `kuaidi_no` varchar(255) DEFAULT NULL COMMENT '快递单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
