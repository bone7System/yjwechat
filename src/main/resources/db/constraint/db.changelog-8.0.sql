-- liquibase formatted sql

-- changeset zhiyuan.huang:1
ALTER TABLE org_car
ADD CONSTRAINT fk_org_car_org
FOREIGN KEY (org_id)
REFERENCES organization(id);

ALTER TABLE org_car
ADD CONSTRAINT fk_org_car_car
FOREIGN KEY (car_id)
REFERENCES car(id);


ALTER TABLE org_user
ADD CONSTRAINT fk_org_user_org
FOREIGN KEY (org_id)
REFERENCES organization(id);

ALTER TABLE org_user
ADD CONSTRAINT fk_org_car_user
FOREIGN KEY (user_id)
REFERENCES user(id);

ALTER TABLE org_car_manage
ADD CONSTRAINT fk_org_car_manage_org
FOREIGN KEY (org_id)
REFERENCES organization(id);

ALTER TABLE org_car_manage
ADD CONSTRAINT fk_org_car_manage_user
FOREIGN KEY (manage_user_id)
REFERENCES user(id);

ALTER TABLE org_car_responsible
ADD CONSTRAINT fk_org_car_responsible_org
FOREIGN KEY (org_id)
REFERENCES organization(id);

ALTER TABLE org_car_responsible
ADD CONSTRAINT fk_org_car_responsible_user
FOREIGN KEY (responsible_user_id)
REFERENCES user(id);

ALTER TABLE car_application
ADD CONSTRAINT fk_car_application_car
FOREIGN KEY (car_id)
REFERENCES car(id);

ALTER TABLE car_application
ADD CONSTRAINT fk_car_application_user
FOREIGN KEY (user_id)
REFERENCES user(id);

ALTER TABLE car_application_note
ADD CONSTRAINT fk_car_application_note_car_application
FOREIGN KEY (car_application_id)
REFERENCES car_application(id);

ALTER TABLE car_application_note
ADD CONSTRAINT fk_car_application_note_car_repair
FOREIGN KEY (car_repair_id)
REFERENCES car_repair(id);

ALTER TABLE car_application_event
ADD CONSTRAINT fk_car_application_event_car_application
FOREIGN KEY (car_application_id)
REFERENCES car_application(id);

ALTER TABLE car_application_event
ADD CONSTRAINT fk_car_application_event_user
FOREIGN KEY (user_id)
REFERENCES user(id);

ALTER TABLE car_repair
ADD CONSTRAINT fk_car_repair_car
FOREIGN KEY (car_id)
REFERENCES car(id);

ALTER TABLE car_repair
ADD CONSTRAINT fk_car_repair_user
FOREIGN KEY (user_id)
REFERENCES user(id);


ALTER TABLE car_repair_event
ADD CONSTRAINT fk_car_repair_event_car_repair
FOREIGN KEY (car_repair_id)
REFERENCES car_repair(id);

ALTER TABLE car_repair_event
ADD CONSTRAINT fk_car_repair_event_user
FOREIGN KEY (user_id)
REFERENCES user(id);

ALTER TABLE car_status
ADD CONSTRAINT fk_car_status_car
FOREIGN KEY (car_id)
REFERENCES car(id);

ALTER TABLE user_role
ADD CONSTRAINT fk_user_role_user
FOREIGN KEY (user_id)
REFERENCES user(id);

ALTER TABLE user_role
ADD CONSTRAINT fk_user_role_role
FOREIGN KEY (role_id)
REFERENCES role(id);

ALTER TABLE role_permission
ADD CONSTRAINT fk_role_permission_role
FOREIGN KEY (role_id)
REFERENCES role(id);

ALTER TABLE role_permission
ADD CONSTRAINT fk_role_permission_permission
FOREIGN KEY (permission_id)
REFERENCES permission(id);

ALTER TABLE profile
ADD CONSTRAINT fk_profile_user
FOREIGN KEY (user_id)
REFERENCES user(id);

ALTER TABLE cabinet
ADD CONSTRAINT fk_cabinet_org
FOREIGN KEY (org_id)
REFERENCES organization(id);

ALTER TABLE car_cabinet_box
ADD CONSTRAINT fk_car_cabinet_box_car
FOREIGN KEY (car_id)
REFERENCES car(id);

ALTER TABLE car_cabinet_box
ADD CONSTRAINT fk_car_cabinet_box_cabinet
FOREIGN KEY (cabinet_id)
REFERENCES cabinet(id);

ALTER TABLE application_message
ADD CONSTRAINT fk_application_message_send_user
FOREIGN KEY (send_user_id)
REFERENCES user(id);

ALTER TABLE application_message
ADD CONSTRAINT fk_application_message_receive_user
FOREIGN KEY (receive_user_id)
REFERENCES user(id);

ALTER TABLE application_message
ADD CONSTRAINT fk_application_message_application
FOREIGN KEY (car_application_id)
REFERENCES car_application(id);