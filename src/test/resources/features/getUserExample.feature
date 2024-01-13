@REQRES
Feature: Information of individual user

  @GET
  Scenario: Send information get user with response success
    Given I load customer information
      | id |
      | 1  |
    When I make the user query
    Then I should see the status code 200
    And I validate schema response "GetUserSchema"
    And I validate fields of get user response
      | id | email                  | first_name | last_name | avatar                                  |
      | 1  | george.bluth@reqres.in | George     | Bluth     | https://reqres.in/img/faces/1-image.jpg |