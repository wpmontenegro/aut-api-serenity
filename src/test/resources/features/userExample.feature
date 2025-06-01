@REQRES
Feature: User Management Example

  Background: Load authentication credentials
    Given I load the API key to authenticate

  @GET @PATH_PARAMS
  Scenario: Get information from an individual user with response success
    Given I load user information
      | id |
      | 1  |
    When I make the single user query
    Then I should see the status code 200
    And I validate schema response "GetSingleUserSchema"
    And I validate fields of get single user response
      | id | email                  | first_name | last_name | avatar                                  |
      | 1  | george.bluth@reqres.in | George     | Bluth     | https://reqres.in/img/faces/1-image.jpg |

  @GET @QUERY_PARAMS
  Scenario: Get information from users by page with response success
    Given I load page information
      | page |
      | 2    |
    When I make the user query
    Then I should see the status code 200
    And I validate schema response "GetUserSchema"
    And I validate fields of get user response
      | per_page | total | total_pages |
      | 6        | 12    | 2           |
    And I validate each user of get user response

  @POST
  Scenario Outline: Create user with response success
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

  @PUT
  Scenario Outline: Update user information with response success
    Given I load user information
      | id   |
      | <id> |
    And I load data user to create
      | name   | job   |
      | <name> | <job> |
    When I make the update of user
    Then I should see the status code 200
    And I validate schema response "PutUserSchema"
    And I validate fields of put user response
    Examples:
      | id | name     | job           |
      | 2  | morpheus | zion resident |

  @DELETE
  Scenario Outline: Delete user with response success
    Given I load user information
      | id   |
      | <id> |
    When I make the delete of user
    Then I should see the status code 204
    And I validate body is empty
    Examples:
      | id |
      | 2  |