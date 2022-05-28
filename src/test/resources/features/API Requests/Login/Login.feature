Feature: Adequate Shop Login

  Scenario: Adequate Shop Login
    Given user is authenticated using valid credentials
    Then I should get an authentication token "data.Token" via url "/api/authaccount/login"