Feature: Searching for a client
  Scenario: Search for a client by string
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And a client list that contains the client
    When the list is searched for string "client"
    Then the client appears in search results

  Scenario: Search for a client by name
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And a client list that contains the client
    When the list is searched for name "client"
    Then the client appears in search results

  Scenario: Search for a client by address
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And a client list that contains the client
    When the client list is searched for clients address
    Then the client appears in search results

