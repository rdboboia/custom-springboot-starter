--liquibase formatted sql

-- changeset liquibase:some_comment
CREATE TABLE Persons (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);
-- rollback DROP Persons;