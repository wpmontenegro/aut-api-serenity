@REQRES
Feature: Creation of individual user

  @POST
  Scenario Outline: Send information post user with response success
    Given I load data user to create
      | name   | job   |
      | <name> | <job> |
    When I make the creation of user
    Then I should see the status code 201
    And I validate schema response "PostUserSchema"
    And I validate fields of post user response
    Examples:
      | name     | job    |
      | morpheus | leader |

