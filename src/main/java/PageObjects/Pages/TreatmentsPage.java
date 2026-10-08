package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.LocatorsType;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TreatmentsPage extends Pages {
    private final String TreatmentTableNoRecordFoundMessage = TreatmentLocators.TableNoRecordFoundMessageByXpath;
    private final String TreatmentTableAllData = TreatmentLocators.TableAllDataByXpath;
    private final String CreateTreatmentButton = TreatmentLocators.CreateTreatmentButtonByID;

    public TreatmentsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        String title = driver.getTitle();
        Allure.step("Retrieved page title: " + title);
        return title;
    }

    public WebElement getNoRecordFoundMessageDisplayed() {
        Allure.step("Waiting for 'No Record Found' message to be visible");
        WebElement element = wait.untilElementVisible(LocatorsType.ByXpath, TreatmentTableNoRecordFoundMessage);
        Allure.step("'No Record Found' message is visible: " + element.isDisplayed());
        return element;
    }

    public List<WebElement> getTableData() {
        Allure.step("Waiting for all table data elements to be visible");
        List<WebElement> elements = wait.untilAllElementsVisible(LocatorsType.ByXpath, TreatmentTableAllData);
        Allure.step("Number of rows retrieved from table: " + elements.size());
        return elements;
    }

    public WebElement getCreateTreatmentButton() {
        Allure.step("Waiting for Create Treatment button to be visible");
        WebElement element = wait.untilElementVisible(LocatorsType.ByXpath, CreateTreatmentButton);
        Allure.step("Create Treatment button is visible: " + element.isDisplayed());
        return element;
    }
}
