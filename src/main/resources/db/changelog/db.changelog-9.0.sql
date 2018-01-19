-- liquibase formatted sql

-- changeset zhiyuan.huang:1

CREATE TABLE car_track_device
(
  id        BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_id    BIGINT                NOT NULL,
  device_sn VARCHAR(64) COMMENT '鹰眼设备序列号'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '百度鹰眼设备';
