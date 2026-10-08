package PageObjects.Pages.CreateScenarioTabs;

import PageObjects.Locators.CreateScenarioMenuLocators;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LeftSideBarMenu extends Pages {

    public LeftSideBarMenu(WebDriver driver) {
        super(driver);
    }

    public void waitForDomToStabilize() {
        WebDriverWait ready = new WebDriverWait(driver, Duration.ofSeconds(20));
        ready.until(d -> {
            Object state = ((JavascriptExecutor) d).executeScript("return document.readyState");
            return state != null && "complete".equals(state.toString());
        });
        wait.waitForAllLoaderContainersToDisappear(20);
    }

    public WebElement getMenuItem(String tabName) {
        return findElement(LocatorsType.ByXpath, CreateScenarioMenuLocators.LinkXpath(tabName));
    }
}
