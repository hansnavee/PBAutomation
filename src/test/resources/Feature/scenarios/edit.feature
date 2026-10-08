@regression @scenarios @cleanup
Feature: Edit scenario

  Background:
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Scenarios"
    And I click on create new scenario button
    And I create a new scenario
    And I search the added scenario

  @smoke
  Scenario Outline: Verify that user is able to navigate to edit the created scenario page
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Settings option from action menu
    Then I verify navigate to "Scenario Parameters" page
    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


