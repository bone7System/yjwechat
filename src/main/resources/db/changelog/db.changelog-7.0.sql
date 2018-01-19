-- liquibase formatted sql

-- changeset zhiyuan.huang:1

CREATE TABLE cabinet
(
  id               BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  org_id           BIGINT                NOT NULL
  COMMENT '所属组织机构ID',
  name             VARCHAR(128) COMMENT '智能柜名称',
  manufacturer     VARCHAR(128) COMMENT '厂家',
  model            VARCHAR(128) COMMENT '型号',
  box_count        INT(38) COMMENT '收件箱个数',
  install_location VARCHAR(255) COMMENT '安装地址',
  longitude        VARCHAR(32) COMMENT '经度',
  latitude         VARCHAR(32) COMMENT '纬度',
  auth_code        CHAR(8) COMMENT '连接码，设备登录时，发送此码验证身份',
  status           INT      DEFAULT 1
  COMMENT '用户状态（1:正常，999:不可用）',
  create_time      DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time      DATETIME ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '智能柜';

CREATE TABLE car_cabinet_box
(
  id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_id     BIGINT,
  cabinet_id BIGINT                NOT NULL,
  box_number INT                   NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '钥匙与智能柜盒子的关联表';


