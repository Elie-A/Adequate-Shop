Feature: Adequate Shop Registration

  Scenario: Adequate Shop Registration
    Given user registers using valid entries
    Then I should get registered via url "/api/authaccount/registration"
    And response matches "adequateShopRegistrationSchema.json" schema
    And I save the response in "login.json"