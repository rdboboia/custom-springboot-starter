--liquibase formatted sql

--------------------------------------------------------
--  ALTER Table <TABLE_NAME>
--------------------------------------------------------

--changeset liquibase:vXX.X_create_fk_<fk_constraint_name>
ALTER TABLE "<TABLE_NAME>"
ADD CONSTRAINT "<FK_CONSTRAINT_NAME>"
FOREIGN KEY ("<TABLE_NAME_COLUMN_NAME>")
REFERENCES "<REFERENCED_TABLE_NAME>" ("<REFERENCED_COLUMN_NAME>");
--rollback ALTER TABLE "<TABLE_NAME>" DROP CONSTRAINT "<FK_CONSTRAINT_NAME>";
