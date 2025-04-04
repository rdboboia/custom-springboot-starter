--liquibase formatted sql

--------------------------------------------------------
--  DDL for Table PRODUCT_TYPE
--------------------------------------------------------

--changeset liquibase:vXX.X_create_seq_table_name
CREATE SEQUENCE "SEQ_TABLE_NAME" START WITH 1 INCREMENT BY 1;
--rollback DROP "SEQUENCE "SEQ_TABLE_NAME";


--changeset liquibase:vXX.X_create_table_name
CREATE TABLE "TABLE_NAME" (
	"ID"			INTEGER			DEFAULT "SEQ_TABLE_NAME".NEXTVAL		NOT NULL,
	"NAME"			VARCHAR2												NOT NULL
);
--rollback DROP TABLE "TABLE_NAME";


--changeset liquibase:vXX.X_create_pk_table_name
ALTER TABLE "TABLE_NAME"
ADD CONSTRAINT "PK_TABLE_NAME"
PRIMARY KEY ("ID");
--rollback ALTER TABLE "TABLE_NAME" DROP CONSTRAINT "PK_TABLE_NAME";
