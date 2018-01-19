-- liquibase formatted sql

-- changeset author0:1
CREATE TABLE user (
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  -- username    VARCHAR(80)           NOT NULL UNIQUE,    -- 由于手机号做为用户名，很多手机号重复，顾暂时设置用户名可以不唯一
  username    VARCHAR(80)           NOT NULL,
  password    VARCHAR(255),
  status      INT DEFAULT 1 COMMENT '用户状态（1:正常，0：锁定，999:不可用）',
  mobile      VARCHAR(11),
  email       VARCHAR(80),
  enabled     BOOLEAN DEFAULT TRUE,
  wechat      VARCHAR(80),
  create_time TIMESTAMP             NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP             NOT NULL DEFAULT CURRENT_TIMESTAMP
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE role (
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  status      INT DEFAULT 1 COMMENT '用户状态（1:正常，999:不可用）',
  role_name   VARCHAR(80)           NOT NULL UNIQUE,
  description VARCHAR(255),
  issystem    INT DEFAULT 0 COMMENT '是否系统角色（1:是，0:不是）'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE permission (
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  domain      VARCHAR(80)           NOT NULL,
  permission  VARCHAR(80)           NOT NULL,
  description VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user_role (
  id      BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE role_permission (
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  role_id       BIGINT NOT NULL,
  permission_id BIGINT NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE SPRING_SESSION (
  SESSION_ID CHAR(36) NOT NULL,
  CREATION_TIME BIGINT NOT NULL,
  LAST_ACCESS_TIME BIGINT NOT NULL,
  MAX_INACTIVE_INTERVAL INT NOT NULL,
  PRINCIPAL_NAME VARCHAR(100),
  CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (SESSION_ID)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (LAST_ACCESS_TIME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
  SESSION_ID CHAR(36) NOT NULL,
  ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
  ATTRIBUTE_BYTES BLOB NOT NULL,
  CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_ID, ATTRIBUTE_NAME),
  CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_ID) REFERENCES SPRING_SESSION(SESSION_ID) ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX SPRING_SESSION_ATTRIBUTES_IX1 ON SPRING_SESSION_ATTRIBUTES (SESSION_ID);

-- changeset author0:2

INSERT INTO user (id, username, password, enabled, status) VALUES (1, 'sysadmin', '$2a$06$Y782N6ZiPdEOAAFAgqqMze0ak2DiPWDn4z5MypfRGzaLLvpZndhWS', TRUE, 1);

INSERT INTO role (id, role_name, description, issystem) VALUES (1, 'system', '超级管理员', 1);        -- 超级管理员（管理所有派出所的所有权限）
INSERT INTO role (id, role_name, description, issystem) VALUES (2, 'admin', '车管员', 1);             -- 车管员（系统管理权限，审批所有部门的车辆）
INSERT INTO role (id, role_name, description, issystem) VALUES (3, 'police', '普通民警', 1);            -- 普通民警，只有申请权限
INSERT INTO role (id, role_name, description, issystem) VALUES (4, 'leader', '领导', 1);            -- 领导，申请，审批权限

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

INSERT INTO permission (id, domain, permission, description) VALUES (1, 'system', 'admin', '系统管理权限');
INSERT INTO permission (id, domain, permission, description) VALUES (2, 'user', 'admin', '用户操作权限');
INSERT INTO permission (id, domain, permission, description) VALUES (3, 'user', 'view', '用户查看权限');
INSERT INTO permission (id, domain, permission, description) VALUES (4, 'apply', 'admin', '申请操作权限');
INSERT INTO permission (id, domain, permission, description) VALUES (5, 'apply', 'view', '申请查看权限');
INSERT INTO permission (id, domain, permission, description) VALUES (6, 'approve', 'admin', '审批操作权限');
INSERT INTO permission (id, domain, permission, description) VALUES (7, 'approve', 'view', '审批查看权限');
INSERT INTO permission (id, domain, permission, description) VALUES (8, 'repair', 'admin', '维修操作权限');
INSERT INTO permission (id, domain, permission, description) VALUES (9, 'repair', 'view', '维修查看权限');
INSERT INTO permission (id, domain, permission, description) VALUES (10, 'cabinet', 'admin', '智能柜管理');
INSERT INTO permission (id, domain, permission, description) VALUES (11, 'car', 'admin', '车辆操作权限');
INSERT INTO permission (id, domain, permission, description) VALUES (12, 'car', 'view', '车辆查看权限');
INSERT INTO permission (id, domain, permission, description) VALUES (13, 'org', 'admin', '组织操作权限');
INSERT INTO permission (id, domain, permission, description) VALUES (14, 'org', 'view', '组织查看权限');

INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 4);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 5);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 6);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 7);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 8);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 9);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 10);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 11);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 12);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 13);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 14);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 4);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 5);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 12);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 14);
INSERT INTO role_permission (role_id, permission_id) VALUES (4, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (4, 4);
INSERT INTO role_permission (role_id, permission_id) VALUES (4, 5);
INSERT INTO role_permission (role_id, permission_id) VALUES (4, 6);
INSERT INTO role_permission (role_id, permission_id) VALUES (4, 7);
INSERT INTO role_permission (role_id, permission_id) VALUES (4, 12);



