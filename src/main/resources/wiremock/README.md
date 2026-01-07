# Wiremock README
Official documentation page: [https://wiremock.org/docs/stubbing/](https://wiremock.org/docs/stubbing/)

In addition to the official documentation I will put here some tips and tricks of my main used features.

## Summary of how it works
This library creates a HTTP (or even HTTPS) server that can simulate an external API. I'm using wiremock to achieve 2 goals:
- ** Demonstration purposes **
    + Have an "external" API for an API consumer example.
- ** External API replacement (mocking) **
    + Assuming this is a real project this can replace external APIs with mocks so that we don't depend on external factors for local development.

With Wiremock's configurations file we can define a response for each endpoint that, if matched, will be returned as specified.

Wiremock will not start a server by default when the app starts. To do so we created this configuration file: `WireMockConfig`.
Note that a Wiremock server annotation exists, but it's intended to be used in tests and will lead to errors if used in the main class.