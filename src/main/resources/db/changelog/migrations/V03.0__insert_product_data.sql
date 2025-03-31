--liquibase formatted sql

--------------------------------------------------------
--  DATA for Table PRODUCT
--------------------------------------------------------

--changeset liquibase:v03.0_insert_data_product
INSERT INTO "PRODUCT" VALUES ("SEQ_PRODUCT".nextval,	'Tomato', 	SELECT "ID" FROM "PRODUCT_TYPE" WHERE "NAME" = 'Food');
INSERT INTO "PRODUCT" VALUES ("SEQ_PRODUCT".nextval,	'Water', 	SELECT "ID" FROM "PRODUCT_TYPE" WHERE "NAME" = 'Drink');
INSERT INTO "PRODUCT" VALUES ("SEQ_PRODUCT".nextval,	'Eraser', 	SELECT "ID" FROM "PRODUCT_TYPE" WHERE "NAME" = 'Other');
--rollback DELETE FROM "PRODUCT" WHERE "ID" IN (SELECT "ID" FROM "PRODUCT" ORDER BY "ID" DESC FETCH FIRST 3 ROWS ONLY);
--rollback ALTER SEQUENCE "SEQ_PRODUCT" INCREMENT BY -3;
--rollback SELECT "SEQ_PRODUCT".nextval FROM DUAL;
--rollback ALTER SEQUENCE "SEQ_PRODUCT" INCREMENT BY 1;
