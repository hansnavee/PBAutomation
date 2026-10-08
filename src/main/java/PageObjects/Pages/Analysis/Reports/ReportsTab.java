package PageObjects.Pages.Analysis.Reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;

public class ReportsTab extends Pages{

    public static final String Reports = LeftTabMenuLocators.ReportsByXpath;
    public static final String NoDataAvailable = LeftTabMenuLocators.NoDataAvailableByXpath;
    private final String DownloadButton = ReportsTabLocators.DownloadbuttonByID;

    public ReportsTab(WebDriver driver) {
        super(driver);
    
    }

     public void clickOnReports() {
        wait.untilElementClickable(LocatorsType.ByXpath, Reports).click();
    }

    public WebElement getSubMenu(String menuName) {
        return wait.untilElementPresent(LocatorsType.ByXpath, LeftTabMenuLocators.reportsSubMenuLinkXpath(menuName));
    }

    public WebElement getNoDataAvailableMessage() {
        wait.untilElementHasText(LocatorsType.ByXpath, NoDataAvailable);
        return wait.untilElementPresent(LocatorsType.ByXpath, NoDataAvailable);
    }

    public Boolean isDownloadButtonDisplayed() {
        try{
        return wait.untilElementPresent(LocatorsType.ByID, DownloadButton).isDisplayed();
        }catch(Exception e)
        {
            return false;
        }
    }
    
}
