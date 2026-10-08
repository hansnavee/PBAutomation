@regression @password_reset
Feature: ForgotYourPassword

  @smoke
  Scenario: Validate user navigate to Forgot your password Page
    When I click on forgot your password link
    Then I verify user navigate to forgot your password page
    And I verify cancel link is displayed
    And I verify email field is displayed
    And I verify heading text "User Details" is displayed
    And I verify send verification code button is displayed
    And I verify send verification code button is enabled
    And I verify continue button is displayed
    And I verify continue button is disabled

  Scenario: Validation Message for Blank Email in Forgot Password Page
    When I click on forgot your password link
    And I click on Send verification code button
    Then I verify validation message "Email Address is required." is displayed

  Scenario: Validation Message for Blank Verification Code
    When I click on forgot your password link
    And I enter forgot email address "test@yopmail.com"
    And I click on Send verification code button
    And I click on Verify code button
    Then I verify validation message "Verification Code is required." is displayed
    And I verify continue button is disabled