--liquibase formatted sql

--------------------------------------------------------
--  DDL for Table REQUEST
--------------------------------------------------------

--changeset liquibase:v05.0_create_seq_request
CREATE SEQUENCE "SEQ_REQUEST" START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE "SEQ_REQUEST";


--changeset liquibase:v05.0_create_table_request
CREATE TABLE "REQUEST" (
	"ID"			INTEGER			DEFAULT "SEQ_REQUEST".NEXTVAL		NOT NULL,
	"DATA"			VARCHAR2											NOT NULL
);
--rollback DROP TABLE "REQUEST";


--changeset liquibase:v05.0_create_pk_request
ALTER TABLE "REQUEST"
ADD CONSTRAINT "PK_REQUEST"
PRIMARY KEY ("ID");
--rollback ALTER TABLE "REQUEST" DROP CONSTRAINT "PK_REQUEST";
