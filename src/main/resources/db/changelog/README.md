Note that Liquibase doesn't require file names to contain version numbers.
However, if "includeAll" property is used (see: https://docs.liquibase.com/change-types/includeall.html) it is highly recommended, even mandatory I would say, to ensure that the proper order is followed.
It also makes the files compatible with Flyway, which is another DB change manager like Liquibase ;)