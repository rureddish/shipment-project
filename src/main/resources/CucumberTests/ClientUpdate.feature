Feature: update client
  Scenario: successfully updating info for a client
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And client list containing the client
    When the info of the client is updated
    Then the client now has new info

