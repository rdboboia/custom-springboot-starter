--liquibase formatted sql

--changeset liquibase:create_ok_table
CREATE TABLE OkTable (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);
-- rollback DROP TABLE OkTable;



--changeset liquibase:create_invalid_table failOnError:false
CREATE TABLE InvalidTable (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);
-- rollback DROP TABLE InvalidTable;