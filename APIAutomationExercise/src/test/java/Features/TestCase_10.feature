@POSTToVerifyLoginWithInvalidDetails_TestCase_10
Feature: TestCase_10

  Scenario Outline: POST To Verify Login with invalid details
    Given the user post the request to "https://automationexercise.com/api/verifyLogin" with invalid details "<email>" "<password>"
    Then verify status code "400"
    And verify the response message
    Examples:
      | email               | password  |
      | ahmetcanads@asdmsad | asdfasdfs |