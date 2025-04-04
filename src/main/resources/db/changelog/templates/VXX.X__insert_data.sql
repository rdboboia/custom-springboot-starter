--liquibase formatted sql

--------------------------------------------------------
--  DATA for Table <TABLE_NAME>
--------------------------------------------------------

--changeset liquibase:vXX.X_insert_data_<table_name>
INSERT INTO "<TABLE_NAME>" VALUES ("<SEQ_TABLE_NAME>".nextval,    'Name1');
INSERT INTO "<TABLE_NAME>" VALUES ("<SEQ_TABLE_NAME>".nextval,    'Name2');
INSERT INTO "<TABLE_NAME>" VALUES ("<SEQ_TABLE_NAME>".nextval,    'Name3');
--rollback DELETE FROM "<TABLE_NAME>" WHERE "ID" IN (SELECT "ID" FROM "<TABLE_NAME>" ORDER BY "ID" DESC FETCH FIRST 3 ROWS ONLY);
--rollback ALTER SEQUENCE "<SEQ_TABLE_NAME>" INCREMENT BY -3;
--rollback SELECT "<SEQ_TABLE_NAME>".nextval FROM DUAL;
--rollback ALTER SEQUENCE "<SEQ_TABLE_NAME>" INCREMENT BY 1;
