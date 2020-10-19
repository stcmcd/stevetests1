@steve
Feature: Bikes

  Scenario: 1. Verify City in Country
    Given the city "Frankfurt" is in "Germany" is displayed in the console
#      | longitude | latitude |
#      | 8.66375   | 50.1072  |

  Scenario: 2. Find extras at bike stations
    And display in console what extras are in stations "{path}"
      | network     |
      | v2/networks |

  Scenario: 3. Find bike stations that a specific extra is available
    And Find bike stations that the extra "has_ebikes" is available "{path}"
      | network     |
      | v2/networks |