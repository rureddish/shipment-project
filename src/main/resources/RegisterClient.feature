Feature: Registering client
  Scenario: successfully registering client with all info
    Given a Client "client" with all info filled in
    And a client list not containing the client
    When the client is registered
    Then the client appears in database

  Scenario: registering a client with info missing
    Given a Client "incompleteclient" that is missing info
    And a client list not containing the client
    When the client is registered
    Then error message incompleteclient is displayed

  Scenario: successfully updating info for a client
    Given a Client "client" with all info filled in
    And a client list that contains the client
    When the client has their address updated
    Then the address of the client is now the new address

  Scenario: Remove client
    Given a Client "client" with all info filled in
    And a client list that contains the client
    When the client is removed
    Then the client list no longer contains client

  Scenario: Search for a client by name
    Given a Client "client" with all info filled in
    And a client list that contains the client
    When the client list is searched for "client"
    Then the client appears in search results

  Scenario: Search for a client by address
    Given a Client "client" with all info filled in
    And a client list that contains the client
    When the client list is searched for clients address
    Then the client appears in search results

