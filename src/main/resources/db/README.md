# Liquibase README
Official documentation page: [https://docs.liquibase.com/reference-guide](https://docs.liquibase.com/reference-guide)

In addition to the official documentation I will put here some tips and tricks of my main used features.

## Summary of how it works
Liquibase update is automatically executed at app startup.
It will execute all migration files inside the `migrations` folder that have not been successfully executed before.
Each migration file might have multiple changesets. It is recommended to make each changeset atomic, specially DDL operations since they are committed by default.
If a changeset fails the migration process is stopped. Only a fully executed changeset is committed and marked as executed (keep in mind the DDL thing).
When a fail occurs you can make the required changes to the changeset that failed (and any other future changeset) and try again. However, and this is important, you **CAN NOT** make any changes to already executed and tracked changesets. Doing so will raise a checksum error when rolling back.

## Versioning
Note that Liquibase doesn't require file names to contain version numbers. However, if "includeAll" property is used (see: [https://docs.liquibase.com/change-types/includeall.html](https://docs.liquibase.com/change-types/includeall.html)) it is highly recommended, even mandatory I would say, to ensure that the proper order is followed. They don't have to be actual numbers, but some kind of naming scheme that preserves creation and execution order is necessary, like a date prefix.
However I encourage using Flyway's naming scheme to have compatibility with both Liquibase and Flyway, which is another DB change manager.

## Update
Liquibase update is configured (by default) to be executed when the SpringBoot APP starts.

## Rollback
Rollback must be manually done. To do so you need to crate a new maven run configuration:
- Goals:
    + org.liquibase:liquibase-maven-plugin:rollback
- Parameters:
    + liquibase.url=jdbc:h2:file:./testdb;MODE=ORACLE
    + liquibase.changeLogFile=db/changelog/db.changelog-master.yaml
    + liquibase.password=sa
    + liquibase.username=sa
    + liquibase.rollbackCount=9999
        * I'm usually using the rollback count, but there are other options like tags and timestamps.

I've enabled rollback error checking. However it seems like it's not a perfect solution since it only raises the error once. If you try again the same error will not raise again, so keep that in mind.