@regression @treatment_pool
Feature: Add Treatments

  Scenario Outline: Verify that user is able to open create treatment modal
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Treatment Pool Libraries"
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I import "BAMS" file "BAMS_TestData.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that "2388" treatment imported successfully from treatment page
    When I click on selected three dots under Action column
    And I click on Treatments option from treatment page
    And I click on Create New Treatment button
    Then I verify that "Create Treatment" modal is displayed

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |

  Scenario Outline: Verify that user is able to close treatment modal on clicking on Cancel button
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Treatment Pool Libraries"
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I import "BAMS" file "BAMS_TestData.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that "2388" treatment imported successfully from treatment page
    When I click on selected three dots under Action column
    And I click on Treatments option from treatment page
    And I click on Create New Treatment button
    And I click on Cancel button in Create Treatment modal
    Then I verify that Create Treatment modal is not displayed

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |

  Scenario Outline: Verify that user is able to close treatment modal on clicking on Close icon
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Treatment Pool Libraries"
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I import "BAMS" file "BAMS_TestData.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that "2388" treatment imported successfully from treatment page
    When I click on selected three dots under Action column
    And I click on Treatments option from treatment page
    And I click on Create New Treatment button
    And I click on Close icon in Create Treatment modal
    Then I verify that Create Treatment modal is not displayed

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |

  Scenario Outline: Verify that user is validate date field validation message is displayed on entering past date in Start Date field
    Given I login with "<UserType>"
    Then I verify user navigate to "<User>" Dashboard
    When I click on "Treatment Pool Libraries"
    And I create a new treatment pool "with" "all" field
    And I search the added treatment pool
    Then I verify that added treatment pool is displayed under treatment pool library list
    When I click on selected three dots under Action column
    And I import "BAMS" file "BAMS_TestData.xlsx"
    Then I verify that added treatment pool is displayed under treatment pool library list
    And I validate that "2388" treatment imported successfully from treatment page
    When I click on selected three dots under Action column
    And I click on Treatments option from treatment page
    And I click on Create New Treatment button
    And I click on Close icon in Create Treatment modal
    Then I verify that Create Treatment modal is not displayed

    Examples:
      |  UserType       | User           |
      | Admin User      | Administration |
      | Operator User   | Operator       |