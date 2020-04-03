Feature: Registering client

  Scenario: successfully registering client with all info
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And a client list not containing the client
    When the client is registered
    Then the client appears in client list

  Scenario: successfully updating info for a client
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And a client list that contains the client
    When the client has their info updated
    Then the info of the client is now the new address

  Scenario: Remove client
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And a client list that contains the client
    When the client is removed
    Then client is not in client list

