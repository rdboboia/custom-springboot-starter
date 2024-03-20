--liquibase formatted sql

-- changeset liquibase:create_other_table
CREATE TABLE Otros (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);
-- rollback DROP TABLE Otros;