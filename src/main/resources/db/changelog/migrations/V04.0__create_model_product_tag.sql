--liquibase formatted sql

--------------------------------------------------------
--  DDL for Table PRODUCT_TAG
--------------------------------------------------------

--changeset liquibase:v04.0_create_seq_product_tag
CREATE SEQUENCE "SEQ_PRODUCT_TAG" START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE "SEQ_PRODUCT_TAG";


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






--------------------------------------------------------
--  DDL for Table PRODUCT_TAG_RELATION
--------------------------------------------------------

--changeset liquibase:v04.0_create_seq_product_tag_relation
CREATE SEQUENCE "SEQ_PRODUCT_TAG_RELATION" START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE "SEQ_PRODUCT_TAG_RELATION";


--changeset liquibase:v04.0_create_table_product_tag_relation
CREATE TABLE "PRODUCT_TAG_RELATION" (
	"PRODUCT_ID"	INTEGER		NOT NULL,
	"TAG_ID"		INTEGER		NOT NULL
);
--rollback DROP TABLE "PRODUCT_TAG_RELATION";


--changeset liquibase:v04.0_create_pk_product_tag_relation
ALTER TABLE "PRODUCT_TAG_RELATION"
ADD CONSTRAINT "PK_PRODUCT_TAG_RELATION"
PRIMARY KEY ("PRODUCT_ID", "TAG_ID");
--rollback ALTER TABLE "PRODUCT_TAG_RELATION" DROP CONSTRAINT "PK_PRODUCT_TAG_RELATION";


--changeset liquibase:v04.0_create_fk_product_tag_relation_product
ALTER TABLE "PRODUCT_TAG_RELATION"
ADD CONSTRAINT "FK_PRODUCT_TAG_RELATION_PRODUCT"
FOREIGN KEY ("PRODUCT_ID")
REFERENCES "PRODUCT" ("ID");
--rollback ALTER TABLE "PRODUCT_TAG_RELATION" DROP CONSTRAINT "FK_PRODUCT_TAG_RELATION_PRODUCT";


--changeset liquibase:v04.0_create_fk_product_tag_relation_tag
ALTER TABLE "PRODUCT_TAG_RELATION"
ADD CONSTRAINT "FK_PRODUCT_TAG_RELATION_TAG"
FOREIGN KEY ("TAG_ID")
REFERENCES "PRODUCT_TAG" ("ID");
--rollback ALTER TABLE "PRODUCT_TAG_RELATION" DROP CONSTRAINT "FK_PRODUCT_TAG_RELATION_TAG";
