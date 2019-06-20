-- ----------------------------
-- Table structure for act_account
-- ----------------------------
DROP TABLE IF EXISTS `act_account`;
CREATE TABLE `act_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `partner_id` varchar(30) DEFAULT NULL COMMENT '接入商ID',
  `user_id` varchar(30) DEFAULT NULL COMMENT '会员ID',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名/外部用户id',
  `account_type` varchar(32) DEFAULT NULL COMMENT '用户类型',
  `account_no` varchar(30) DEFAULT NULL COMMENT '账户号',
  `balance` bigint(20) DEFAULT '0' COMMENT '余额',
  `freeze` bigint(20) DEFAULT NULL COMMENT '冻结金额',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `memo` varchar(128) DEFAULT NULL COMMENT '内部备注',
  `comments` varchar(128) DEFAULT NULL COMMENT '外部备注',
  `ext_context_json` varchar(256) DEFAULT NULL COMMENT '扩展字段',
  `last_change_id` bigint(20) DEFAULT NULL COMMENT '最后变动记录',
  `parent_user_id` varchar(30) DEFAULT NULL COMMENT '上级用户ID',
  `search_path` varchar(256) DEFAULT NULL COMMENT '查询归类(上级userid,....,自身userid)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for act_account_change
-- ----------------------------
DROP TABLE IF EXISTS `act_account_change`;
CREATE TABLE `act_account_change` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `partner_id` varchar(32) DEFAULT NULL COMMENT '接入商ID',
  `trade_type` varchar(32) DEFAULT NULL COMMENT '顶层交易类型',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `account_type` varchar(32) DEFAULT NULL COMMENT '账户类型',
  `account_no` varchar(64) DEFAULT NULL COMMENT '账户号(本地账户编号)',
  `change_type` varchar(16) DEFAULT NULL COMMENT '变动类型 {UP:上账,DOWN:下载,TRANSFER:转账,KEEP:不变}',
  `amount` bigint(20) DEFAULT '0' COMMENT '交易金额(分)',
  `balance` bigint(20) DEFAULT '0' COMMENT '交易后余额(分)',
  `freeze` bigint(20) DEFAULT '0' COMMENT '冻结/解冻金额',
  `freeze_balance` bigint(20) DEFAULT '0' COMMENT '交易后冻结余额',
  `status` varchar(32) DEFAULT NULL COMMENT '状态 {DISABLE:无效,ENABLE:有效}',
  `ref_user_id` varchar(32) DEFAULT NULL COMMENT '相关用户ID',
  `ref_user_name` varchar(30) DEFAULT NULL,
  `ref_account_no` varchar(32) DEFAULT NULL COMMENT '相关账户号',
  `memo` varchar(128) DEFAULT NULL COMMENT '内部备注',
  `comments` varchar(128) DEFAULT NULL COMMENT '外部备注',
  `transfer_type` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `business_id` varchar(32) DEFAULT NULL COMMENT '上层业务id',
  `previou_change_id` bigint(20) DEFAULT NULL COMMENT '前置变动id',
  `trans_date` datetime DEFAULT NULL COMMENT '交易时间/网关时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for act_account_freeze
-- ----------------------------
DROP TABLE IF EXISTS `act_account_freeze`;
CREATE TABLE `act_account_freeze` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `partner_id` varchar(30) DEFAULT NULL COMMENT '接入商ID',
  `user_id` varchar(30) DEFAULT NULL COMMENT '会员ID',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名/外部用户id',
  `account_type` varchar(32) DEFAULT NULL COMMENT '用户类型',
  `account_no` varchar(30) DEFAULT NULL COMMENT '账户号',
  `freeze_type` varchar(32) DEFAULT NULL COMMENT '冻结类型',
  `freeze_balance` bigint(20) DEFAULT NULL COMMENT '冻结金额余额',
  `memo` varchar(128) DEFAULT NULL COMMENT '内部备注',
  `comments` varchar(128) DEFAULT NULL COMMENT '外部备注',
  `last_freeze_change_id` bigint(20) DEFAULT NULL COMMENT '最后变动记录id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for act_account_freeze_change
-- ----------------------------
DROP TABLE IF EXISTS `act_account_freeze_change`;
CREATE TABLE `act_account_freeze_change` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `partner_id` varchar(32) DEFAULT NULL COMMENT '接入商ID',
  `trade_type` varchar(32) DEFAULT NULL COMMENT '顶层交易类型',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `account_type` varchar(32) DEFAULT NULL COMMENT '账户类型',
  `account_no` varchar(64) DEFAULT NULL COMMENT '账户号(本地账户编号)',
  `freeze_type` varchar(32) DEFAULT NULL COMMENT '冻结类型',
  `freeze_amount` bigint(20) DEFAULT '0' COMMENT '变动金额',
  `freeze_balance` bigint(20) DEFAULT '0' COMMENT '变动后余额',
  `memo` varchar(128) DEFAULT NULL COMMENT '内部备注',
  `comments` varchar(128) DEFAULT NULL COMMENT '外部备注',
  `business_id` varchar(30) DEFAULT NULL COMMENT '上传业务id',
  `freeze_leave` bigint(20) DEFAULT '0' COMMENT '剩余未解冻金额',
  `previou_freeze_change_id` bigint(20) DEFAULT NULL COMMENT '前置变动id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;


/*2018-04-10*/
/* 接入商户为观世宇测试商户账号：test*/
 -- 账务表写默认早导网接入商
INSERT INTO act_account(partner_id,user_id,user_name,account_type,account_no,balance,freeze,status,memo,search_path)
SELECT 'test', 'test', '早导网','MAIN','test','0','0','ENABLE','早导网账户',''
WHERE NOT EXISTS (SELECT 1 FROM act_account WHERE user_id='test' AND partner_id='test');

-- 同步用户表到账务表
INSERT INTO act_account(partner_id,user_id,user_name,account_type,account_no,balance,freeze,status,memo,parent_user_id,search_path)
SELECT 'test', a.user_id,a.user_name,'MAIN',a.user_id,'0','0','ENABLE','用户注册','test', CONCAT(a.user_id,',')
FROM zd_customer a WHERE NOT EXISTS(
	SELECT 1 FROM act_account b WHERE a.user_id=b.user_id
);


/* 接入商户为观世宇正式商户账号：GSY17081817454905730026*/
/*
INSERT INTO act_account(partner_id,user_id,user_name,account_type,account_no,balance,freeze,status,memo,search_path)
SELECT 'GSY17081817454905730026', 'GSY17081817454905730026', '早导网','MAIN','GSY17081817454905730026','0','0','ENABLE','早导网账户',''
WHERE NOT EXISTS (SELECT 1 FROM act_account WHERE user_id='GSY17081817454905730026' AND partner_id='GSY17081817454905730026');

-- 同步用户表到账务表
INSERT INTO act_account(partner_id,user_id,user_name,account_type,account_no,balance,freeze,status,memo,parent_user_id,search_path)
SELECT 'GSY17081817454905730026', a.user_id,a.user_name,'MAIN',a.user_id,'0','0','ENABLE','用户注册','GSY17081817454905730026', CONCAT(a.user_id,',')
FROM zd_customer a WHERE NOT EXISTS(
	SELECT 1 FROM act_account b WHERE a.user_id=b.user_id
);

*/