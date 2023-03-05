@Station @API
Feature: Weather Station Registration
  Description: Adding and Fetching New Station via Open API

  Scenario Outline: verify invalid key is return when the user register a weather station without an API key
    Given the user prepare station submit request with following details: <external_id> <name> <latitude> <longitude> <altitude>
    When the user execute register a weather station without an API key
    Then the user validate register a weather station api status code: <code>
    And the user validates failure response by register a weather station api
    | code    | <code>                                                                             |
    | message | Invalid API key. Please see https://openweathermap.org/faq#error401 for more info. |
    Examples:
    | external_id  | name                       | latitude | longitude | altitude | code |
    | DEMO_TEST001 | Team Demo Test Station 001 | 33.33    | -122.43   | 222      | 401  |

  Scenario Outline: verify valid key is return when the user register a weather station with an API key
    Given the user prepare station submit request with following details: <external_id> <name> <latitude> <longitude> <altitude>
    When the user execute register a weather station with an API key
    Then the user validate register a weather station api status code: 201
    When the user execute fetch a weather station to validate registered weather station
    Then the user validate fetch a weather station api status code: 200
    And the user verify success response by fetch to validate weather station registration details: <external_id> <name> <latitude> <longitude> <altitude>
    Examples:
      | external_id  | name                       | latitude | longitude | altitude |
      | DEMO_TEST001 | Team Demo Test Station 001 | 33.33    | -122.43   | 222      |
      | DEMO_TEST001 | Team Demo Test Station 002 | 44.44    | -122.44   | 111      |