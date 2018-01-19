-- liquibase formatted sql
-- changeset me:test1 runOnChange:true endDelimiter:#

DROP PROCEDURE IF EXISTS createChildLst;
#
CREATE PROCEDURE createChildLst (IN rootId INT,IN nDepth INT)
BEGIN
  DECLARE done INT DEFAULT 0;
  DECLARE b INT;
  DECLARE cur1 CURSOR FOR SELECT id FROM organization where pid=rootId;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

  insert into tmpLst values (null,rootId,nDepth);


  OPEN cur1;

  FETCH cur1 INTO b;
  WHILE done=0 DO
          CALL createChildLst(b,nDepth+1);
          FETCH cur1 INTO b;
  END WHILE;

  CLOSE cur1;
END
#

DROP PROCEDURE IF EXISTS showChildLst;
#
CREATE PROCEDURE showChildLst (IN rootId INT, IN appId VARCHAR(50))
BEGIN
  CREATE TEMPORARY TABLE IF NOT EXISTS tmpLst (sno int primary key auto_increment,id int,depth int);
  DELETE FROM tmpLst;

  CALL createChildLst(rootId,0);

  select tmpLst.*,organization.* from tmpLst,organization where tmpLst.id=organization.id  and organization.appid=appId and organization.status=1 order by tmpLst.sno;
END
#

-- #
-- set max_sp_recursion_depth =4;
-- call showChildLst(1, 'tysqyh');
-- #