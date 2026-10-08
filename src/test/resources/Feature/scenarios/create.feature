@regression @scenarios @cleanup
Feature: Create and manage scenarios

  Background:
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Scenarios"
    And I click on create new scenario button
    And I create a new scenario
    And I search the added scenario

  @smoke
  Scenario Outline: Verify that user is able to generate a new scenario
    Then I verify that added scenario is displayed under Scenarios list

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to import the BAMS treatments in treatment pool from scenario page
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on import option scenario page
    And I import "BAMS" file "BAMS_TestData.xlsx"
    Then I verify that added scenario is displayed under Scenarios list
    And I validate that "2388" treatment imported successfully

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to import the PAMS treatments in treatment pool from scenario page
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on import option scenario page
    And I import "PAMS" file "PAMS_TestData.xlsx" with "PAMS_TREATMENTS"
    Then I verify that added scenario is displayed under Scenarios list
    And I validate that "1026" treatment imported successfully

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  @smoke
  Scenario Outline: Verify that user is able to export the BAMS treatment from treatment pool
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on export option scenario page
    And I export "BAMS" the file
    Then I validate that treatment file exported successfully

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  @smoke
  Scenario Outline: Verify that user is able to export the PAMS treatment from treatment pool
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on export option scenario page
    And I export "PAMS" the file
    Then I validate that treatment file exported successfully

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that options displayed under Actions
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    Then I Verify that "Run" option is displayed
    And I Verify that "Import" option is displayed
    And I Verify that "Export" option is displayed
    And I Verify that "Settings" option is displayed
    And I Verify that "Analysis" option is displayed
    And I Verify that "Delete" option is displayed
    And I Verify that "Treatment Pool Library" option is displayed

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |
  

  Scenario Outline: Verify that user is able to delete scenario
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click "Delete" the scenario
    Then I verify that added scenario is not displayed under Scenarios list

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to create a new scenario with deleted scenario name
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click "Delete" the scenario
    Then I verify that added scenario is not displayed under Scenarios list
    When I click on create new scenario button
    And I create a scenario with deleted scenario details
    Then I verify that added scenario is displayed under Scenarios list

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to run BAMS the created scenario
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on import option scenario page
    And I import "BAMS" file "BAMS_TestData.xlsx"
    And I search the added scenario
    Then I verify status of Scenario is change to "Created"
    When I click on Actions three dots of scenario page
    And I click on run option scenario page
    Then I verify status of Scenario is change to "Success"

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |


  Scenario Outline: Verify that user is able to run PAMS the created scenario
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on import option scenario page
    And I import "PAMS" file "PAMS_TestData.xlsx" with "PAMS_TREATMENTS"
    Then I verify that added scenario is displayed under Scenarios list
    And I validate that "1026" treatment imported successfully
    And I verify status of Scenario is change to "Created"
    When I click on Actions three dots of scenario page
    And I click on run option scenario page
    Then I verify that added scenario is displayed under Scenarios list
    And I verify status of Scenario is change to "Success"

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  @smoke
  Scenario Outline: Verify that user navigate to Treatment Pool library page from action menu
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Treatment Pool Library option from action menu
    Then I verify navigate to "Treatment Pool Libraries" page
    And I verify that added treatment pool is displayed under treatment pool library list

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that Reset functionality is working on Scenario page
    Then I verify that added scenario is displayed under Scenarios list
    When I click Reset button to reset scenarios
    Then I verify that scenario search field is empty
    And I verify that scenarios table data is reset

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |
  

