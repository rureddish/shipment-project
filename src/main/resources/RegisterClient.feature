Feature: Registering client
  Scenario: successfully registering client with all info
    Given a Client "clientname" with "address", "reference person" and "email"
    And a database not containing the client "clientname"
    When "clientname" is registered
    Then "clientname" appears in database

  Scenario: registering a client with info missing
    Given a Client "clientname" that is missing info
    And a database not containing the client "clientname"
    When "clientname" is registered
    Then error message incompleteclient is displayed

  Scenario: registering a client that database already contains
    Given a Client "clientname" that is missing info
    And a database that already contains a client "clientname"
    When "clientname" is registered
    Then error message clientAlreadyRegistered is displayed

  Scenario: successfully updating info for a client
    Given a Client "clientname" with "address", "reference person" and "email"
    And a database that already contains a client "clientname"
    When client "clientname" has their address updated to "otheraddress"
    Then the address of client "clientname" is now "otheraddress"