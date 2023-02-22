@POSTToVerifyLoginWithoutEmailParameter_TestCase_08
Feature: TestCase_08
  Scenario: POST To Verify Login without email parameter
    Given the user post the request to "https://automationexercise.com/api/verifyLogin"
    Then verify status code "400"
    And verify the response message