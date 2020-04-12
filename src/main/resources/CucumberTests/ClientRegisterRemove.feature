Feature: register client
  Scenario: successfully registering client with all info
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And an empty client list
    When the client is registered
    Then the client list contains the client

  Scenario: Remove client
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And client list containing the client
    When the client is removed
    Then the client list does not contain the client
