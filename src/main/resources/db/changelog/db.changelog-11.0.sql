-- liquibase formatted sql

-- changeset zhiyuan.huang:1

CREATE TABLE app_version
(
  id       VARCHAR(255) NOT NULL PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  version  VARCHAR(255) NOT NULL,
  url      VARCHAR(255) NOT NULL,
  md5      VARCHAR(255) NOT NULL,
  service  VARCHAR(255),
  activity VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT 'APP 更新信息';
