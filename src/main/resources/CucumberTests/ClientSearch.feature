Feature: finding clients by simple criterias

  Scenario: Search for a client by name
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And client list containing the client
    When searching clients by name "client"
    Then the client appears in search results

  Scenario: Search for a client by address
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And client list containing the client
    When searching clients by address "address"
    Then the client appears in search results

  Scenario: Search for a client by string
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And client list containing the client
    When searching clients for string "email"
    Then the client appears in search results