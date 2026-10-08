package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class LoginPage extends Pages {

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

            WebElement signInBtn = signInButton();
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
        openCredentialForm();
        WebElement emailField = emailField();
        Allure.step("Typing email: " + email);
        emailField.clear();
        emailField.sendKeys(email);
        AllureLogger.passStep("Entered email successfully");
    }

    @Step("Entering password")
    public void enterPassword(String password) {
        WebElement passwordField = passwordField();
        Allure.step("Typing password");
        passwordField.clear();
        passwordField.sendKeys(password);
        AllureLogger.passStep("Entered password successfully");
    }

    private void openCredentialForm() {
        try {
            emailField(Duration.ofSeconds(25));
        } catch (TimeoutException notOnCredentialPage) {
            WebElement azureSignIn = wait.findFirstVisible(
                    Duration.ofSeconds(5),
                    By.cssSelector("button[name='provider'][value='AzureAd']"),
                    By.cssSelector("button.btn-signin-nav")
            );
            azureSignIn.click();
        }
    }

    private WebElement emailField() {
        return emailField(Duration.ofSeconds(30));
    }

    private WebElement emailField(Duration timeout) {
        return wait.findFirstVisible(
                timeout,
                By.id("email"),
                By.name("email"),
                By.id("signInName"),
                By.name("loginfmt"),
                By.id("i0116")
        );
    }

    private WebElement passwordField() {
        try {
            return wait.findFirstVisible(Duration.ofSeconds(8), By.id("password"), By.name("passwd"), By.id("i0118"));
        } catch (TimeoutException passwordNotShownYet) {
            signInButton().click();
            return wait.findFirstVisible(Duration.ofSeconds(20), By.id("password"), By.name("passwd"), By.id("i0118"));
        }
    }

    private WebElement signInButton() {
        return wait.findFirstVisible(
                Duration.ofSeconds(15),
                By.id("next"),
                By.id("idSIButton9"),
                By.xpath(SignInButton)
        );
    }

    // ------------- ELEMENT GETTERS WITH ALLURE LOGGING ------------- //

    @Step("Get password field")
    public WebElement getPasswordField() {
        Allure.step("Locating Password field");
        return passwordField();
    }

    @Step("Get email field")
    public WebElement getEmailField() {
        Allure.step("Locating Email field");
        return emailField();
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