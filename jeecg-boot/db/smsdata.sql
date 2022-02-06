--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `sms_producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_producer` (
  `producer_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '供应商名称',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '供应商编码',
  `address` varchar(1024) NOT NULL DEFAULT '' COMMENT '供应商地址',
  `type` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '供应商类型：0.线下，1.线上，2.代工，3中间商',
  `platform` varchar(255) NOT NULL DEFAULT '' COMMENT '线上平台',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用，1是，0否',
  `pay_type` tinyint(2) NOT NULL COMMENT '付款方式',
  `max_money` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '限制月最大采购金额，0不限制',
  `bank` varchar(255) NOT NULL DEFAULT '' COMMENT '银行',
  `bank_address` varchar(1024) NOT NULL DEFAULT '' COMMENT '银行地址',
  `bank_user` varchar(128) NOT NULL DEFAULT '' COMMENT '户名',
  `account` varchar(32) NOT NULL DEFAULT '' COMMENT '银行账号',
  `before_pay` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '预付款，百分比',
  `credit_day` smallint(3) unsigned NOT NULL DEFAULT '0' COMMENT '账期天数',
  `site` varchar(255) NOT NULL DEFAULT '' COMMENT '网站',
  `is_invoice` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否可开发票',
  `is_free_tax` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否包税',
  `total_price` decimal(15,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '共采购金额',
  `delivery_day` smallint(3) unsigned NOT NULL DEFAULT '0' COMMENT '交货时间（天）',
  `quality` smallint(3) unsigned NOT NULL DEFAULT '1' COMMENT '供应商质量分数',
  `main_product` varchar(1024) NOT NULL DEFAULT '' COMMENT '主营商品',
  `contacts` varchar(128) NOT NULL DEFAULT '' COMMENT '联系人',
  `mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '手机',
  `phone` varchar(32) NOT NULL DEFAULT '' COMMENT '电话',
  `wangwang` varchar(255) NOT NULL DEFAULT '' COMMENT '阿里旺旺',
  `qq` varchar(32) NOT NULL DEFAULT '' COMMENT 'qq',
  `spare_contacts` varchar(128) NOT NULL DEFAULT '' COMMENT '备用联系人',
  `spare_mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '备用联系人手机',
  `spare_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '备用联系人电话',
  `spare_qq` varchar(32) NOT NULL DEFAULT '' COMMENT '备用联系人qq',
  `sort` tinyint(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `certificate` varchar(1024) NOT NULL DEFAULT '' COMMENT '供应商资质',
  `remark` text COMMENT '备注信息',
  `ext_json` json DEFAULT NULL COMMENT '扩展字段',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_id` int(11) unsigned DEFAULT '0' COMMENT '创建人id',
  `update_by` varchar(64) DEFAULT '' COMMENT '修改人',
  `update_id` int(11) unsigned DEFAULT '0' COMMENT '修改人id',
  PRIMARY KEY (`producer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='供应商表';
/*!40101 SET character_set_client = @saved_cs_client */;
