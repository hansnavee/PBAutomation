package PageObjects.Pages.CreateScenarioTabs;
import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import Utils.FileUtility;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BudgetConstraints extends Pages {
    private final String BudgetConstraintDefaultMessage = BudgetConstraintsLocators.BudgetConstraintDefaultMessageByXpath;
    private final String BudgetConstraintSuccessMessage = BudgetConstraintsLocators.BudgetConstraintSuccessMessageByXpath;
    private final String DownloadButton = BudgetConstraintsLocators.DownloadButtonByXpath;
    private final String ImportBudgetButton = BudgetConstraintsLocators.ImportBudgetButtonByXpath;
    private final String ImportButton = BudgetConstraintsLocators.ImportButtonByXpath;
    private final String ImportBudgetConstraintsNote = BudgetConstraintsLocators.ImportBudgetConstraintNoteByXpath;

    public BudgetConstraints(WebDriver driver){
        super(driver);
    }

    @Step("Get Budget Constraints Message")
    public WebElement getBudgetConstraintsDefaultMessage() {
        Allure.step("Fetching default message element");

        try {
            return findElement(LocatorsType.ByXpath, BudgetConstraintDefaultMessage);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get default message element", e);
            throw e;
        }
    }

     @Step("Is Budget Constraints Message Displaye")
    public WebElement getBudgetConstraintsSuccessMessage() {
        Allure.step("Fetching success message element");

        try {
            return wait.untilElementPresent(LocatorsType.ByXpath, BudgetConstraintSuccessMessage);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get default message element", e);
            throw e;
        }
    }

    public WebElement getDownloadButton() {
        Allure.step("Fetching Download Button element");

        try {
            return findElement(LocatorsType.ByXpath, DownloadButton);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get Download Button element", e);
            throw e;
        }
    }

     public WebElement getImportBudgetButton() {
        Allure.step("Fetching Import Budget Button element");

        try {
            return wait.untilElementClickable(LocatorsType.ByXpath, ImportBudgetButton);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get Import Button element", e);
            throw e;
        }
    }

    public void clickImportButton() {
        Allure.step("Fetching Import Button element");

        try {
            wait.untilElementClickable(LocatorsType.ByXpath, ImportButton).click();
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get Import Button element", e);
            throw e;
        }
    }

    @Step("Upload Budget Constraints File")
    public void uploadBudgetConstraintsFile(String fileName) {
        Allure.step("Uploading budget constraints file: " + fileName);

        try {
            String filePath = FileUtility.getResourceFilePath(fileName);
            WebElement uploadElement = findElement(LocatorsType.ByName, BudgetConstraintsLocators.UploadBudgetConstraintsFileByName);
            uploadElement.sendKeys(filePath);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to upload budget constraints file", e);
            throw e;
        }
    }

    public WebElement getUploadBudgetConstraintsNote(){
        Allure.step("Fetching Import Budget Constraints Note element");

        try {
            WebElement element = findElement(LocatorsType.ByXpath, ImportBudgetConstraintsNote);
            wait.untilElementHasText(LocatorsType.ByXpath, ImportBudgetConstraintsNote);
            return element;
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get Import Budget Constraints Note element", e);
            throw e;
        }
    }

    public boolean isUploadBudgetConstraintsNoteDisplayed(){
        Allure.step("Fetching Import Budget Constraints Note element");

        try {
            return findElement(LocatorsType.ByXpath, ImportBudgetConstraintsNote).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
