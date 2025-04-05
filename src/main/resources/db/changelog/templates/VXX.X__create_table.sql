--liquibase formatted sql


--------------------------------------------------------
--  DDL for Table <TABLE_NAME>
--------------------------------------------------------


--changeset liquibase:vXX.X_create_<seq_table_name>
CREATE SEQUENCE "<SEQ_TABLE_NAME>" START WITH 1 INCREMENT BY 1;
--rollback DROP "SEQUENCE "<SEQ_TABLE_NAME>";


--changeset liquibase:vXX.X_create_<table_name>
CREATE TABLE "<TABLE_NAME>" (
	"ID"			INTEGER			DEFAULT "<SEQ_TABLE_NAME>".NEXTVAL		NOT NULL,
	"NAME"			VARCHAR2												NOT NULL
);
--rollback DROP TABLE "<TABLE_NAME>";


--changeset liquibase:vXX.X_create_<pk_table_name>
ALTER TABLE "<TABLE_NAME>"
ADD CONSTRAINT "<PK_TABLE_NAME>"
PRIMARY KEY ("ID");
--rollback ALTER TABLE "<TABLE_NAME>" DROP CONSTRAINT "<PK_TABLE_NAME>";
