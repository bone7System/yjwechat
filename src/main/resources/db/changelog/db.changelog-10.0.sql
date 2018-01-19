-- liquibase formatted sql

-- changeset zhiyuan.huang:1

CREATE TABLE car_approval
(
  id        BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  car_id    BIGINT                NOT NULL,
  approval_user_id BIGINT COMMENT '审批人 id',
  approval_role_id BIGINT COMMENT '审批角色 id',
  level INT COMMENT '多级审批的顺序，数字小的先审批'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '车的审批人设置';


  CREATE TABLE car_apply (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  car_id bigint(20) NOT NULL,
  apply_org_id bigint(20) DEFAULT NULL COMMENT '部门 id',
  PRIMARY KEY (id)
  ) ENGINE=InnoDB
 DEFAULT CHARSET=utf8
  COMMENT='申请部门设置';

