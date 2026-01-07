--liquibase formatted sql

--------------------------------------------------------
--  DDL for Table PRODUCT_TYPE
--------------------------------------------------------

--changeset liquibase:v01.0_create_seq_product_type
CREATE SEQUENCE "SEQ_PRODUCT_TYPE" START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE "SEQ_PRODUCT_TYPE";


--changeset liquibase:v01.0_create_table_product_type
CREATE TABLE "PRODUCT_TYPE" (
	"ID"			INTEGER			DEFAULT "SEQ_PRODUCT_TYPE".NEXTVAL		NOT NULL,
	"NAME"			VARCHAR2												NOT NULL
);
--rollback DROP TABLE "PRODUCT_TYPE";


--changeset liquibase:v01.0_create_pk_product_type
ALTER TABLE "PRODUCT_TYPE"
ADD CONSTRAINT "PK_PRODUCT_TYPE"
PRIMARY KEY ("ID");
--rollback ALTER TABLE "PRODUCT_TYPE" DROP CONSTRAINT "PK_PRODUCT_TYPE";






--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

--changeset liquibase:v01.0_create_seq_product
CREATE SEQUENCE "SEQ_PRODUCT" START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE "SEQ_PRODUCT";


--changeset liquibase:v01.0_create_table_product
CREATE TABLE "PRODUCT" (
	"ID"					INTEGER		DEFAULT "SEQ_PRODUCT".NEXTVAL		NOT NULL,
	"NAME"					VARCHAR2										NOT NULL,
	"FK_PRODUCT_TYPE"		INTEGER											NOT NULL
);
--rollback DROP TABLE "PRODUCT";


--changeset liquibase:v01.0_create_pk_product
ALTER TABLE "PRODUCT"
ADD CONSTRAINT "PK_PRODUCT"
PRIMARY KEY ("ID");
--rollback ALTER TABLE "PRODUCT" DROP CONSTRAINT "PK_PRODUCT";


--changeset liquibase:v01.0_create_fk_product_type
ALTER TABLE "PRODUCT"
ADD CONSTRAINT "FK_PRODUCT_PRODUCT_TYPE"
FOREIGN KEY ("FK_PRODUCT_TYPE")
REFERENCES "PRODUCT_TYPE" ("ID");
--rollback ALTER TABLE "PRODUCT" DROP CONSTRAINT "FK_PRODUCT_PRODUCT_TYPE";
