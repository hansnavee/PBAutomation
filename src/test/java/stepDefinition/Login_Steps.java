package stepDefinition;

import PageObjects.Pages.AdministratorPage;
import PageObjects.Pages.DashboardPage;
import PageObjects.Pages.LoginPage;
import PageObjects.models.Application;
import Utils.StepActions;
import Utils.XMLFileUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class Login_Steps extends Base_Steps{

    private final LoginPage loginPage = pages.getLoginPage();
    private final DashboardPage dashboardPage = pages.getDashboardPage();    
    private final AdministratorPage adminPage = pages.getAdministratorPage();
    private Map<String, String> user;

    private static String value(Map<String, String> user, String key) {
        if (user == null || user.get(key) == null) {
            return "";
        }
        return user.get(key).trim();
    }

    private static void requireCredentials(String role, String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Assert.fail("Missing email or password for role '" + role
                    + "'. Set them in src/test/resources/config/users.properties.");
        }
    }

    // ====================================================
    //                LOGIN STEPS
    // ====================================================

    @Given("I login with {string}")
    public void iLoginWith(String role) {
        user = XMLFileUtility.getUsers(role);
        String username = value(user, "Username");
        String password = value(user, "Password");

        StepActions.run("Logging in with Username: " + username,
                () -> {
                    requireCredentials(role, username, password);
                    loginPage.loginWithValidCredentials(username, password);
                }
        );
    }

    @Given("I login with pressing enter key {string}")
    public void iLoginWithEnterKey(String role) {
        user = XMLFileUtility.getUsers(role);
        String username = value(user, "Username");
        String password = value(user, "Password");

        StepActions.run("Logging in using Enter Key: " + username,
                () -> {
                    requireCredentials(role, username, password);
                    loginPage.loginWithValidCredentialsAndEnterKey(username, password);
                }
        );
    }

    // ====================================================
    //             DASHBOARD VERIFICATION
    // ====================================================

    @Then("I verify user navigate to {string} Dashboard")
    public void iVerifyDashboard(String role) {

        StepActions.run("Verifying dashboard for role: " + role, () -> {

            switch (role.toLowerCase()) {

                case "guest":
                    Assert.assertFalse(
                            "Guest user should NOT see skip button",
                            dashboardPage.isSkipButtonDisplayed()
                    );
                    break;

                case "operator":
                    Assert.assertTrue(
                            "Operator user should see skip button",
                            dashboardPage.isSkipButtonDisplayed()
                    );
                    dashboardPage.clickSkipButton();
                    dashboardPage.selectApplication(Application.PROJECT_BUILDER);
                    break;

                case "administration":
                    Assert.assertTrue(
                            "Admin should see Administration menu",
                            adminPage.menuItem(role).isDisplayed()
                    );
                    break;

                default:
                    Assert.fail("Unknown user role: " + role);
            }
        });
    }

    // ====================================================
    //         VALIDATION MESSAGE CHECKS
    // ====================================================

    @Then("I verify validation {string} message is displayed")
    public void iValidateErrorMessage(String expectedMessage) {
        StepActions.run("Validating error message: " + expectedMessage, () -> {
            String actualMessage = loginPage.getValidationErrorMessage().getText();
            Assert.assertEquals("Error message mismatch", expectedMessage, actualMessage);
        });
    }

    @Then("I verify email field validation message {string} displayed")
    public void iVerifyEmailValidation(String message) {

        StepActions.run("Verify Email Validation Message: " + message, () -> {
            Assert.assertEquals(message, loginPage.getEmailValidationMessage().getText());
        });
    }

    @Then("I verify email field validation message not displayed")
    public void iVerifyEmailValidationNotDisplayed() {

        StepActions.run("Verify Email Validation Not Displayed", () -> {
            Assert.assertFalse(loginPage.isEmailValidationElementVisible());
        });
    }

    @Then("I verify password field validation message {string} displayed")
    public void iVerifyPasswordValidation(String msg) {

        StepActions.run("Verify Password Validation Message: " + msg, () -> {
            Assert.assertEquals(msg, loginPage.getPasswordValidation().getText());
        });
    }

    // ====================================================
    //                FIELD INPUT STEPS
    // ====================================================

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        StepActions.run("Enter password", () ->
                loginPage.getPasswordField().sendKeys(password)
        );
    }

    @When("I enter email address {string}")
    public void iEnterEmailAddress(String email) {
        StepActions.run("Enter email: " + email, () ->
                loginPage.getEmailField().sendKeys(email)
        );
    }

    @When("I click Sign In button")
    public void iClickSignInButton() {
        StepActions.run("Click Sign In", () ->
                loginPage.getSignInButton().click()
        );
    }

    @When("I click on forgot your password link")
    public void iClickForgotPassword() {
        StepActions.run("Click Forgot Password Link", () ->
                loginPage.getForgotYourPasswordLink().click()
        );
    }

    // ====================================================
    //          FORGOT PASSWORD PAGE NAVIGATION
    // ====================================================

    @Then("I verify user navigate to forgot your password page")
    public void iVerifyForgotPasswordPage() {

        StepActions.run("Verify Forgot Password Navigation", () -> {
            Assert.assertTrue(
                    loginPage.getCurrentURL().contains("passwordreset")
            );
        });
    }

    // ====================================================
    //              PASSWORD MASKING CHECK
    // ====================================================

    @Then("I verify {string} is masked")
    public void iVerifyPasswordIsMasked(String fieldType) {

        StepActions.run("Verify password masking", () -> {
            Assert.assertEquals(
                    fieldType,
                    loginPage.getPasswordField().getAttribute("type")
            );
        });
    }
}