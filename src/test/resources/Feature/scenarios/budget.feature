@regression @scenarios @cleanup
Feature: Budget constraints

  Background:
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Scenarios"
    And I click on create new scenario button
    And I create a new scenario
    And I search the added scenario

  Scenario Outline: Verify that user is able to navigate Budget Constraints tab under Edit Scenario page
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Settings option from action menu
    Then I verify navigate to "Scenario Parameters" page
    When I click on "Budget Constraints" left side menu
    Then I verify navigate to "Budget Constraints" page
    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify default message on Budget Constraints tab when no budget is imported
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Settings option from action menu
    Then I verify navigate to "Scenario Parameters" page
    When I click on "Budget Constraints" left side menu
    Then I verify navigate to "Budget Constraints" page
    And I verify default message "No budget information available" is displayed
    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |
  

  Scenario Outline: Verify that user is able to download the budget constraints template file
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Settings option from action menu
    Then I verify navigate to "Scenario Parameters" page
    When I click on "Budget Constraints" left side menu
    Then I verify navigate to "Budget Constraints" page
    And I verify default message "No budget information available" is displayed
    When I click on download template link
    Then I validate that budget constraints template file downloaded successfully
    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that note is displayed when no treatments are added and user try to upload budget constraints file
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Settings option from action menu
    Then I verify navigate to "Scenario Parameters" page
    When I click on "Budget Constraints" left side menu
    Then I verify navigate to "Budget Constraints" page
    And I verify default message "No budget information available" is displayed
    When I click on Import budget button
    And I click on upload budget constraints file and upload the file "Budget_Constraints_TestData.xlsx"
    Then I validate that note "Please import the treatments to proceed" is displayed when no treatments are added
    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |
  

  Scenario Outline: Verify that system allow to upload budget constraints file after treatments are added
    And I click on Actions three dots of scenario page
    And I click on import option scenario page
    And I import "BAMS" file "BAMS_TestData.xlsx"
    And I search the added scenario
    And I click on Actions three dots of scenario page
    And I click on Settings option from action menu
    Then I verify navigate to "Scenario Parameters" page
    When I click on "Budget Constraints" left side menu
    Then I verify navigate to "Budget Constraints" page
    And I verify default message "No budget information available" is displayed
    When I click on Import budget button
    And I click on upload budget constraints file and upload the file "Budget_Constraints_TestData.xlsx"
    And I click on Import button
    Then I verify success message "Budget constraints imported successfully!" is displayed

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |
  

