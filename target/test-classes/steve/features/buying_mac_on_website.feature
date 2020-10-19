@steve
Feature: Buying a MacBook Pro on the website

  As an online customer
  I want to choose a MacBook with accessories on the website
  So so that I can buy it online

  Scenario: A MacBook 15” with accessories can be ordered on the website
    Given I navigate to the apple store
    When I choose a MacBook Pro with the following features and accessories
      | Option          | Specification        | xxx |
      | Screen          | 15’’                 | aa  |
      | Colour          | Silver               | aa  |
      | Processor       | 2.9 GHz              | aa  |
      | Memory RAM      | 16 GB                | aa  |
      | Software        | Logic Pro X          | aa  |
      | Display adapter | USB-C to USB Adapter | aa  |
    Then I can place an order for it

  Scenario: The correct price and VAT are displayed for a MacBook 15” with accessories
    Given when I choose the following items:
      | Option                   | Price in £ |
      | MacBook Pro and software | 3168.99    |
      | Display adapter          | 19.00      |
#  When I proceed to the checkout
    Then a total price of £3187.99 will be displayed
    And £531.34 will be listed for VAT
