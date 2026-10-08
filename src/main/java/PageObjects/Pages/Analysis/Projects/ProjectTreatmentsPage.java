package PageObjects.Pages.Analysis.Projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;

public class ProjectTreatmentsPage extends Pages  {

    private final String PageHeading = ProjectTreatmentLocators.ProjectTreatmentsHeadingByXpath;
    private final String NoRecordsFoundMessage = ProjectLocators.NoRecordsFoundMessageByXpath;

    public ProjectTreatmentsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageHeading() {
        return wait.untilElementPresent(LocatorsType.ByXpath, PageHeading);
    }

     public WebElement getNoRecordFoundMessage() {
        wait.untilElementHasText(LocatorsType.ByXpath, NoRecordsFoundMessage);
        return wait.untilElementPresent(LocatorsType.ByXpath, NoRecordsFoundMessage);
    }
    
}
