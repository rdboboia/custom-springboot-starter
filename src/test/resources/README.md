# Testing (WIP)
We will be focusing mainly on unit testing.

The goal of this readme is to give a quick explanation on why the test are and should be implemented in the way that they are.



## Key objectives
- Bugs (with static analysis and unit testing).
- Coverage (with a white box unit testing strategy).
- Logic and edge cases (with a mix of white box and black box unit testing strategies).



## Naming conventions
JUnit 5 naming conventions (previous versions have slight differences):

### Class
TestedClassNameTest

### Method
testedMethodNameWithAdditionalFunctionalityInfoTest



## Implementation
The following methods and libraries are used for unit testing.



### JUnit 5
Provides the base @Test (and related) annotations and the assert methods.

At least one assert is required for each test (Sonar checks this).

**WARNING** 

Make sure to use only the JUnit 5 (jupiter) libraries, as mixing versions might lead to unexpected and strange behavior.



### Mockito
Allows us to mock external dependencies so that unit tests can be implemented.

#### When-Then
Allows us to control how the external mocks behave.

#### Verify
Allows us to check that the expected interactions with external dependencies are present.
This is a nice way of checking that the test execution follows the expected path.

#### CaptureArgument
Used in a very few cases when an external class is used but it's not a dependency that can be mocked.
Allows us to retrieve and check the attributes of an object that is passed as an argument.



### Test types
Based on some minor implementation details we can have the following types of unit tests:

#### Service
The most common type that follows the standard unit testing implementation.
It usually has some external dependencies that will be mocked.

The following elements are used:
- @ExtendWith
    + Mockito and additional test extensions.
- @InjectMocks
    + Creates and instance with the desired mocks of the class to be tests (must be the impl class).
- @Mock
    + Creates a mock for an external dependency.
- @Test
    + The test implementation.

#### Controller
There are 2 ways to approach this layer. On the one hand we can do it as a service: mocking external dependencies and calling the methods.
However, the controllers don't usually have that much code to test, so we can improve and expand the reach of our test by including the API layer.
If we want to be strict we should test the API and the code separately, but that would be a lot of extra work that can be skipped by testing from the API layer directly.

So, to do so, we will use the following elements:
- @WebMvcTest
    + Controller spring autoconfiguration (receives the class to be tested as an argument)
- @ExtendWith (only if we need any extensions, like our custom verify extension).
    + Custom extensions; no mockito extensions here.
- @Autowired of MockMvc
    + Provides the instance of MockMvc to call the controller endpoints.
- @MockitoBean
    + Creates a mock for an external dependency that can be used by the MockMvc.
- ObjectMapper
    + Required for JSON-Object (de)serialization. Remember to register the JavaTimeModule if dates and/or times are used (no longer present by default in recent versions).

#### Utils
Utility classes usually have static methods.
Here we don't usually have mocks, so all mock related elements can be ignored.



## Tips and observations
### Assert vs Verify
- Assert: check changes or returned object from the called method.
- Verify: check interactions (calls) with external dependencies.

### Test fails
When a test fails we shouldn't assume that the test is bad. Tests are implemented to warn us when unintented changes are made to the code.
A failing test should lead us to check and understand what the tested method does and then check if the test is properly testing said method or it must be updated to meet the new logic.

### Beware of the alternative sintaxis of when-then
We usually write when-then for mocks.
However, when a method doesn't return anything (void) then we must invert the order: do-when.

Note: we could always use the do-when form so that when dealing with void methods we don't have an order inversion but, at least for now, both orders are valid (except for void methods).

### new String() in tests
Java's Strings can behave in some weird ways sometimes if you don't know how some inner implementation details.
If we want to catch string comparisons that are not done with equals we should always use new String() when creating a String so that we force a new instance.
This is not that necessary since Sonar already checks for that error, but it's nice to keep in mind if we want to be more strict with the tests.
