Feature: register client

  Scenario: successfully registering client with all info
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And an empty client list
    When the client is registered
    Then the client list contains the client

  Scenario: Email already used
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And client list containing a client with same email
    When the client is registered
    Then the client list doesnt contain the client

  Scenario: Remove client
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And client list containing the client
    When the client is removed
    Then the client is soft deleted

  Scenario: Delete client
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And the client is registered
    When  the client is removed
    Then  the client does not exist