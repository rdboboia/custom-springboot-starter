@echo off

mvnw liquibase:rollback -Dliquibase.url=jdbc:h2:file:./testdb -Dliquibase.username=sa -Dliquibase.password= -Dliquibase.changeLogFile=db/changelog/db.changelog-master.yaml -Dliquibase.rollbackCount=1