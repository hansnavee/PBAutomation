package PageObjects.Pages.Analysis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;

public class MapPage extends Pages{

    private static String ProjectTreatmentsHeading = MapLocators.ProjectTreatmentsHeading;

    public MapPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProjectTreatmentsHeadingText() {
        return wait.untilElementPresent(LocatorsType.ByXpath, ProjectTreatmentsHeading);
    }
    
}
