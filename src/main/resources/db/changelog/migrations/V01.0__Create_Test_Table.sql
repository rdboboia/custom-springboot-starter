--liquibase formatted sql

-- changeset liquibase:create_test_table
CREATE TABLE Persons (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);
-- rollback DROP TABLE Persons;