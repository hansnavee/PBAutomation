package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;

public class LoginPage extends Pages {

    private final String EmailField = LoginLocators.UsernameOrEmailByName;
    private final String PasswordField = LoginLocators.PasswordByID;
    private final String SignInButton = LoginLocators.SignInByXpath;
    private final String ForgotYourPasswordLink = LoginLocators.ForgotYourPasswordLinkByXpath;
    private final String EmailValidationMessage = LoginLocators.EmailFieldValidationByXpath;
    private final String PasswordValidationMessage = LoginLocators.PasswordFieldValidationByXpath;
    private final String ValidationMessage = LoginLocators.ValidationErrorMessageByXpath;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ------------- Screenshot Attachment ------------- //

    private void attachScreenshot(String stepName) {
        try {
            Allure.addAttachment(
                    stepName + " - Screenshot",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))
            );
        } catch (Exception ignored) {}
    }

    // ------------- LOGIN METHODS WITH ALLURE LOGGING ------------- //

    @Step("Login with email: {email}")
    public void loginWithValidCredentials(String email, String password) {
        Allure.step("🔹 Attempting login with user: " + email);

        try {
            enterEmail(email);
            enterPassword(password);

            WebElement signInBtn = wait.untilElementClickable(LocatorsType.ByXpath, SignInButton);
            Allure.step("Clicking on Sign In button");
            signInBtn.click();
            AllureLogger.passStep("Clicked Sign In button successfully");
            attachScreenshot("After clicking sign-in");

        } catch (Exception e) {
            AllureLogger.failStep("Login failed", e);
            attachScreenshot("Login failure");
            throw e;
        }
    }

    @Step("Login using Enter key with email: {email}")
    public void loginWithValidCredentialsAndEnterKey(String email, String password) {
        Allure.step("🔹 Attempting login with Enter key for user: " + email);

        try {
            enterEmail(email);
            enterPassword(password);

            Allure.step("Pressing ENTER key to submit login");
            sendKeys(Keys.ENTER);
            AllureLogger.passStep("ENTER key submitted login successfully");
            attachScreenshot("After pressing ENTER");

        } catch (Exception e) {
            AllureLogger.failStep("Login using Enter key failed", e);
            attachScreenshot("Login enter-key failure");
            throw e;
        }
    }

    // ------------- INPUT FIELD ACTIONS WITH LOGGING ------------- //

    @Step("Entering email: {email}")
    public void enterEmail(String email) {
        WebElement emailField = wait.untilElementVisible(LocatorsType.ByID, EmailField);
        if (emailField == null) {
            AllureLogger.failStep("Email field not found", new Exception("Element null"));
            attachScreenshot("Email field missing");
            return;
        }
        Allure.step("Typing email: " + email);
        emailField.clear();
        emailField.sendKeys(email);
        AllureLogger.passStep("Entered email successfully");
    }

    @Step("Entering password")
    public void enterPassword(String password) {
        WebElement passwordField = wait.untilElementVisible(LocatorsType.ByID, PasswordField);
        if (passwordField == null) {
            AllureLogger.failStep("Password field not found", new Exception("Element null"));
            attachScreenshot("Password field missing");
            return;
        }
        Allure.step("Typing password");
        passwordField.clear();
        passwordField.sendKeys(password);
        AllureLogger.passStep("Entered password successfully");
    }

    // ------------- ELEMENT GETTERS WITH ALLURE LOGGING ------------- //

    @Step("Get password field")
    public WebElement getPasswordField() {
        Allure.step("Locating Password field");
        return wait.untilElementVisible(LocatorsType.ByID, PasswordField);
    }

    @Step("Get email field")
    public WebElement getEmailField() {
        Allure.step("Locating Email field");
        return wait.untilElementVisible(LocatorsType.ByID, EmailField);
    }

    @Step("Get Sign-In button")
    public WebElement getSignInButton() {
        Allure.step("Locating Sign-In button");
        return wait.untilElementVisible(LocatorsType.ByXpath, SignInButton);
    }

    @Step("Get email validation message")
    public WebElement getEmailValidationMessage() {
        Allure.step("Locating email validation message");
        return wait.untilElementVisible(LocatorsType.ByXpath, EmailValidationMessage);
    }

    @Step("Get password validation message")
    public WebElement getPasswordValidation() {
        Allure.step("Locating password validation message");
        return wait.untilElementVisible(LocatorsType.ByXpath, PasswordValidationMessage);
    }

    @Step("Get generic validation error message")
    public WebElement getValidationErrorMessage() {
        Allure.step("Locating validation error message");
        return wait.untilElementVisible(LocatorsType.ByXpath, ValidationMessage);
    }

    @Step("Get Forgot Password link")
    public WebElement getForgotYourPasswordLink() {
        Allure.step("Locating Forgot Your Password link");
        return wait.untilElementVisible(LocatorsType.ByXpath, ForgotYourPasswordLink);
    }

    // ------------- VALIDATION CHECK ------------- //

    @Step("Check if email validation message is visible")
    public boolean isEmailValidationElementVisible() {
        try {
            WebElement element = findElement(LocatorsType.ByXpath, EmailValidationMessage);
            Allure.step("Email validation message visibility: " + element.isDisplayed());
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            Allure.step("Email validation message not visible");
            return false;
        }
    }
}