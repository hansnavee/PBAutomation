package PageObjects.Pages;

import Driver.Driver;
import Driver.WaitUntil;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Pages extends Driver {
    protected final WaitUntil wait;

    public Pages(WebDriver driver) {
        super(driver);

        Allure.step("🔧 Initializing page object: " + this.getClass().getSimpleName());
        System.out.println("🔧 Initializing page object: " + this.getClass().getSimpleName());

        this.wait = new WaitUntil(driver);
    }

    public void safeClick(By locator) {
        try {
            waitForLoaderToDisappear();
            WebDriverWait waitClickable = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement element = waitClickable.until(ExpectedConditions.elementToBeClickable(locator));
            Allure.step("Clicking element: " + locator);
            element.click();

            waitForLoaderToDisappear();

        } catch (TimeoutException e) {
            Allure.step("Element not clickable within timeout: " + locator);
            throw e;
        }
    }

    private void waitForLoaderToDisappear() {
        wait.waitForAllLoaderContainersToDisappear(60);
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void sendKeys(Keys key){
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    public boolean waitForStyleToBe(By locator, String expectedStyle) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(Exception.class);

            return wait.until(driver -> {
                WebElement element = driver.findElement(locator); // re-locate each time
                String style = element.getAttribute("style");
                System.out.println("Current style = " + style);
                return style != null && style.contains(expectedStyle);
            });

        } catch (TimeoutException e) {
            System.out.println("❌ Expected style not applied within timeout: " + expectedStyle);
            return false;
        }
    }


    private static final By TOAST = By.xpath("//div[contains(@class,'toast-body')]");

    public boolean waitForToastMessage(String expectedMessage, int timeoutSec) {
        WebDriverWait toastWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        toastWait.pollingEvery(Duration.ofMillis(200));
        toastWait.ignoring(NoSuchElementException.class);
        toastWait.ignoring(StaleElementReferenceException.class);

        try {
            return toastWait.until(d -> {
                List<WebElement> toasts = d.findElements(TOAST);
                for (WebElement toast : toasts) {
                    if (toast.isDisplayed() && toast.getText().trim().contains(expectedMessage)) {
                        return true;
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean waitForToastDisappear(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }
}