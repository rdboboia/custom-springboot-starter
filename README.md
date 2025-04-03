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
- Lombok
    + Object common methods.
- JPA
    + Persistence manager.
- Input Validation
    + DTO validation.
- Junit 5 (Jupiter)
    + Unit teting.
- OpenAPI
    + API documentation + swagger UI.
- H2 (as a placeholder)
    + Actual persistence (should be replaced with a proper DB).
    + Custom configuration:
        * Using Oracle mode.
- Liquibase
    + DB changes manager (kinda like GIT, but for the DB).
    + Custom configuration:
        * Check application.yaml.
- Mapstruct
    + Object to object mapping.
    + Custom configuration:
        * Unmapped target or source raises compilation error.
        * Default component model spring (avoids the need to declare it inside each mapper; automatically creates the bean).

- JaCoCo
    + Code coverage.
    
- Google Java Format
    + Code formatter.
- Checkstyle
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



# TODOs
- Jacoco coverage check
- Specifications / Query by Example
- Transactions
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

### Additional configuration (Eclise IDE Only)
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