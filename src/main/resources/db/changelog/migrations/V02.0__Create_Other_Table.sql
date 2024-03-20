--liquibase formatted sql

-- changeset liquibase:other_comment
CREATE TABLE Otros (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);
-- rollback DROP Otros;