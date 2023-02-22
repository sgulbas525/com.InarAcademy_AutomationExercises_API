@POSTToAllProductsList_TestCase_02
  Feature: TestCase_02
    Scenario: POST To All Product List
      Given the user build the body to post
      When the user post the product to "https://automationexercise.com/api/productsList"
      Then convert response into java
      Then  verify status code "405"
      And verify the response message