--liquibase formatted sql

--------------------------------------------------------
--  DATA for Table PRODUCT_TYPE
--------------------------------------------------------

--changeset liquibase:v02.0_insert_data_product_type
INSERT INTO "PRODUCT_TYPE" VALUES ("SEQ_PRODUCT_TYPE".nextval,    'Food');
INSERT INTO "PRODUCT_TYPE" VALUES ("SEQ_PRODUCT_TYPE".nextval,    'Drink');
INSERT INTO "PRODUCT_TYPE" VALUES ("SEQ_PRODUCT_TYPE".nextval,    'Other');
--rollback DELETE FROM "PRODUCT_TYPE" WHERE "ID" IN (SELECT "ID" FROM "PRODUCT_TYPE" ORDER BY "ID" DESC FETCH FIRST 3 ROWS ONLY);
--rollback ALTER SEQUENCE "SEQ_PRODUCT_TYPE" INCREMENT BY -3;
--rollback SELECT "SEQ_PRODUCT_TYPE".nextval FROM DUAL;
--rollback ALTER SEQUENCE "SEQ_PRODUCT_TYPE" INCREMENT BY 1;
