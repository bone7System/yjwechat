-- liquibase formatted sql

-- changeset zhiyuan.huang:1

CREATE TABLE side_cabinet
(
  id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  cabinet_id    BIGINT                NOT NULL,
  install_order INT COMMENT '主副柜， 0 表示主柜，1表示右一的副柜，-1表示左一的副柜',
  start_number  INT COMMENT '箱子起始编号',
  box_count     INT COMMENT '箱子个数'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '智能柜主副柜信息';
