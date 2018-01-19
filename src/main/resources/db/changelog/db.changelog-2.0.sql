-- liquibase formatted sql

-- changeset zhiyuan.huang:1
CREATE TABLE profile
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  user_id      BIGINT                NOT NULL,
  mobile       VARCHAR(32) COMMENT '手机号码',
  identity    VARCHAR(200) DEFAULT '民警' COMMENT '身份标识',
  sex         BIGINT COMMENT '性别（0 男,1 女）',
  name        VARCHAR(100) COMMENT '姓名',
  avatar      VARCHAR(256) COMMENT '用户头像',
  policeno    VARCHAR(32) COMMENT '警号',
  position    VARCHAR(200) COMMENT '职位',
  remark      VARCHAR(200) COMMENT '备注',
  leader      BIGINT DEFAULT 0
  COMMENT '0,非领导；1,领导',
  sort        BIGINT DEFAULT 1000000
  COMMENT '序号，排序，越小职位越大',
  update_time DATETIME ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后一次修改时间'
)
  COMMENT '用户画像'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE organization
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  pid         BIGINT                NOT NULL
  COMMENT '父机构ID，根机构的父ID设为-1',
  orgname     VARCHAR(128)          NOT NULL
  COMMENT '机构名称',
  orgdesc     VARCHAR(255) COMMENT '机构描述',
  orgcode     VARCHAR(128) COMMENT '机构编码',
  keyword     VARCHAR(200) COMMENT '关键字',
  status      INT COMMENT '状态1，可用，999，异常',
  leader_id   BIGINT COMMENT '领导编号',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME ON UPDATE CURRENT_TIMESTAMP,
  appid       VARCHAR(200)    COMMENT '企业号标识，用来区分企业号'
)
  COMMENT '组织机构'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE org_user(
 id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
 user_id      BIGINT                NOT NULL,
 org_id      BIGINT                NOT NULL
)
COMMENT '用户组织机构关联表'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE org_role (
 id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
 role_id     BIGINT                NOT NULL,
 org_id      BIGINT                NOT NULL
)
COMMENT '角色组织机构关联表，组织id是每个派出所的根节点'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE dictionary_type
(
  id          INT COMMENT '编号',
  type        VARCHAR(50)  NOT NULL
  COMMENT '字典类别',
  ptype       VARCHAR(50) COMMENT '父类别',
  name        VARCHAR(500) NOT NULL
  COMMENT '列表名称',
  pinyin      VARCHAR(500) COMMENT '拼音缩写',
  note        VARCHAR(500) COMMENT '备注',
  source      VARCHAR(50) COMMENT '来源, 如：自定义',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME ON UPDATE CURRENT_TIMESTAMP
)
  COMMENT '字典类别信息'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE dictionary
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
  COMMENT '字典编号',
  type        VARCHAR(50)           NOT NULL
  COMMENT '字典类别',
  pcode       VARCHAR(50) COMMENT '父字典编码',
  code        VARCHAR(50)           NOT NULL
  COMMENT '字典编码',
  value       VARCHAR(500)          NOT NULL
  COMMENT '字典值',
  pinyin      VARCHAR(500) COMMENT '拼音缩写',
  sort        VARCHAR(50) COMMENT '排序号',
  note        VARCHAR(500) COMMENT '备注',
  source      VARCHAR(50) COMMENT '来源, 如：自定义',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME ON UPDATE CURRENT_TIMESTAMP
)
  COMMENT '字典信息'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


  CREATE TABLE dic_dictype (
  id                  BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  dictionary_type_id      BIGINT                NOT NULL COMMENT '字典类型编号',
  dictionary_id              BIGINT                NOT NULL COMMENT '字典编号'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;