@THIRD_PARTIES
Feature: Api Integration Example

  @AUTH0 @POST
  Scenario: Create user with Auth0 as intermediate
    Given I load data user to create
      | email                         | job        |
      | williammontenegro4d@gmail.com | Programmer |
    And I get the user in auth0
    When I make the creation of user
    Then I should see the status code 201
    And I validate schema response "PostUserSchema"
    And I validate fields of post user response

  @MAILSAC @POST
  Scenario: Create user with Mailsac as intermediate
    Given I load data user to create
      | email            | job    |
      | pazd@mailsac.com | Tester |
    And I get the message in mailsac
    When I make the creation of user with message
    Then I should see the status code 201
    And I validate schema response "PostUserSchema"
    And I validate fields of post user response

  @CSV @POST
  Scenario: Create user with data from CSV
    Given I load data from csv
    When I make the creation of user
    Then I should see the status code 201
    And I validate schema response "PostUserSchema"
    And I validate fields of post user response