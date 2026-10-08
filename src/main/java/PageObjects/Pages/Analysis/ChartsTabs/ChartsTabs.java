package PageObjects.Pages.Analysis.ChartsTabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;

public class ChartsTabs extends Pages{
    private final String NoRecordsFoundMessage = ChartsTabLocators.NoRecordsFoundMessageByXpath;
    private final String DownloadButton = ChartsTabLocators.DownloadbuttonByID;

    public ChartsTabs(WebDriver driver) {
        super(driver);

    }

    public WebElement getNoRecordFoundMessage() {
        wait.untilElementHasText(LocatorsType.ByXpath, NoRecordsFoundMessage);
        return wait.untilElementPresent(LocatorsType.ByXpath, NoRecordsFoundMessage);
    }

    public WebElement getDownloadButton() {
        return wait.untilElementPresent(LocatorsType.ByID, DownloadButton);
    }
}
