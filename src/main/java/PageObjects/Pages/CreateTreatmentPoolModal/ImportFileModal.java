package PageObjects.Pages.CreateTreatmentPoolModal;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;
import Utils.FileUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class ImportFileModal extends Pages {
    private final String treatmentTypeDropdown = ImportModalLocators.TreatmentTypeDropdownByName;
    private final String ImportButton = ImportModalLocators.ImportButtonByXpath;
    private final String CancelButton = ImportModalLocators.CancelButtonByXpath;
    private final String TabName = ImportModalLocators.TabNameByName;

    public ImportFileModal(WebDriver driver) {
        super(driver);
    }

    public void selectFileType(String type) {
        WebElement element = wait.untilElementClickable(LocatorsType.ByName, treatmentTypeDropdown);
        Select select = new Select(element);
        select.selectByVisibleText(type);
    }

    public void uploadFile(String fileName) {
        String filePath = FileUtility.getResourceFilePath(fileName);
        WebElement element = findElement(LocatorsType.ByID, "excelfile");
        element.sendKeys(filePath);
        clickImportButton();
    }

    public void uploadFile(String fileName, String tabName) {
        String filePath = FileUtility.getResourceFilePath(fileName);
        WebElement element = findElement(LocatorsType.ByID, "excelfile");
        element.sendKeys(filePath);
        setTabName(tabName);
        clickImportButton();
    }

    public void clickImportButton() {
        WebElement element = wait.untilElementClickable(LocatorsType.ByXpath, ImportButton);
        clickElement(element);
    }

    public void setTabName(String tabName) {
        setText(By.name(TabName), tabName);
    }

    public void clickCancelButton(){
        clickElement(LocatorsType.ByXpath, CancelButton);
    }

}
