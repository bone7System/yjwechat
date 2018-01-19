-- liquibase formatted sql

-- changeset zhiyuan.huang:1

CREATE TABLE car (
  id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  license_plate VARCHAR(80)           NOT NULL UNIQUE
  COMMENT '车牌号',
  style         VARCHAR(80) COMMENT '车型',
  brand         VARCHAR(80) COMMENT '品牌',
  color         VARCHAR(80) COMMENT '颜色',
  seat          VARCHAR(80) COMMENT '座位数',
  buy_time      DATE COMMENT '购买日期',
  car_age       INT COMMENT '车龄',
  note          VARCHAR(1000) COMMENT '备注'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE org_car (
  id     BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_id BIGINT                NOT NULL COMMENT '车编号',
  org_id BIGINT                NOT NULL COMMENT '组织编号'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE org_car_responsible (
  id                  BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  org_id              BIGINT                NOT NULL COMMENT '组织编号',
  responsible_user_id BIGINT                NOT NULL COMMENT '责任领导编号'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

  CREATE TABLE org_car_manage (
  id                  BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  org_id              BIGINT                NOT NULL COMMENT '组织编号',
  manage_user_id      BIGINT                NOT NULL COMMENT '普通领导编号'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE car_status (
  id               BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_id           BIGINT                NOT NULL COMMENT '车编号',
  serviceability   VARCHAR(80)          NOT NULL COMMENT '车状态（空闲中，使用中，维修中）',
  early_warning    VARCHAR(80)          NOT NULL      COMMENT '预警（油耗预警，维修预警，正常，维修中，油耗预警与维修预警同时存在显示维修预警）',
  gas              VARCHAR(80)          COMMENT '油量',
  mileage          INT                   COMMENT '公里数',
  status           INT                    DEFAULT 1 COMMENT '状态1：正常，999删除',
  health_condition VARCHAR(80)           NOT NULL     COMMENT '车身状况（正常，异常但不需要维修，需维修）',
  damaged_part     VARCHAR(80)           NOT NULL     COMMENT '需要维修的部件（正常，车门，车窗，机头）',
  note             VARCHAR(255)         COMMENT '备注'

  )
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
-- 用车申请
CREATE TABLE car_application (
  id              BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_id          BIGINT                NOT NULL COMMENT '车编号',
  user_id         BIGINT                NOT NULL COMMENT '申请人编号',
  sp_user_id      BIGINT                          COMMENT '审批人编号',
  begin_time      DATETIME              NOT NULL COMMENT '用车时间',
  end_time        DATETIME                        COMMENT '还车时间',
  destination     VARCHAR(255)                   COMMENT '目的地',
  reason          VARCHAR(255)          NOT NULL COMMENT '事由',
  apply_time      DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  status          VARCHAR(80)           NOT NULL COMMENT '状态（已提交，已审批，已取车，待还车，已还车,已取消，已驳回）',
  get_car_code    VARCHAR(80) COMMENT '取车码',
  return_car_code VARCHAR(80) COMMENT '还车码',
  get_car_time    DATETIME COMMENT '取车时间',
  return_car_time DATETIME COMMENT '还车时间',
  cross_border    boolean  NOT NULL COMMENT '是否跨部门申请（true:是,false:否）',
  update_time     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 用车申请流程表
CREATE TABLE car_application_event (
  id                 BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_application_id BIGINT                NOT NULL  COMMENT '申请编号',
  user_id            BIGINT                NOT NULL  COMMENT '操作人编号',
  operation          VARCHAR(80)           NOT NULL  COMMENT '操作(民警发起申请，民警取消申请，单位领导审批，民警取车，民警填写车辆归还信息，民警还车)',
  status             VARCHAR(80)           NOT NULL  COMMENT '状态(已提交，已审批，已取车，待还车，已还车，已取消，已驳回)',
  approval_level INT COMMENT '当前是第几级审批',
  approval_user_name VARCHAR(255) COMMENT '审批人姓名',
  next_approval_user_list VARCHAR(255) COMMENT '下一级审批人列表',
  time               DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '操作时间'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 车况信息补录
CREATE TABLE car_application_note (
  id                 BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_application_id BIGINT                COMMENT '申请编号',
  car_repair_id      BIGINT                COMMENT '维修编号',
  operation          VARCHAR(80)           NOT NULL  COMMENT '操作（补录，还车，维修）',
  health_condition   VARCHAR(80)           NOT NULL  COMMENT '车身状况（正常，异常但不需要维修，需维修）',
  damaged_part       VARCHAR(80
                     )                       COMMENT '需要维修的部件（正常，车门，车窗，机头）',
  note               VARCHAR(255
                     )                       COMMENT '备注',
  image              LONGTEXT   COMMENT '图片路径',
  time               DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '提交时间'

)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE car_repair (
  id           BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_id       BIGINT                NOT NULL  COMMENT '车编号',
  user_id      BIGINT                NOT NULL  COMMENT '维修人编号',
  damaged_part VARCHAR(80)           NOT NULL  COMMENT '需要维修的部件（正常，车门，车窗，机头）',
  begin_time   DATETIME               NOT NULL  DEFAULT CURRENT_TIMESTAMP  COMMENT '维修开始时间',
  end_time     DATETIME               COMMENT '维修结束时间',
  status       VARCHAR(80)           NOT NULL  COMMENT '维修状态（已提交，维修中，待还车，已维修）',
  get_car_code    VARCHAR(80)       COMMENT '取车码',
  return_car_code VARCHAR(80)       COMMENT '还车码',
  update_time     DATETIME          DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
-- 车辆维修流程表
CREATE TABLE car_repair_event (
  id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_repair_id BIGINT                NOT NULL COMMENT '维修编号',
  user_id       BIGINT                NOT NULL COMMENT '用户编号',
  operation     VARCHAR(80)           NOT NULL COMMENT '操作（维修人员提交维修信息，获得取车码；维修人员已取车，车辆正常维护中；维修人员填写车辆信息，获得还车码；车辆已归位，维修完毕）',
  status        VARCHAR(80)           NOT NULL COMMENT '状态（已提交，维修中，待还车，已维修）',
  time          DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  -- 消息发送表
CREATE TABLE application_message (
  id                 BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  send_user_id       BIGINT                COMMENT '发送人编号',
  car_application_id BIGINT                COMMENT '申请编号',
  receive_user_id    BIGINT                COMMENT '接收人编号',
  note               VARCHAR(80)          COMMENT '操作',
  time               DATETIME              NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '提交时间'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


