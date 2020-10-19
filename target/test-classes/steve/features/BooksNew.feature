Feature: Books new

  Scenario: Add books and update

    Given i perform post operation with volumeInfo
      | title      | subtitle      | publisher      |
      | steveTitle | steveSubTitle | stevePublisher |
    Given i perform post operation with items
      | id | kind         | etag    |
      | 16 | books#volume | abqrstu |
    And i perform put operation for "{path}" with body
      | id | etag  | kind         |
      | 16 | steve | books#volume |
    And i perform get operation for "{path}"
      | id           |
      | 9s1CDwAAQBAJ |
    And i delete "16" for "{path}"