package PageObjects.Pages.Analysis.Projects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;

public class ProjectsPage extends Pages  {

    private final String PageHeading = ProjectLocators.ProjectPageHeadingByXpath;
    private final String NoRecordsFoundMessage = ProjectLocators.NoRecordsFoundMessageByXpath;
    private final String ExportToExcel = ProjectLocators.ExportToExcelButtonByXpath;
    private final String ExportToJson = ProjectLocators.DownloadJsonByXpath;
    private final String ProjectTreatmentsButton = ProjectLocators.ProjectTreatmentsButtonByXpath;


    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageHeading() {
        return wait.untilElementPresent(LocatorsType.ByXpath, PageHeading);
    }

   public long getProjectsTableRowCount() {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    Long result = (Long) js.executeScript(
        "return $('#projectsdataTable').DataTable().rows().count();"
    );
    return result != null ? result : 0L;
}



    public WebElement getExportToExcelButton() {
        return wait.untilElementPresent(LocatorsType.ByXpath, ExportToExcel);
    }

    public WebElement getExportToJsonButton() {
        return wait.untilElementPresent(LocatorsType.ByXpath, ExportToJson);
    }

    public WebElement getProjectTreatmentsButton() {
        return wait.untilElementPresent(LocatorsType.ByXpath, ProjectTreatmentsButton);
    }
    
}
