
-- CK本地库新建表 【sku禁售平台风险等级表】
CREATE TABLE imdb.syn_yibai_prod_forbidden_grade (
id Int64 COMMENT 'ID',
sku String  COMMENT 'sku',
platform_code String  NOT NULL DEFAULT '' COMMENT '平台编码 参考平台表',
risk_grade String NOT NULL DEFAULT '暂无风险' COMMENT '风险等级：V：特别重大风险（易引起诉讼案件）, IV：重大风险,III：较高风险,II：一般风险,I：低风险,0：暂无风险',
risk_grade_type String NOT NULL DEFAULT '' COMMENT '风险等级类型：V：特别重大风险（易引起诉讼案件）, IV：重大风险,III：较高风险,II：一般风险,I：低风险,0：暂无风险',
create_time datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
create_user String NOT NULL DEFAULT '' COMMENT '创建人',
update_user String NOT NULL DEFAULT '' COMMENT '更新人',
update_time datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
is_del Int32 NOT NULL DEFAULT '0' COMMENT '是否删除，0未删除，1删除 默认0',
reason String NOT NULL DEFAULT '' COMMENT '禁售原因',
forbidden_source Int64  NOT NULL DEFAULT '0' COMMENT '禁售来源id',
forbidden_data_type Int32 NOT NULL DEFAULT '0' COMMENT '数据来源 0:产品系统 1:小盾科技'
)
    ENGINE = MergeTree()
ORDER BY (id, platform_code ,create_user, update_user, create_time,update_time)
COMMENT 'sku禁售平台风险等级表';