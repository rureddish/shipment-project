Feature: Registering client
  Scenario: successfully registering client with all info
    Given a Client with all info filled in
    And a database not containing the client
    When the client is registered
    Then the client appears in database

  Scenario: registering a client with info missing
    Given a Client that is missing info
    And a database not containing the client
    When the client is registered
    Then error message incompleteclient is displayed

  Scenario: registering a client that database already contains
    Given a Client with all info filled in
    And a database that contains the client
    When the client is registered
    Then error message clientAlreadyRegistered is displayed

  Scenario: successfully updating info for a client
    Given a Client with all info filled in
    And a database that contains the client
    When the client has their address updated
    Then the address of the client is now the new address

  Scenario: Remove client
    Given a Client with all info filled in
    And a database that contains the client
    When the client is removed
    Then display message saying client has been removed