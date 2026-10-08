package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.LocatorsType;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;

public class PasswordResetPage extends Pages {

    private final String CancelLink = PasswordResetLocators.CancelLinkByXpath;
    private final String EmailField = PasswordResetLocators.EmailFieldByXpath;
    private final String HeadingText = PasswordResetLocators.HeadingTextByXpath;
    private final String RequiredValidationText = PasswordResetLocators.RequiredFieldValidationTextByXpath;
    private final String SendVerificationButton = PasswordResetLocators.SendVerificationCodeButtonByXpath;
    private final String ContinueButton = PasswordResetLocators.ContinueButtonByXpath;
    private final String VerifyCodeButton = PasswordResetLocators.VerifyCodeButtonByID;

    private final String ForgotYourPasswordLink = LoginLocators.ForgotYourPasswordLinkByXpath;

    public PasswordResetPage(WebDriver driver) {
        super(driver);
    }

    // -------- Reusable Screenshot for Allure -------- //
    private void attachScreenshot(String stepName) {
        Allure.addAttachment(stepName + " - Screenshot",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    // -------------- ELEMENTS WITH ALLURE LOGGING -------------- //

    @Step("Get 'Cancel' link on Password Reset Page")
    public WebElement getCancelLink() {
        Allure.step("Locating the Cancel link using XPath: " + CancelLink);
        WebElement el = wait.untilElementVisible(LocatorsType.ByXpath, CancelLink);
        return el;
    }

    @Step("Get Email Field on Password Reset Page")
    public WebElement getEmailField() {
        Allure.step("Locating the Email input field using XPath: " + EmailField);
        WebElement el = wait.untilElementVisible(LocatorsType.ByXpath, EmailField);
        return el;
    }

    @Step("Get Heading Text on Password Reset Page")
    public WebElement getHeadingText() {
        Allure.step("Locating the main heading text using XPath: " + HeadingText);
        return wait.untilElementVisible(LocatorsType.ByXpath, HeadingText);
    }

    @Step("Get Required Field Validation Text")
    public WebElement getRequiredValidationText() {
        Allure.step("Locating Required Field validation using XPath: " + RequiredValidationText);
        return wait.untilElementVisible(LocatorsType.ByXpath, RequiredValidationText);
    }

    @Step("Get Send Verification Code button")
    public WebElement getSendVerificationButton() {
        Allure.step("Locating Send Verification button using XPath: " + SendVerificationButton);
        return wait.untilElementVisible(LocatorsType.ByXpath, SendVerificationButton);
    }

    @Step("Get Verify Code button")
    public WebElement getVerifyCodeButton() {
        Allure.step("Locating Verify Code button using ID: " + VerifyCodeButton);
        return wait.untilElementVisible(LocatorsType.ByID, VerifyCodeButton);
    }

    @Step("Get Continue button on Password Reset Page")
    public WebElement getContinueButton() {
        Allure.step("Locating Continue button using XPath: " + ContinueButton);
        return wait.untilElementVisible(LocatorsType.ByXpath, ContinueButton);
    }

    // -------- Additional Step Example: Click Forgot Password -------- //
    @Step("Click 'Forgot your Password' Link")
    public void clickForgotYourPassword() {
        Allure.step("Clicking the Forgot Your Password link");
        WebElement el = wait.untilElementVisible(LocatorsType.ByXpath, ForgotYourPasswordLink);
        el.click();
        attachScreenshot("Clicked Forgot Your Password");
    }
}