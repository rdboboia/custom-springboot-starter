--liquibase formatted sql

--------------------------------------------------------
--  DDL for Table PRODUCT_TAG
--------------------------------------------------------

--changeset liquibase:v04.0_create_seq_product_tag
CREATE SEQUENCE "SEQ_PRODUCT_TAG" START WITH 1 INCREMENT BY 1;
--rollback DROP "SEQUENCE "SEQ_PRODUCT_TAG";


--changeset liquibase:v04.0_create_table_product_tag
CREATE TABLE "PRODUCT_TAG" (
	"ID"			INTEGER			DEFAULT "SEQ_PRODUCT_TAG".NEXTVAL		NOT NULL,
	"NAME"			VARCHAR2												NOT NULL
);
--rollback DROP TABLE "PRODUCT_TAG";


--changeset liquibase:v04.0_create_pk_product_tag
ALTER TABLE "PRODUCT_TAG"
ADD CONSTRAINT "PK_PRODUCT_TAG"
PRIMARY KEY ("ID");
--rollback ALTER TABLE "PRODUCT_TAG" DROP CONSTRAINT "PK_PRODUCT_TAG";
