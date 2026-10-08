@regression @login
Feature: Login

  @smoke
  Scenario Outline: Verify that user is able to login with valid credentials
    Given I login with "<User Type>"
    Then I verify user navigate to "<User>" Dashboard

    Examples:
      | User Type     | User           |
      | Admin User    | Administration |
      | Guest User    | Guest          |
      | Operator User | Operator       |

  Scenario Outline: Verify Login Successful by Pressing Enter Key in Password Field
    Given I login with pressing enter key "<User Type>"
    Then I verify user navigate to "<User>" Dashboard

    Examples:
      | User Type     | User           |
      | Admin User    | Administration |
      | Guest User    | Guest          |
      | Operator User | Operator       |

  Scenario: Validation Message for Empty Email Address Field
    When I enter password "password"
    And I click Sign In button
    Then I verify email field validation message "Please enter your Email Address" displayed

  Scenario: Validation Message for Empty password Field
    When I enter email address "emailtest@email.com"
    And I click Sign In button
    Then I verify password field validation message "Please enter your password" displayed

  Scenario: Email Field Validation for Correct Format
    When I enter email address "valid.format@example.com"
    Then I verify email field validation message not displayed

  Scenario: Validation Message for Invalid Email Format
    When I enter email address "not-an-email"
    Then I verify email field validation message "Please enter a valid email address." displayed

  Scenario: Login Attempt with Invalid Email Address and Password
    When I enter email address "invalidemail@gmail.com"
    And I enter password "password123"
    And I click Sign In button
    Then I verify validation "Invalid username or password." message is displayed

  Scenario: Verify Password Masking on Sign In Page
    When I enter password "password123"
    Then I verify "password" is masked
