package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.Application;
import PageObjects.models.LocatorsType;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends Pages {

    private final String SkipButtonLocatorValue = DashboardLocators.SkipButtonByXPATH;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Verify if Skip button is displayed
    public boolean isSkipButtonDisplayed() {
        Allure.step("Verifying if Skip button is displayed on Dashboard");
        try {
            WebElement skipButton = wait.untilElementClickable(LocatorsType.ByXpath, SkipButtonLocatorValue);
            boolean displayed = skipButton.isDisplayed();
            Allure.step("Skip button display status: " + displayed);
            return displayed;
        } catch (Exception e) {
            Allure.step("❌ Skip button not found or not visible: " + e.getMessage());
            System.err.println("Skip button not found or not visible: " + e.getMessage());
            return false;
        }
    }

    // ✅ Click Skip button safely
    public void clickSkipButton() {
        Allure.step("Attempting to click Skip button on Dashboard");
        try {
            WebElement skipButton = wait.untilElementClickable(LocatorsType.ByXpath, SkipButtonLocatorValue);
            skipButton.click();
            Allure.step("✅ Clicked Skip button successfully.");
            System.out.println("Clicked Skip button successfully.");
        } catch (Exception e) {
            Allure.step("❌ Failed to click Skip button: " + e.getMessage());
            System.err.println("Failed to click Skip button: " + e.getMessage());
        }
    }

    // ✅ Safely click application button from Dashboard
    public void selectApplication(Application applicationName) {

        String app = applicationName.getApplicationName();
        Allure.step("Attempting to click application: " + app);

        try {
            By by = By.xpath(DashboardLocators.applicationNamesXpath(app));

            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(25));
            w.pollingEvery(Duration.ofMillis(200));

            // Wait for element to appear in DOM
            WebElement element = w.until(ExpectedConditions.presenceOfElementLocated(by));

            // Wait until visible
            element = w.until(ExpectedConditions.visibilityOfElementLocated(by));

            // Scroll into view (important for left menus + dashboards)
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

            // Wait until clickable
            element = w.until(ExpectedConditions.elementToBeClickable(by));

            // Primary click
            try {
                element.click();
            } catch (Exception clickException) {
                // JS click fallback (reliable for overlapping or animated UIs)
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", element);
            }

            Allure.step("✔ Successfully clicked application: " + app);

        } catch (Exception e) {
            Allure.step("❌ Failed to click application: " + app + " → " + e.getMessage());
            throw e; // rethrow so test fails cleanly
        }
    }

}