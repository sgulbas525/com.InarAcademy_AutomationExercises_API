@GetAllProductsList_TestCase_01
  Feature: TestCase_01
    Scenario: Get All Products
      Given the user retrieves products list from "https://automationexercise.com/api/productsList"
      Then  verify status code "200"
      And store the products in a "TestCase_01_output" txt file