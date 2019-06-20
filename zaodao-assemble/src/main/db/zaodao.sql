/*
Navicat MySQL Data Transfer

Source Server         : 早导网正式
Source Server Version : 50634
Source Host           : rm-uf677748hx0vzm51d.mysql.rds.aliyuncs.com:3306
Source Database       : zaodao

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-09-21 09:57:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zd_area
-- ----------------------------
DROP TABLE IF EXISTS `zd_area`;
CREATE TABLE `zd_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '地区名称',
  `code` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='地区表';

-- ----------------------------
-- Table structure for zd_article
-- ----------------------------
DROP TABLE IF EXISTS `zd_article`;
CREATE TABLE `zd_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '发表人ID',
  `mobile_no` varchar(32) NOT NULL COMMENT '发表人手机号码',
  `title` varchar(64) NOT NULL COMMENT '文章标题',
  `content` longtext NOT NULL COMMENT '文章内容',
  `cover` varchar(128) NOT NULL COMMENT '文章封面',
  `cover_thumb` varchar(128) DEFAULT NULL COMMENT '帖子封面小图',
  `area` varchar(64) NOT NULL COMMENT '地区',
  `scenic` varchar(64) NOT NULL COMMENT '景区',
  `label` varchar(64) NOT NULL COMMENT '标签',
  `read_count` bigint(10) DEFAULT '0' COMMENT '阅览数',
  `praise_count` bigint(10) DEFAULT '0' COMMENT '点赞数',
  `article_type` varchar(20) NOT NULL COMMENT '文章类型 {travelStrategy:旅游攻略,experienceTour:带团心得,personalSummary:个人总结}',
  `article_status` varchar(20) DEFAULT NULL COMMENT '文章状态:{draft:草稿,published:已发布,deleted:已删除}',
  `has_red_bag` int(10) DEFAULT '0' COMMENT '是否有红包{1:有红包,0无红包}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `essence_status` int(11) DEFAULT NULL,
  `up_status` int(11) DEFAULT NULL,
  `article_hot_type` varchar(16) DEFAULT 'general' COMMENT '帖子展示类型 {general:普通帖子,essence:精华热帖}',
  `stamp_code` varchar(20) DEFAULT NULL COMMENT '图章编码',
  `enshrine_count` bigint(10) DEFAULT '0' COMMENT '收藏数',
  `reward_count` bigint(10) DEFAULT '0' COMMENT '打赏数',
  `reward_total_amount` bigint(20) DEFAULT '0' COMMENT '打赏金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=289 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for zd_article_enshrine_log
-- ----------------------------
DROP TABLE IF EXISTS `zd_article_enshrine_log`;
CREATE TABLE `zd_article_enshrine_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '帖子ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8 COMMENT='帖子收藏记录表';

-- ----------------------------
-- Table structure for zd_article_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `zd_article_operation_log`;
CREATE TABLE `zd_article_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `opt_mobile_no` varchar(255) DEFAULT NULL,
  `opt_real_name` varchar(255) DEFAULT NULL,
  `opt_user_id` varchar(255) DEFAULT NULL,
  `opt_user_name` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zd_article_praise_log
-- ----------------------------
DROP TABLE IF EXISTS `zd_article_praise_log`;
CREATE TABLE `zd_article_praise_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '帖子ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='帖子点赞记录表';

-- ----------------------------
-- Table structure for zd_article_review
-- ----------------------------
DROP TABLE IF EXISTS `zd_article_review`;
CREATE TABLE `zd_article_review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '回复的评论ID',
  `article_id` bigint(20) NOT NULL COMMENT '回复的文章ID',
  `review_user_id` varchar(64) NOT NULL COMMENT '评论人ID',
  `content` text NOT NULL COMMENT '评论内容',
  `reply_count` bigint(10) DEFAULT '0' COMMENT '被回复数',
  `read_status` int(11) DEFAULT '0' COMMENT '查看状态 {1:已查看,0:未查看}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `shield_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8 COMMENT='评论回复表';

-- ----------------------------
-- Table structure for zd_article_reward_log
-- ----------------------------
DROP TABLE IF EXISTS `zd_article_reward_log`;
CREATE TABLE `zd_article_reward_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '打赏用户ID',
  `business_id` bigint(20) DEFAULT NULL COMMENT '业务关联ID',
  `point_amount` bigint(20) DEFAULT '0' COMMENT '打赏金额',
  `reward_type` varchar(32) DEFAULT NULL COMMENT '打赏类型{article:文章打赏,review:回复打赏}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zd_credit_signin
-- ----------------------------
DROP TABLE IF EXISTS `zd_credit_signin`;
CREATE TABLE `zd_credit_signin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `sign_time` datetime DEFAULT NULL COMMENT '签到时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `times` int(11) DEFAULT '0' COMMENT '连续签到天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='积分签到记录';

-- ----------------------------
-- Table structure for zd_cus_account
-- ----------------------------
DROP TABLE IF EXISTS `zd_cus_account`;
CREATE TABLE `zd_cus_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `mobile_no` varchar(32) NOT NULL COMMENT '用户手机号码',
  `total_amount` bigint(20) DEFAULT '0' COMMENT '总资产',
  `available_balance` bigint(20) DEFAULT '0' COMMENT '可用余额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户信息表';

-- ----------------------------
-- Table structure for zd_customer
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer`;
CREATE TABLE `zd_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '会员唯一标识',
  `mobile_no` varchar(32) NOT NULL COMMENT '电话号码',
  `user_name` varchar(32) DEFAULT NULL COMMENT '账户名',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `is_certification` int(1) DEFAULT '0' COMMENT '是否实名 {1:是,0:否}',
  `login_password` varchar(128) NOT NULL COMMENT '登录密码',
  `pay_password` varchar(128) DEFAULT NULL COMMENT '交易密码',
  `login_sail` varchar(32) NOT NULL COMMENT '登录密码加密填充值',
  `pay_sail` varchar(32) DEFAULT NULL COMMENT '交易密码加密填充值',
  `head_img` longtext COMMENT '用户头像',
  `is_tour_guide` int(11) DEFAULT '0' COMMENT '是否为导游 {1:是,0:否}',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮件',
  `sex` int(11) DEFAULT '1' COMMENT '性别 {1:男,0:女}',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `contact_address` varchar(128) DEFAULT NULL COMMENT '联系地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_moderator` int(11) DEFAULT '0' COMMENT '是否为版主',
  `is_shutup` int(11) DEFAULT '0' COMMENT '是否禁言 {1:是,0:否}',
  `is_lector` int(255) DEFAULT '0' COMMENT '是否讲师{0:否,1:是,-1:申请中}',
  `moderator_permission` varchar(32) DEFAULT 'all' COMMENT '版主权限 {all:所有权限,guideSecret:导游秘籍,experienceTour:带团日志,guideTreeHole:导游树洞,caseAnalysis:案例分析}',
  `out_user_id` varchar(64) DEFAULT NULL COMMENT '观世宇会员唯一标识',
  `medal_code` varchar(255) DEFAULT NULL COMMENT '勋章编码，逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for zd_customer_album
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_album`;
CREATE TABLE `zd_customer_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `album_name` text NOT NULL COMMENT '相册名称',
  `type` int(11) DEFAULT '1' COMMENT '相册类型 {1:默认相册,0:上传相册}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cover_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='用户相册表';

-- ----------------------------
-- Table structure for zd_customer_card
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_card`;
CREATE TABLE `zd_customer_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `mobile_no` varchar(32) NOT NULL COMMENT '用户手机号码',
  `card_no` varchar(128) NOT NULL COMMENT '绑卡号',
  `card_name` varchar(128) NOT NULL COMMENT '绑卡中文名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `bind_id` varchar(64) NOT NULL COMMENT '绑卡ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户绑卡信息表';

-- ----------------------------
-- Table structure for zd_customer_img
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_img`;
CREATE TABLE `zd_customer_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `album_id` bigint(20) NOT NULL COMMENT '相册ID',
  `cus_picture` text NOT NULL COMMENT '用户照片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `thumb_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='用户照片表';

-- ----------------------------
-- Table structure for zd_customer_language
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_language`;
CREATE TABLE `zd_customer_language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `language_name` varchar(32) NOT NULL COMMENT '语种名称',
  `language_code` varchar(32) NOT NULL COMMENT '语种编码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导游语种表';

-- ----------------------------
-- Table structure for zd_customer_message
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_message`;
CREATE TABLE `zd_customer_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `message_title` varchar(64) DEFAULT NULL COMMENT '消息标题',
  `message` varchar(512) NOT NULL COMMENT '消息内容',
  `read_status` int(11) DEFAULT '0' COMMENT '查看状态 {1:已查看,0:未查看}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8 COMMENT='用户系统消息表';

-- ----------------------------
-- Table structure for zd_customer_video
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_video`;
CREATE TABLE `zd_customer_video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `cover` varchar(128) NOT NULL COMMENT '视频封面',
  `video_name` varchar(128) NOT NULL COMMENT '视频名称',
  `cus_video` varchar(128) NOT NULL COMMENT '用户视频',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户视频表';

-- ----------------------------
-- Table structure for zd_guide_count_info
-- ----------------------------
DROP TABLE IF EXISTS `zd_guide_count_info`;
CREATE TABLE `zd_guide_count_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `amount_count` bigint(20) DEFAULT NULL,
  `order_count` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zd_leave_message
-- ----------------------------
DROP TABLE IF EXISTS `zd_leave_message`;
CREATE TABLE `zd_leave_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '导游ID',
  `leave_id` varchar(64) NOT NULL COMMENT '留言游客ID',
  `content` text NOT NULL COMMENT '留言内容',
  `read_status` int(11) DEFAULT '0' COMMENT '查看状态 {1:已查看,0:未查看}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='留言表';

-- ----------------------------
-- Table structure for zd_leave_message_reply
-- ----------------------------
DROP TABLE IF EXISTS `zd_leave_message_reply`;
CREATE TABLE `zd_leave_message_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message_id` bigint(20) DEFAULT NULL COMMENT '留言id',
  `reply_content` text NOT NULL COMMENT '回复内容',
  `reply_user_id` varchar(64) DEFAULT NULL COMMENT '回复用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='留言回复表';

-- ----------------------------
-- Table structure for zd_plat_order_info
-- ----------------------------
DROP TABLE IF EXISTS `zd_plat_order_info`;
CREATE TABLE `zd_plat_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tourist_id` varchar(64) NOT NULL COMMENT '游客ID',
  `tour_guide_id` varchar(64) NOT NULL COMMENT '导游ID',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `day_count` int(11) NOT NULL COMMENT '游玩天数',
  `adult_count` int(11) NOT NULL COMMENT '成人数',
  `child_count` int(11) NOT NULL COMMENT '小孩数',
  `order_amount` bigint(20) NOT NULL COMMENT '订单金额',
  `order_status` varchar(20) NOT NULL COMMENT '订单状态 {pay:已支付,noPay:未支付,close:已关闭,delete:已删除}',
  `start_play_time` datetime NOT NULL COMMENT '游玩开始时间',
  `end_play_time` datetime NOT NULL COMMENT '游玩结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `contact_memo` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for zd_plat_sms
-- ----------------------------
DROP TABLE IF EXISTS `zd_plat_sms`;
CREATE TABLE `zd_plat_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile_no` varchar(32) NOT NULL COMMENT '电话号码',
  `sms_code` varchar(16) NOT NULL COMMENT '验证码',
  `order_no` varchar(64) NOT NULL COMMENT '短息校验码',
  `sms_sned_type` varchar(64) NOT NULL COMMENT '短信类型 {register:注册,findPassword:找回密码,bindingCard:绑定银行卡,rsetMobile:修改手机号}',
  `verify_status` varchar(16) DEFAULT 'fail' COMMENT '校验状态 {success:成功,fail:失败}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短息发送校验表';

-- ----------------------------
-- Table structure for zd_red_bag
-- ----------------------------
DROP TABLE IF EXISTS `zd_red_bag`;
CREATE TABLE `zd_red_bag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `order_no` varchar(64) DEFAULT NULL COMMENT '业务订单号',
  `ref_id` bigint(20) DEFAULT NULL COMMENT '关联id',
  `red_bag_type` varchar(32) DEFAULT NULL COMMENT '红包类型',
  `total_num` int(11) DEFAULT NULL COMMENT '红包个数',
  `surplus_num` int(255) DEFAULT NULL COMMENT '剩余个数',
  `total_amount` bigint(20) DEFAULT NULL COMMENT '金额',
  `surplus_amount` bigint(20) DEFAULT NULL COMMENT '剩余金额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='红包表';

-- ----------------------------
-- Table structure for zd_red_bag_log
-- ----------------------------
DROP TABLE IF EXISTS `zd_red_bag_log`;
CREATE TABLE `zd_red_bag_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `red_bag_type` varchar(255) DEFAULT NULL,
  `ref_id` bigint(20) DEFAULT NULL,
  `total_amount` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zd_secret
-- ----------------------------
DROP TABLE IF EXISTS `zd_secret`;
CREATE TABLE `zd_secret` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(128) NOT NULL COMMENT '问题内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='密保问题表';

-- ----------------------------
-- Table structure for zd_secret_answer
-- ----------------------------
DROP TABLE IF EXISTS `zd_secret_answer`;
CREATE TABLE `zd_secret_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '会员ID',
  `mobile_no` varchar(32) NOT NULL COMMENT '会员手机号码',
  `secret_id` bigint(20) NOT NULL COMMENT '密保ID',
  `content` varchar(128) NOT NULL COMMENT '问题内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密保答案表';

-- ----------------------------
-- Table structure for zd_shop_goods
-- ----------------------------
DROP TABLE IF EXISTS `zd_shop_goods`;
CREATE TABLE `zd_shop_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(40) DEFAULT NULL COMMENT '编码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `brand` varchar(32) DEFAULT NULL COMMENT '品牌',
  `subject` varchar(128) DEFAULT NULL COMMENT '简介',
  `logo` varchar(128) DEFAULT NULL COMMENT '商品LOGO',
  `type_id` bigint(20) NOT NULL COMMENT '分类',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `delivery_type` int(11) NOT NULL DEFAULT '1' COMMENT '送货方式{1:实物,2:服务/电子}',
  `credits` bigint(20) NOT NULL DEFAULT '0' COMMENT '积分定价',
  `sell_amount` bigint(20) DEFAULT NULL COMMENT '销售价格',
  `discount_credits` bigint(20) DEFAULT '0' COMMENT '积分折扣价格',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `total_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '上架数量',
  `sell_quantity` int(11) DEFAULT '0' COMMENT '销售数量',
  `content_id` bigint(20) DEFAULT NULL COMMENT '商品介绍内容ID',
  `validity_date` datetime DEFAULT NULL COMMENT '有效期',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态{1:上架,2:下架}',
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `market_credits` bigint(20) DEFAULT '0' COMMENT '市场定价',
  `max_buy_num` int(11) DEFAULT '0' COMMENT '最大购买数量',
  `is_hot` int(11) DEFAULT '0' COMMENT '推荐产品{0:不是,1:是}',
  PRIMARY KEY (`id`),
  KEY `FKe8m27xvx1jjkdoarciruk2nkw` (`type_id`),
  KEY `FK3v0er3wsr4txyto14fv93xvm3` (`supplier_id`),
  CONSTRAINT `FK3v0er3wsr4txyto14fv93xvm3` FOREIGN KEY (`supplier_id`) REFERENCES `zd_shop_supplier` (`id`),
  CONSTRAINT `FKe8m27xvx1jjkdoarciruk2nkw` FOREIGN KEY (`type_id`) REFERENCES `zd_shop_goods_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='商品信息';

-- ----------------------------
-- Table structure for zd_shop_goods_detail
-- ----------------------------
DROP TABLE IF EXISTS `zd_shop_goods_detail`;
CREATE TABLE `zd_shop_goods_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `body` text COMMENT '内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FKbv91a7lngby8drqs4r4qhvg75` (`goods_id`),
  CONSTRAINT `FKbv91a7lngby8drqs4r4qhvg75` FOREIGN KEY (`goods_id`) REFERENCES `zd_shop_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='商品详情';

-- ----------------------------
-- Table structure for zd_shop_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `zd_shop_goods_type`;
CREATE TABLE `zd_shop_goods_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID(顶层为空)',
  `code` varchar(32) NOT NULL COMMENT '编码(每2个字节为一层，顶层从从01开始)',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `sort_time` bigint(20) NOT NULL COMMENT '排序时间',
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='商品分类';

-- ----------------------------
-- Table structure for zd_shop_order_info
-- ----------------------------
DROP TABLE IF EXISTS `zd_shop_order_info`;
CREATE TABLE `zd_shop_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `goods_name` varchar(64) DEFAULT NULL COMMENT '商品名称(冗余)',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT NULL COMMENT '商品数量',
  `amount` bigint(20) NOT NULL COMMENT '订单金额',
  `sell_amount` bigint(20) DEFAULT '0' COMMENT '销售价格',
  `supplier` varchar(64) DEFAULT NULL COMMENT '供货商名称(冗余)',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '供货商ID(冗余)',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '客户用户名',
  `real_name` varchar(32) NOT NULL COMMENT '姓名',
  `prov_name` varchar(16) DEFAULT NULL COMMENT '省',
  `city_name` varchar(16) DEFAULT NULL COMMENT '市',
  `dist_name` varchar(16) DEFAULT NULL COMMENT '区',
  `address` varchar(128) DEFAULT NULL COMMENT '送货地址',
  `mobile_no` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  `verify_code` varchar(16) DEFAULT NULL COMMENT '收货验证码',
  `delivery_type` int(11) DEFAULT NULL COMMENT '投递方式',
  `status` int(11) DEFAULT NULL COMMENT '状态{0:已退货,1:下订,2:送货中,3:已到货}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='商城订单信息';

-- ----------------------------
-- Table structure for zd_shop_supplier
-- ----------------------------
DROP TABLE IF EXISTS `zd_shop_supplier`;
CREATE TABLE `zd_shop_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `mobile_no` varchar(21) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `opt_user` varchar(21) DEFAULT NULL COMMENT '操作员',
  `status` int(11) DEFAULT NULL COMMENT '状态{0:无效,1:有效}',
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商城供货商';

-- ----------------------------
-- Table structure for zd_tour_grade
-- ----------------------------
DROP TABLE IF EXISTS `zd_tour_grade`;
CREATE TABLE `zd_tour_grade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `mobile_no` varchar(32) NOT NULL COMMENT '电话号码',
  `star_count` int(11) DEFAULT '0' COMMENT '评价分数',
  `content` text NOT NULL COMMENT '评价内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `guide_user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导游评论打分表';

-- ----------------------------
-- Table structure for zd_tour_guide
-- ----------------------------
DROP TABLE IF EXISTS `zd_tour_guide`;
CREATE TABLE `zd_tour_guide` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `guide_cover_img` longtext COMMENT '导游封面图',
  `guide_no` varchar(32) DEFAULT NULL COMMENT '导游证号',
  `guide_certificate_no` varchar(32) DEFAULT NULL COMMENT '导游资格证号',
  `tour_time` int(11) DEFAULT '0' COMMENT '带团时间(年)',
  `tour_rank` varchar(32) DEFAULT NULL COMMENT '导游等级{goldMedal:金牌,silverMedal:银牌,copperMedal:铜牌}',
  `permanent_city` varchar(32) DEFAULT NULL COMMENT '常驻城市',
  `province` varchar(255) DEFAULT NULL COMMENT '所在省市',
  `good_route` varchar(512) DEFAULT NULL COMMENT '擅长路线',
  `introduce_myself` varchar(512) DEFAULT NULL COMMENT '自我介绍',
  `speciality` varchar(128) DEFAULT NULL COMMENT '导游专长',
  `language` varchar(255) DEFAULT NULL COMMENT '语种',
  `price_per_day` bigint(20) DEFAULT '0' COMMENT '价格(天)',
  `star_level` int(11) DEFAULT NULL COMMENT '导游星级1-5级',
  `hot_guide` int(11) DEFAULT NULL COMMENT '是否为人气导游{1:是,0:否}',
  `rest_days` varchar(512) DEFAULT NULL COMMENT '导游休息日，逗号分隔的yyyy-MM-dd',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `guide_certificate_img` text COMMENT '导游资格证',
  `guide_level` varchar(255) DEFAULT 'general' COMMENT '导游等级{general:论坛成员,famous:名导}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COMMENT='导游信息表';

-- ----------------------------
-- Table structure for zd_trade_order_info
-- ----------------------------
DROP TABLE IF EXISTS `zd_trade_order_info`;
CREATE TABLE `zd_trade_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '会员ID',
  `out_user_id` varchar(64) NOT NULL COMMENT '银行会员ID',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `bank_order_no` varchar(64) DEFAULT NULL COMMENT '银行订单号',
  `trade_name` varchar(64) DEFAULT NULL COMMENT '交易名称',
  `trade_time` datetime NOT NULL COMMENT '交易时间',
  `finish_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `amount` bigint(20) NOT NULL COMMENT '订单金额',
  `order_trade_type` varchar(20) NOT NULL COMMENT '订单交易类型 {deposit:充值,withdraw:提现}',
  `order_sub_trade_type` varchar(20) DEFAULT NULL COMMENT '订单子交易类型 {weiScan:微信扫码,aliScan:支付宝扫码,withdraw:提现}',
  `order_status` varchar(20) NOT NULL COMMENT '订单状态 {pay:已支付,noPay:未支付,processing:支付中,fail:支付失败,close:已关闭,delete:已删除}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='交易订单表';

-- ----------------------------
-- Table structure for zd_trading_record
-- ----------------------------
DROP TABLE IF EXISTS `zd_trading_record`;
CREATE TABLE `zd_trading_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` varchar(64) NOT NULL COMMENT '用户ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `tourist_site` varchar(64) DEFAULT NULL COMMENT '旅游地点',
  `guide_name` varchar(64) DEFAULT NULL COMMENT '导游真实姓名',
  `trading_amount` bigint(20) DEFAULT '0' COMMENT '交易金额',
  `in_out_type` varchar(16) NOT NULL COMMENT '出入金类型 {in:收入,out:支出}',
  `balance` bigint(20) DEFAULT '0' COMMENT '余额',
  `trading_type` varchar(64) NOT NULL COMMENT '交易类型 {travel:旅游,deposit:充值,withdraw:提现}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='交易记录';

DROP TABLE IF EXISTS `zd_course`;
CREATE TABLE `zd_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '会员唯一标识',
  `course_price` bigint(20) DEFAULT '0' COMMENT '课程价格',
  `course_title` varchar(255) NOT NULL COMMENT '课程名称',
  `course_intro` text NOT NULL COMMENT '课程简介',
  `course_status` varchar(255) NOT NULL COMMENT '课程状态:{draft:草稿,published:已发布,deleted:已删除}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `publish_time` datetime DEFAULT NULL,
  `course_img` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zd_course_record`;
CREATE TABLE `zd_course_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL COMMENT '会员唯一标识',
  `course_id` bigint(20) DEFAULT NULL,
  `record_status` varchar(255) NOT NULL COMMENT '音频状态:{published:已发布,deleted:已删除}',
  `record_title` varchar(255) NOT NULL COMMENT '音频标题',
  `record_url` varchar(1024) NOT NULL COMMENT '音频地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zd_course_purchase`;
CREATE TABLE `zd_course_purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `course_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


--app新增表
-- ----------------------------
-- 旅声资源
-- ----------------------------
DROP TABLE IF EXISTS `zd_travel_resource`;
CREATE TABLE `zd_travel_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `travel_voice_id` bigint(20) NOT NULL COMMENT '旅声内容ID',
  `ofile_id` bigint(20) NOT NULL COMMENT '资源名称',
  `ofile_type` varchar(255) DEFAULT NULL COMMENT '资源类型 0-其他 1-图片 2-视频',
  `cover_url` varchar(255) DEFAULT NULL COMMENT '是否是封面',
  `resource_url` varchar(255) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 旅声
-- ----------------------------
DROP TABLE IF EXISTS `zd_travel_voice`;
CREATE TABLE `zd_travel_voice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `praise_count` bigint(20) DEFAULT '0' COMMENT '收藏数',
  `review_count` bigint(20) DEFAULT '0' COMMENT '评论数',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `position_name` varchar(255) DEFAULT NULL COMMENT '发布位置名称',
  `position_lat` varchar(64) DEFAULT NULL COMMENT '发布位置纬度',
  `position_lng` varchar(64) DEFAULT NULL COMMENT '发布位置经度',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 旅声评论
-- ----------------------------
DROP TABLE IF EXISTS `zd_travel_review`;
CREATE TABLE `zd_travel_review` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `travel_voice_id` bigint(11) NOT NULL COMMENT '旅声内容ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `review_parent_id` bigint(20) DEFAULT NULL COMMENT '评论父级ID',
  `content` longtext COMMENT '评论内容',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 旅声点赞
-- ----------------------------
DROP TABLE IF EXISTS `zd_travel_praise_log`;
CREATE TABLE `zd_travel_praise_log` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `travel_voice_id` bigint(11) NOT NULL COMMENT '旅声内容ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 用户关注
-- ----------------------------
DROP TABLE IF EXISTS `zd_customer_focus`;
CREATE TABLE `zd_customer_focus` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `focus_user_id` varchar(64) DEFAULT NULL COMMENT '关注用户ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 课程评论
-- ----------------------------
DROP TABLE IF EXISTS `zd_course_review`;
CREATE TABLE `zd_course_review` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) NOT NULL COMMENT '课程ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `review_parent_id` bigint(11) DEFAULT NULL COMMENT '评论父级ID',
  `content` longtext COMMENT '评论内容',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 关注课程
-- ----------------------------
DROP TABLE IF EXISTS `zd_course_focus`;
CREATE TABLE `zd_course_focus` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `focus_course_id` varchar(64) DEFAULT NULL COMMENT '关注课程ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 详情动态
-- ----------------------------
DROP TABLE IF EXISTS `zd_guide_message`;
CREATE TABLE `zd_guide_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL COMMENT '发布用户ID',
  `read_status` int(11) DEFAULT '0' COMMENT '查看状态 {1:已查看,0:未查看}',
  `message_type` varchar(255) DEFAULT NULL COMMENT '消息类型',
  `message_name` varchar(255) DEFAULT NULL COMMENT '消息类型名称',
  `title` longtext COMMENT '标题',
  `content_bom_id` varchar(255) DEFAULT NULL COMMENT '消息内容ID',
  `content_user_id` varchar(64) DEFAULT NULL COMMENT '内容作者id',
  `content` longtext COMMENT '消息内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--app新增表结束


