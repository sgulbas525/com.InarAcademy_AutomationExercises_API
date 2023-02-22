@POSTToSearchProductWithoutSearchProductParameter_TestCase_06
  Feature: TestCase_06
    Scenario: POST To Search Product without search_product parameter
      Given the user post the request to "https://automationexercise.com/api/searchProduct"
      Then verify status code "400"
      And verify the response message