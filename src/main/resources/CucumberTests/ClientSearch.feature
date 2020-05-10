Feature: finding clients by simple criterias

  Scenario: Search for a client by name
    Given a Client "client2" with address "address2" email "email2" and ref person "refperson2"
    And the client is registered
    And client list containing the client
    When searching clients by name "client2"
    Then the client appears in search results

  Scenario: Search for a client by address
    Given a Client "client3" with address "address3" email "email3" and ref person "refperson3"
    And the client is registered
    And client list containing the client
    When searching clients by address "address3"
    Then the client appears in search results

  Scenario: Search for a client by email
    Given a Client "client4" with address "address4" email "email4" and ref person "refperson4"
    And the client is registered
    And client list containing the client
    When searching clients for email "email4"
    Then the client appears in search results