Feature: Vid on rest apis

  Scenario: Perform post opertion for profile
    Given i perform post operation for "http://localhost:3000/posts" with body
      | id | title               | author             |
      | 16 | rest assured course | execute automation |
    And i perform put operation for "http://localhost:3000/posts" with body
      | id | title               | author             |
      | 16 | rest assured course | automation |
    And i delete "16"