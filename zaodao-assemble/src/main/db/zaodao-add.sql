
/* 添加交易方式字段 */
ALTER TABLE zd_trading_record ADD trade_method  varchar(255) ;

/* 删除银行流水号字段,添加交易描述 */
ALTER TABLE zd_trade_order_info CHANGE bank_order_no trade_text VARCHAR(255);

/* 添加订单类型 约导订单 交易订单 */
ALTER TABLE zd_trading_record ADD trade_business  varchar(255) ;

-- ----------------------------
-- Table structure for zd_customer_feedback
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_feedback`;
CREATE TABLE `zd_customer_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '反馈内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户反馈';

/* 添加订单表中导游每日收费金额*/
ALTER TABLE zd_plat_order_info ADD price_per_day bigint;



/* 导游评价 */
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for zd_plat_order_eval
-- ----------------------------
DROP TABLE IF EXISTS `zd_plat_order_eval`;
CREATE TABLE `zd_plat_order_eval` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tourist_id` varchar(64) NOT NULL COMMENT '游客ID',
  `tour_guide_id` varchar(64) NOT NULL COMMENT '导游ID',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `score` int(10) COMMENT '评分{1,2,3,4,5}',
  `content` longtext COMMENT '评价内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*添加微信openid字段*/
ALTER TABLE zd_customer ADD open_id VARCHAR(64);



/*2018-1-3*/
/*添加qq和微博openid字段*/

ALTER TABLE zd_customer ADD qq_id VARCHAR(64);

ALTER TABLE zd_customer ADD weibo_id VARCHAR(64);



/*2018-1-10*/
DROP TABLE IF EXISTS `zd_customer_ingore`;
CREATE TABLE `zd_customer_ingore` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `ingore_user_id` varchar(64) DEFAULT NULL COMMENT '屏蔽人员ID',
  `ingore_type` varchar(64) DEFAULT NULL COMMENT '屏蔽类型{travelVoice-旅声}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COMMENT='用户屏蔽表';