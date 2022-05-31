Feature: Adequate Shop Login

  Scenario: Adequate Shop Login
    Given user is authenticated using valid credentials from "login.json"
    Then I should get an authentication token "data.Token" via url "/api/authaccount/login"
    And response matches "adequateShopLoginSchema.json" schema