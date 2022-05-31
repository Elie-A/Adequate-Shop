Feature: Adequate Shop Users

  Scenario: Adequate Shop Get Users
    Given I invoke "GET" operation of "/api/users" with query parameters "getUsersParams" and values "getUsersParamsValues"
    And I save the response in "users.json"
    Then response matches "adequateShopGetUsersSchema.json" schema
    And I save all users

  Scenario: Adequate Shop Get User By ID
    Given I invoke "GET" operation of "/api/users/paramToReplace" with path parameters "getUsersByIDParams" and values "getUsersByIDParamsValues"
    And I save the response in "users.json"
    And response matches "adequateShopGetUserByID.json" schema

  Scenario: Adequate Shop User Creation
    Given I invoke "POST" operation of "/api/users" with valid entries
    And response matches "adequateShopUserCreation.json" schema

  Scenario: Adequate Shop User Update
    Given I invoke "PUT" operation of "/api/users/paramToReplace" with path parameters "userUpdateParameter" with body
    And response matches "adequateShopUserUpdate.json" schema

  Scenario: Adequate Shop User Deletion
    Given I invoke "DELETE" operation of "/api/users/paramToReplace" with path parameters "userDeletionParameter"
    And response matches "adequateShopUserDeletion.json" schema