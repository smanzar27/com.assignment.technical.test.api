@Clean @API
Feature: Cleaning
  Description: removing station created by specific api-key

  Scenario: cleanup
    When the user prepare list of station ids registered against specific api-key
    Then the user execute remove register stations against specific api-key