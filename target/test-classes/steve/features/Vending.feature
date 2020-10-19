Feature: Vending machine for drinks

  Scenario: Enter exact price for drink
    Given i want to buy a "Soda"
    And i enter the following coins
      | coin    |
      | Quarter |
      | Dime    |
      | Nickle  |
      | Penny   |
      | Penny   |
      | Penny   |
      | Penny   |
      | Penny   |
    Then i  receive a refund of 0
