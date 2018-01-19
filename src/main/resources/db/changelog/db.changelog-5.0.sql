-- liquibase formatted sql
-- changeset zhiyuan.huang:1
CREATE VIEW car_org_view AS
  SELECT
    r.*,
    o.orgname,
    o.appid
  FROM (SELECT *
        FROM org_car
        WHERE id IN (SELECT max(id)
                     FROM org_car
                     GROUP BY car_id)) r LEFT JOIN organization o ON o.id = r.org_id;


CREATE VIEW user_org_view AS
  SELECT
    r.*,
    o.orgname,
    o.appid
  FROM (SELECT *
        FROM org_user
        WHERE id IN (SELECT max(id)
                     FROM org_user
                     GROUP BY user_id)) r LEFT JOIN organization o ON o.id = r.org_id;

CREATE VIEW count_car_kxz AS
select o.org_id, count(*) num from car_status c
left join org_car o on c.car_id = o.car_id
where c.serviceability = '空闲中' and c.status = 1
GROUP BY o.org_id;

CREATE VIEW count_car_syz AS
select o.org_id, count(*) num from car_status c
left join org_car o on c.car_id = o.car_id
where c.serviceability = '使用中' and c.status = 1
GROUP BY o.org_id;

CREATE VIEW count_car_wxz AS
select o.org_id, count(*) num from car_status c
left join org_car o on c.car_id = o.car_id
where c.serviceability = '维修中' and c.status = 1
GROUP BY o.org_id;

CREATE VIEW count_car_yj AS
select o.org_id, count(*) num from car_status c
left join org_car o on c.car_id = o.car_id
where c.early_warning != '正常' and c.status = 1
GROUP BY o.org_id;

CREATE VIEW sel_car_fzrname AS
SELECT
  oc.car_id,
  GROUP_CONCAT(DISTINCT t1.name ORDER BY t1.name DESC) fzrname
FROM org_car oc
  LEFT JOIN
  (SELECT
     o.org_id,
     p.name
   FROM org_car_responsible o
     LEFT JOIN profile p
       ON p.user_id = o.responsible_user_id
    LEFT JOIN user u
       ON u.id = o.responsible_user_id
   WHERE u.status != 999
   ORDER BY o.org_id, p.name
  ) t1
    ON t1.org_id = oc.org_id
GROUP BY oc.car_id;

CREATE VIEW sel_org_chargehandname AS
SELECT
  o.id,
  IFNULL(GROUP_CONCAT(DISTINCT t1.user_id ORDER BY t1.name DESC), '') chargehandid,
  IFNULL(GROUP_CONCAT(DISTINCT t1.name ORDER BY t1.name DESC), '') chargehandname
FROM organization o
  LEFT JOIN
  (SELECT
     o.org_id,
     p.name,
    p.user_id
   FROM org_car_manage o
     LEFT JOIN profile p
       ON p.user_id = o.manage_user_id
     LEFT JOIN user u
       ON u.id = o.manage_user_id
   WHERE u.status != 999
   ORDER BY o.org_id, p.name
  ) t1
    ON t1.org_id = o.id
GROUP BY o.id;

CREATE VIEW sel_org_leadername AS
SELECT
  o.id,
  IFNULL(GROUP_CONCAT(DISTINCT t1.user_id ORDER BY t1.name DESC), '') leaderid,
  IFNULL(GROUP_CONCAT(DISTINCT t1.name ORDER BY t1.name DESC), '') leadername
FROM organization o
  LEFT JOIN
  (SELECT
     o.org_id,
     p.name,
     p.user_id
   FROM org_car_responsible o
     LEFT JOIN profile p
       ON p.user_id = o.responsible_user_id
    LEFT JOIN user u
       ON u.id = o.responsible_user_id
   WHERE u.status != 999
   ORDER BY o.org_id, p.name
  ) t1
    ON t1.org_id = o.id
GROUP BY o.id;

CREATE VIEW sel_org_adminname AS
SELECT
  o.id,
  IFNULL(GROUP_CONCAT(DISTINCT t1.user_id ORDER BY t1.name DESC), '') adminid,
  IFNULL(GROUP_CONCAT(DISTINCT t1.name ORDER BY t1.name DESC), '') adminname
FROM organization o
  LEFT JOIN
  (SELECT
     o.org_id,
     p.name,
     p.user_id
   FROM org_user o
     LEFT JOIN profile p
       ON p.user_id = o.user_id
     LEFT JOIN user u
       ON u.id = o.user_id
   left join user_role r on u.id = r.user_id
   WHERE u.status != 999
    and r.role_id = 2
   ORDER BY o.org_id, p.name
  ) t1
    ON t1.org_id = o.id
GROUP BY o.id;

CREATE VIEW sel_system_adminname AS
SELECT
  o.appid,
  IFNULL(GROUP_CONCAT(DISTINCT t1.user_id ORDER BY t1.name DESC), '') adminid,
  IFNULL(GROUP_CONCAT(DISTINCT t1.name ORDER BY t1.name DESC), '') adminname
FROM organization o
  LEFT JOIN
  (SELECT
     o.org_id,
     p.name,
     p.user_id
   FROM org_user o
     LEFT JOIN profile p
       ON p.user_id = o.user_id
     LEFT JOIN user u
       ON u.id = o.user_id
   left join user_role r on u.id = r.user_id
   WHERE u.status != 999
    and r.role_id = 2
   ORDER BY o.org_id, p.name
  ) t1
    ON t1.org_id = o.id
GROUP BY o.appid;

CREATE VIEW sel_user_organization AS
SELECT
  u.id,
  GROUP_CONCAT(o.id ORDER BY o.id ) orgid,
  GROUP_CONCAT(o.orgname ORDER BY o.id ) orgname
FROM user u LEFT JOIN (
                        SELECT
                          ou.user_id,
                          o.id,
                          o.orgname
                        FROM org_user ou
                          LEFT JOIN organization o ON o.id = ou.org_id
                      ) o ON u.id = o.user_id
GROUP BY u.id;

CREATE VIEW sel_car_organization AS
SELECT
  c.id,
  GROUP_CONCAT(o.id ORDER BY o.id ) orgid,
  GROUP_CONCAT(o.orgname ORDER BY o.id ) orgname
FROM car c LEFT JOIN (
                        SELECT
                          oc.car_id,
                          o.id,
                          o.orgname
                        FROM org_car oc
                          LEFT JOIN organization o ON o.id = oc.org_id
                      ) o ON c.id = o.car_id
GROUP BY c.id;




