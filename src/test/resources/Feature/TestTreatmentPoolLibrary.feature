@regression @treatment_pool
Feature: Treatment Pool Library

  Background:
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Treatment Pool Libraries"

  @smoke
  Scenario Outline: Verify  Navigation to Treatment Pool Library
    Then I verify navigate to "Treatment Pool Libraries" page

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Validation Message for Name Field Max Limit
    Then I verify navigate to "Treatment Pool Libraries" page
    When I click on Create New Treatment Pool Button
    And I enter characters more than max limit of name field
    Then I verify Name Field not allow to copy more than max limit characters

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify "Create New Treatment Pool Library" dialog elements
    Then I verify navigate to "Treatment Pool Libraries" page
    When I click on Create New Treatment Pool Button
    Then I Create New Treatment Pool dialog displayed
    And I verify Name field is displayed
    And I verify Description field is displayed
    And I verify shared toggle button is displayed
    And I verify shared toggle label is displayed
    And I verify Cancel button is displayed
    And I verify Create button is displayed
    And I verify Close icon is displayed
    When I click on close icon
    Then I verify Create New Treatment Pool library dialog is closed
    And I verify navigate to "Treatment Pool Libraries" page

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify Cancel Button Functionality
    Then I verify navigate to "Treatment Pool Libraries" page
    When I click on Create New Treatment Pool Button
    And I click on cancel button
    Then I verify Create New Treatment Pool library dialog is closed
    And I verify navigate to "Treatment Pool Libraries" page

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  @smoke
  Scenario Outline: Verify Edit Treatment Pool library Functionality
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I select on edit option
    Then I Edit Treatment Pool dialog displayed
    And I verify Name field value is retained
    And I verify Description field value is retained
    And I verify Cancel button is displayed on edit form
    And I verify Update button is displayed
    And I verify Close icon is displayed on edit form
    When I update the Name of treatment pool library
    Then I verify that added treatment pool is displayed under treatment pool library list

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  @smoke
  Scenario Outline: Verify that user is able to generate a new treatment pool
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list

    Examples:
    |  UserType       | User           |
    | Admin User      | Administration |
    | Operator User   | Operator       |


  @smoke
  Scenario Outline: Verify that user is able to generate a new treatment pool without description
    And I create a new treatment pool "without" "description" field
    Then I verify that validation error "Field is required" message displayed for description field

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  @smoke
  Scenario Outline: Verify that user is not able to generate a new treatment pool without name
    And I create a new treatment pool "without" "name" field
    Then I verify that validation error "Field is required" message displayed for name field

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that validation error message is displayed without all required fields
    And I click on Create New Treatment Pool Button
    And I click on create button
    Then I verify that validation error "Field is required" message displayed for all required fields

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that new treatment pool is not created on click cancel button
    And I click on Create New Treatment Pool Button
    And I fill all required fields for new treatment pool creation
    And I click on cancel button
    And I search the added treatment pool
    Then I verify that treatment pool is not displayed under treatment pool library list

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify Options under treatment Action
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    Then I Verify that "Edit" option is displayed under treatments actions
    And I Verify that "Import" option is displayed under treatments actions
    And I Verify that "Delete" option is displayed under treatments actions
    And I Verify that "Treatments" option is displayed under treatments actions
    And I Verify that "Copy" option is displayed under treatments actions

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify Delete Option is disable under treatment Action
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    Then I Verify that "Delete" option is displayed under treatments actions
    And I Verify that Delete option is displayed under treatments actions

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify Create New Treatment Dialog opened on clicking Copy Option under treatment Action
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    Then I Verify that "Copy" option is displayed under treatments actions
    When I click on Copy option
    Then I Create New Treatment Pool dialog displayed

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to import the BAMS treatments in treatment pool without multiple tabs
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "BAMS" file "BAMS_TestData.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that "2388" treatment imported successfully from treatment page

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to import the BAMS treatments in treatment pool with multiple tabs
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "BAMS" file "BAMS_TestData_With_Multiple_Tabs.xlsx" with "BAMS TREATMENTS"
    And I click on "Treatment Pool Libraries"
    And I search the added treatment pool
    Then I validate that "2388" treatment imported successfully from treatment page

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to import the PAMS treatments in treatment pool without multiple tabs
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "PAMS" file "PAMS_TestData.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that "1026" treatment imported successfully from treatment page

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to import the PAMS treatments in treatment pool with Multiple tabs
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    And I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "PAMS" file "PAMS_TestData.xlsx" with "PAMS_TREATMENTS"
    Then I validate that system display inprogress message invisible "Importing treatments from excel file please wait"
    When I search the added treatment pool
    Then I validate that "1026" treatment imported successfully from treatment page

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |



  Scenario Outline: Verify that system through error import the PAMS treatments without tabName when multiple tabs exists
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "PAMS" file "PAMS_TestData_With_Multiple_Tabs.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that system through import error "The target database table tbl_import_PAMS_Treatments expects column but the file is empty."

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that system through error import the PAMS treatments with invalid tabName
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "PAMS" file "PAMS_TestData_With_Multiple_Tabs.xlsx" with "PAMS_TEST"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that system through import error "Tab [PAMS_TREAT] not found in the file"

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that system through error import the BAMS treatments without tabName when multiple tabs exists
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "BAMS" file "BAMS_TestData_With_Multiple_Tabs.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that system through import error "The target database table tbl_import_BAMS_Treatments expects column but the file is empty."

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that system through error import the BAMS treatments with invalid tabName
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Import option from treatment page
    And I import "BAMS" file "BAMS_TestData_With_Multiple_Tabs.xlsx" with "BAMS_TEST"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that system through import error "Tab [BAMS_TEST] not found in the file"

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to navigate treatments page when treatment pool is empty
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I click on Treatments option from treatment page
    Then I verify user navigate to "Treatments" page
    And I verify treatment page is empty

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that user is able to navigate treatments page when treatment pool have treatments
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I import "BAMS" file "BAMS_TestData.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that "2388" treatment imported successfully from treatment page
    When I click on selected three dots under Action column
    And I click on Treatments option from treatment page
    Then I verify user navigate to "Treatments" page
    And I verify treatment page is not empty

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |


  Scenario Outline: Verify that Reset functionality is working on treatment page
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on Reset button
    Then I verify that search field is empty
    And I verify that table data is reset

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |
