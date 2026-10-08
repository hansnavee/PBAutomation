@regression @scenarios @cleanup
Feature: Scenario analysis

  Background:
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Scenarios"
    And I click on create new scenario button
    And I create a new scenario
    And I search the added scenario

  Scenario Outline: Verify that user is able to Navigate Projects page from Analysis option in action menu
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |


  Scenario Outline: Verify that Export to excel and Download Json buttons are enabled on Projects page when no treatments are added
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    And I verify that button Export to excel button is enabled
    And I verify that button Download Json button is enabled

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to Navigate Projects Treatments page from Projects page
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    And I click on Project Treatments button
    Then I verify that user navigate to "Project Treatments" page

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |
  

  Scenario Outline: Verify that No record found message is displayed on Projects page when treatment pool is empty
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    And I verify that No records found message is displayed

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |


  Scenario Outline: Verify that exports format buttons are displayed enabled on Projects page when treatment pool is filled but scenario is not executed  
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    And I verify that No records found message is displayed

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |


  Scenario Outline: Verify that Export to excel and Export to Json buttons are enabled on Projects page when treatment pool is filled but scenario is not executed
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    And I verify that button Export to excel button is enabled
    And I verify that button Download Json button is enabled

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |
  

  Scenario Outline: Verify that No record found message is displayed on all Charts tabs when treatment pool is empty
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    When I click on "<Charts Tabs>" tab from charts dropdown menu
    Then I verify that "No records found" message is displayed on "<Charts Tabs>" under Charts tab

    Examples:
    |  UserType       | User           | Charts Tabs        |
    | Admin User      | Administration | Needs              |
    | Operator User   | Operator       | Potential Benefits |
    | Admin User      | Administration | Budget             |
    | Operator User   | Operator       | Budget Spent       |
  

  Scenario Outline: Verify that download button is displayed disabled when no data is available on all Charts tabs
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    When I click on "<Charts Tabs>" tab from charts dropdown menu
    Then I verify that download button is displayed disabled on "<Charts Tabs>" under Charts tab

    Examples:
    |  UserType       | User           | Charts Tabs        |
    | Admin User      | Administration | Needs              |
    | Operator User   | Operator       | Potential Benefits |
    | Admin User      | Administration | Budget             |
    | Operator User   | Operator       | Budget Spent       |
 

  Scenario Outline: Verify that No record found message is displayed when treatment pool is filled but scenario is not executed
    And I click on Actions three dots of scenario page
    And I click on import option scenario page
    And I import "BAMS" file "BAMS_TestData.xlsx"
    And I search the added scenario
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    And I verify that No records found message is displayed

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |

  Scenario Outline: Verify that "No data available in table" message is displayed on all Reports tabs when treatment pool is empty
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    When I click on "<Reports Tabs>" tab from reports dropdown menu
    Then I verify that "No data available in table" message is displayed on "<Reports Tabs>" under Reports tab 

    Examples:
    |  UserType       | User           | Reports Tabs      |
    | Admin User      | Administration | Project Summary   |
    | Operator User   | Operator       | Treatment Summary |
    | Admin User      | Administration | Combined Projects |
  

  Scenario Outline: Verify that download button is not displayed on Reports tabs when treatment pool is empty
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    When I click on "<Reports Tabs>" tab from reports dropdown menu
    Then I verify that download button is not displayed on "<Reports Tabs>" under Reports tab

    Examples:
    |  UserType       | User           | Reports Tabs      |
    | Admin User      | Administration | Project Summary   |
    | Operator User   | Operator       | Treatment Summary |
    | Admin User      | Administration | Combined Projects |
 

  Scenario Outline: Verify that user is able to navigate Map page
    Then I verify that added scenario is displayed under Scenarios list
    When I click on Actions three dots of scenario page
    And I click on Analysis option from action menu
    Then I verify that user navigate to "Projects" page
    When I click on Map tab
    Then I verify that Map page is loaded successfully

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |


