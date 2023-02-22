@PUTToAllProductsList_TestCase_04
  Feature: TestCase_04
    Scenario: PUP To All Product List
      Given the user build the body to put
      When the user put the product to "https://automationexercise.com/api/brandsList"
      Then convert response into java
      Then  verify status code "405"
      And verify the response message