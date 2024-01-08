Feature: Information of individual user

  Scenario: Send information get user with response success
    Given I load customer information
      | id |
      | 1  |
    When I make the user query
    Then I should see the status code 200
    And I validate schema response "GetExampleSchema"
    And I validate fields of get response