Feature: Constructing and searching the database
  Scenario: Searching for a client that has been registered
    Given a Client "client" with all info filled in
    And a database that contains the client
    When the database is searched for the client
    Then display the client info

  Scenario: Searching for a client not registered
    Given a Client "client" with all info filled in
    And a database not containing the client
    When the database is searched for the client
    Then display message saying no clients found

  Scenario: Registering a container for a client
    Given a Client "client" with all info filled in
    And a database that contains the client
    And a container not used by a client
    When The container is registered for the client
    Then Display "id" registered for "client"

  Scenario: Trying to register container already in use
    Given a Client "client" with all info filled in
    And a database that contains the client
    And a container being used by another client
    When The container is registered for the client
    Then Display container used by other client
