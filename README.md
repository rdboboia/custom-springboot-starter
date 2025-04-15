# Description
This is my customized springboot starter with all libraries and utilities that I usually include in my projects.
My plan is to keep ip updated and well documented so that it can be understood and used by anyone.






# Features
This project is intented for REST services.
The latest version of each component is encouraged.

## Language
- Java 21

## Framework
- SpringBoot 3

## Public dependencies / components
These should not change too much. They are the base of this custom starter.
- **Lombok**
    + Object common methods.
- **JPA + Spring Data JPA**
    + Persistence manager.
- **Input Validation**
    + DTO validation.
- **Junit 5 (Jupiter)**
    + Unit testing.
- **OpenAPI**
    + API documentation + swagger UI.
- **H2**
    + Actual persistence; DB engine.
    + Custom configuration:
        * Using Oracle mode.
- **Liquibase**
    + DB changes manager (kinda like GIT, but for the DB).
    + Custom configuration:
        * Check application.yaml.
    + Templates for basic DB operations.
- **Mapstruct**
    + Object to object mapping.
    + Custom configuration:
        * Unmapped target or source raises compilation error.
        * Default component model spring (avoids the need to declare it inside each mapper; automatically creates the bean).
- **JaCoCo**
    + Code coverage (report and checker)
    + Custom configuration:
        * Exclude mapper *Impl classes (that is, excludes autogenerated mapper code, but still checks custom static or default methods).
        * Check that ALL metrics at BUNDLE level are > 90%. This ratio is defined as a property (jacoco-coverage-check-threshold-bundle).
- **Google Java Format**
    + Code formatter.
- **Checkstyle**
    + Checks for style, code and logic errors.
    + Custom configuration:
        * Using google_checks to match Google's style and format.

## Custom additions (might be migrated to maven central in the future if possible)
- VerifyNoMoreInteractionsExtension
    + Extension to automatically verify the expected interactions based on the when-then instructions.
        * Caveat: manual verify implementation still required when MockMvc is used.
- FieldsUtils
    + Provides methods to update and null check objects.
- Enum Validator
    + Validates enum-entity integrity against the DB at APP startup.
        * Caveat: each enum validation must be implemented manually. Maybe automation is possible?

## Placeholders
Some components are intented to be just an example and they should be removed or replaced by something that suits your specific case.
- H2
    + Fine for development and tinkering, not so fine for production.
- Liquibase scripts
    + While the configuration is fine, the migration scripts should not be migrated.






# TODOs
- Specifications / Query by Example
- Input validation (lib already imported; missing usage)
- Junit examples
    + any error for primitives
- Transactions
- Cache
- Security ?
	+ peer token
	+ access token (service activity)
	+ https

## Low priority
- Fix exception handler testing method.






# Required configuration

## Format
This project uses Google Java Format. You can find the official install configuration on Google's GitHub:
https://github.com/google/google-java-format

### Additional configuration (Eclipse IDE Only)
For Eclipse (and STS) some additional configuration is needed. This might not apply to other IDEs.
#### Organize Imports
Additional configuration is required for Eclipse IDE to properly organize imports:
To comply with Google's style, go to `Window` > `Preferences` > `Java` > `Code Style` > `Organize Imports`, delete all default groups and add a single `*` static import group and a single `*` import group. Apply changes.

#### New line indentation
Additional configuration is required for Eclipse IDE to properly indent new lines:
To comply with Google's style, go to `Window` > `Preferences` > `Java` > `Code Style` > `Formatter`, click on `Edit`.
In the first list `Indentation` change `Tab Size` to `2`.






# Recommended IDE plugins
- EclEmma Java Code Coverage
- SonarQube
- Checkstyle
    + Might need to select the google_checks profile.