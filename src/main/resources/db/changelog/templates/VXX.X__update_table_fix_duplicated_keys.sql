--liquibase formatted sql


-- Ok, this one might need some context.
-- Let's say you have a column with no constraints and you want to apply a unique key constraint.
-- Before you can do that you MUST make each value unique (that is, remove or tranform duplicates).
-- Deleting duplicates is not that difficult, but making non unique values unique might be tricky.
-- This script does exactly that. It might need some modifications, but it gives you something to start with.
-- To achieve this I found 2 easy ways:
--      - using the ID sequence + some hardcoded values.
--      - using the UUID generation.
-- Pick the one that best suits your needs.
-- As for the rollback, while technically possible with an auxiliary table, I don't find it very useful, so I left it empty.


--------------------------------------------------------
--  UPDATE TABLE <TABLE_NAME> randomize duplicated values
--------------------------------------------------------




--------------------------------------------------------
--  UPDATE TABLE <TABLE_NAME> make unique all duplicated values
--------------------------------------------------------


--changeset liquibase:vXX.X_update_<column_name>_column_set_duplicates_to_id_seq_based_values
UPDATE
    "<TABLE_NAME>"
SET
    "<COLUMN_NAME>" = CONCAT('<HARDCODED_VALUE>', "<SEQ_TABLE_NAME>".nextval)
WHERE
    "<COLUMN_NAME>" IN (
        -- Retrieve list of duplicateas sub-query
        SELECT
            "<COLUMN_NAME>"
        FROM (
            SELECT
                COUNT(*) AS "COUNT_VAR", "<COLUMN_NAME>"
            FROM
                "<TABLE_NAME>"
            GROUP BY
                "<COLUMN_NAME>"
        )
        WHERE
            "COUNT_VAR" > 1
    );
--rollback empty
