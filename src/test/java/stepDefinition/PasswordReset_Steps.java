package stepDefinition;

import PageObjects.Pages.PasswordResetPage;
import Utils.StepActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PasswordReset_Steps extends Base_Steps{

    private final PasswordResetPage passwordResetPage = pages.getPasswordResetPage();

    @Then("I verify cancel link is displayed")
    public void iVerifyCancelLinkIsDisplayed() {
        StepActions.run("Verify Cancel link is displayed", () ->
                Assert.assertTrue("Cancel link should be displayed",
                        passwordResetPage.getCancelLink().isDisplayed())
        );
    }

    @Then("I verify email field is displayed")
    public void iVerifyEmailFieldIsDisplayed() {
        StepActions.run("Verify Email field is displayed", () ->
                Assert.assertTrue("Email field should be displayed",
                        passwordResetPage.getEmailField().isDisplayed())
        );
    }

    @When("I enter forgot email address {string}")
    public void iEnterForgotEmail(String emailAddress) {
        StepActions.run("Enter Forgot Email: " + emailAddress, () ->
                passwordResetPage.getEmailField().sendKeys(emailAddress)
        );
    }

    @Then("I verify validation message {string} is displayed")
    public void iVerifyValidationMessage(String expectedMessage) {
        StepActions.run("Verify Validation Message: " + expectedMessage, () ->
                Assert.assertEquals(
                        "Validation message mismatch",
                        expectedMessage,
                        passwordResetPage.getRequiredValidationText().getText()
                )
        );
    }

    @Then("I verify heading text {string} is displayed")
    public void iVerifyHeadingText(String expectedHeading) {
        StepActions.run("Verify Heading Text: " + expectedHeading, () ->
                Assert.assertEquals(
                        "Heading text mismatch",
                        expectedHeading,
                        passwordResetPage.getHeadingText().getText()
                )
        );
    }

    @Then("I verify send verification code button is displayed")
    public void iVerifySendVerificationCodeIsDisplayed() {
        StepActions.run("Verify Send Verification Code button is displayed", () ->
                Assert.assertTrue(
                        "Send Verification Code button should be displayed",
                        passwordResetPage.getSendVerificationButton().isDisplayed()
                )
        );
    }

    @When("I click on Send verification code button")
    public void iClickSendVerificationCodeButton() {
        StepActions.run("Click Send Verification Code button", () ->
                passwordResetPage.getSendVerificationButton().click()
        );
    }

    @When("I click on Verify code button")
    public void iClickVerifyCodeButton() {
        StepActions.run("Click Verify Code button", () ->
                passwordResetPage.getVerifyCodeButton().click()
        );
    }

    @Then("I verify continue button is displayed")
    public void iVerifyContinueButtonIsDisplayed() {
        StepActions.run("Verify Continue button is displayed", () ->
                Assert.assertTrue("Continue button should be displayed",
                        passwordResetPage.getContinueButton().isDisplayed())
        );
    }

    @Then("I verify send verification code button is enabled")
    public void iVerifySendCodeButtonEnabled() {
        StepActions.run("Verify Send Verification Code button is enabled", () ->
                Assert.assertTrue(
                        "Send Verification Code button should be enabled",
                        passwordResetPage.getSendVerificationButton().isEnabled()
                )
        );
    }

    @Then("I verify continue button is disabled")
    public void iVerifyContinueButtonDisabled() {
        StepActions.run("Verify Continue button is disabled", () -> {
            String ariaDisabled = passwordResetPage.getContinueButton().getAttribute("aria-disabled");
            Assert.assertTrue(
                    "Continue button should be disabled",
                    Boolean.parseBoolean(ariaDisabled)
            );
        });
    }
}