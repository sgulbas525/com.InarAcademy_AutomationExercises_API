@GetAllBrandsList_TestCase_03
Feature: TestCase_01
  Scenario: Get All Brands List
    Given the user retrieves products list from "https://automationexercise.com/api/brandsList"
    Then  verify status code "200"
    And store the brands in a "TestCase_03_output" txt file