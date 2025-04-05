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