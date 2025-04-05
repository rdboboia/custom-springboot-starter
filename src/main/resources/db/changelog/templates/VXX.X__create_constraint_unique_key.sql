--liquibase formatted sql


--------------------------------------------------------
--  ALTER Table <TABLE_NAME> add <COLUMN_NAME> UK constraint
--------------------------------------------------------


--changeset liquibase:vXX.X_create_uk_<uk_constraint_name>
ALTER TABLE "<TABLE_NAME>"
ADD CONSTRAINT "<UK_CONSTRAINT_NAME>"
UNIQUE ("<COLUMN_NAME>");
--rollback ALTER TABLE "<TABLE_NAME>" DROP CONSTRAINT "<UK_CONSTRAINT_NAME>";
