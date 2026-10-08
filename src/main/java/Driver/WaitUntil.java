package Driver;

import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.cucumber.java.an.E;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WaitUntil {

    private final WebDriver driver;
    int defaultWaitTime = 60;

    public WaitUntil(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultWaitTime));
    }

    private By getBy(LocatorsType locatorType, String locator) {
        switch (locatorType) {
            case ByXpath: return By.xpath(locator);
            case ByID: return By.id(locator);
            case ByClassName: return By.className(locator);
            case ByName: return By.name(locator);
            case ByCss: return By.cssSelector(locator);
            case ByTagName: return By.tagName(locator);
            default: throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }

    public WebElement untilElementVisible(
        LocatorsType locatorType,
        String locator) {

    WebDriverWait wait = getWait();

    return wait.until(driver -> {
        try {
            WebElement element = driver.findElement(getBy(locatorType, locator));
            return element.isDisplayed() ? element : null;
        } catch (StaleElementReferenceException e) {
            return null;
        }
    });
}

    // ✅ Wait until element is clickable
    public WebElement untilElementClickable(LocatorsType locatorType, String locator) {
        if (!waitForAllLoaderContainersToDisappear(defaultWaitTime)) {
        throw new TimeoutException("Loader containers still visible");
        }
        return Allure.step("⏳ Waiting for element to be clickable: [" + locatorType + "] " + locator, () -> {
            try {
                WebElement element = getWait().until(
                        ExpectedConditions.elementToBeClickable(getBy(locatorType, locator))
                );
                AllureLogger.passStep("Element clickable: " + locator);
                return element;
            } catch (TimeoutException | NoSuchElementException e) {
                AllureLogger.failStep("Element not clickable: " + locator, e);
                throw e;
            }
        });
    }

    // ✅ Wait until element is present in DOM
    public WebElement untilElementPresent(LocatorsType locatorType, String locator) {
          if (!waitForAllLoaderContainersToDisappear(defaultWaitTime)) {
        throw new TimeoutException("Loader containers still visible");
        }
        return Allure.step("⏳ Waiting for element to be present: [" + locatorType + "] " + locator, () -> {
            try {
                WebElement element = getWait().until(
                        ExpectedConditions.presenceOfElementLocated(getBy(locatorType, locator))
                );
                AllureLogger.passStep("Element present in DOM: " + locator);
                return element;
            } catch (TimeoutException | NoSuchElementException e) {
                AllureLogger.failStep("Element not present in DOM: " + locator, e);
                throw e;
            }
        });
    }

    // ✅ Wait until element is visible AND contains non-empty text (React-safe)
    public void untilElementHasText(LocatorsType locatorType, String locator) {
        if (!waitForAllLoaderContainersToDisappear(defaultWaitTime)) {
        throw new TimeoutException("Loader containers still visible");
        }
        Allure.step("⏳ Waiting for element to have non-empty text: [" + locatorType + "] " + locator, () -> {
            try {
                // Wait for element to be visible first
                WebElement element = getWait().until(
                        ExpectedConditions.visibilityOfElementLocated(getBy(locatorType, locator))
                );

                // Wait until the element contains some actual text
                getWait().until(driver -> {
                    String text = element.getText().trim();
                    return !text.isEmpty();
                });

                AllureLogger.passStep("Element has non-empty text: " + locator);
                return element;

            } catch (Exception e) {
                AllureLogger.failStep("Element did NOT load text: " + locator, e);
                throw e;
            }
        });
    }

    public void untilElementDisappear(LocatorsType locatorType, String locator) {
        try {
            By by = getBy(locatorType, locator);
            // seconds
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultWaitTime));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element did not disappear: " + locator + " - " + e.getMessage());
        }
    }

    public List<WebElement> untilAllElementsVisible(LocatorsType type, String locator) {
        if (!waitForAllLoaderContainersToDisappear(defaultWaitTime)) {
        throw new TimeoutException("Loader containers still visible");
        }
        By by = getBy(type, locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultWaitTime));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public boolean waitUntilCondition(Function<WebDriver, Boolean> condition,
                                       Duration timeout,
                                       Duration polling) {

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(condition);
    }

   public boolean waitForAllLoaderContainersToDisappear(int timeoutSec) {
    By loader = By.id("loader-container");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    wait.pollingEvery(Duration.ofMillis(200));
    wait.ignoring(StaleElementReferenceException.class);

    try {
        return wait.until(d -> {
            List<WebElement> loaders = d.findElements(loader);

            // 🔑 Key condition
            return loaders.stream().noneMatch(WebElement::isDisplayed);
        });
    } catch (TimeoutException e) {
        return false;
    }
}

public List<WebElement> untilAllElementsPresent(
        LocatorsType locatorType,
        String locator) {

    WebDriverWait wait = getWait();
    return wait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(
            getBy(locatorType, locator)
            )
        );
    }

    public WebElement findFirstVisible(Duration timeout, By... locators) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.pollingEvery(Duration.ofMillis(250));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(webDriver -> {
            for (By locator : locators) {
                for (WebElement element : webDriver.findElements(locator)) {
                    try {
                        if (element.isDisplayed()) {
                            return element;
                        }
                    } catch (StaleElementReferenceException ignored) {
                        // The node was redrawn. Try the next match.
                    }
                }
            }
            return null;
        });
    }
}