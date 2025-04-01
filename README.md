# Description
This is my customized springboot starter with all libraries and utilities that I usually include in my projects.
My plan is to keep ip updated and well documented so that it can be understood and used by anyone.

# TODOs
- Transactions
- Security ?
	+ peer token
	+ access token (service activity)
	+ https

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