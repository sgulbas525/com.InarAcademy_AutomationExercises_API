@DELETEToVerifyLogin_TestCase_09
Feature: TestCase_09
  Scenario: DELETE To Verify Login
    Given the user post the request to "https://automationexercise.com/api/verifyLogin"
    Then verify status code "400"
    And verify the response message