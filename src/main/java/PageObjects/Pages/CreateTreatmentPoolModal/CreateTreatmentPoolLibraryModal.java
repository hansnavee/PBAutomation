package PageObjects.Pages.CreateTreatmentPoolModal;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateTreatmentPoolLibraryModal extends Pages {

    private final String TreatmentPoolLibraryName = TreatmentPoolModalLocators.TreatmentLibraryNameFieldByName;
    private final String TreatmentPoolLibraryEditFormName = TreatmentPoolModalLocators.TreatmentLibraryEditFormNameFieldByXpath;
    private final String TreatmentPoolLibraryDescription = TreatmentPoolModalLocators.TreatmentLibraryDescriptionFieldByName;
    private final String TreatmentPoolLibraryEditFormDescription = TreatmentPoolModalLocators.TreatmentLibraryEditFormDescriptionFieldByXpath;
    private final String CreateButton = TreatmentPoolModalLocators.CreateButtonByXpath;
    private final String CancelButton = TreatmentPoolModalLocators.CancelButtonByXpath;
    private final String CancelButtonEditForm = TreatmentPoolModalLocators.CancelButtonFromEditFormByXpath;
    private final String UpdateButton = TreatmentPoolModalLocators.UpdateByXpath;
    private final String CloseIcon = TreatmentPoolModalLocators.CloseIconByXpath;
    private final String CloseIconEditForm = TreatmentPoolModalLocators.CloseIconEditFormByXpath;
    private final String DescriptionFieldValidationMessage = TreatmentPoolModalLocators.DescriptionFieldValidationMessageByID;
    private final String NameFieldValidationMessage = TreatmentPoolModalLocators.NameFieldValidationMessageByID;
    private final String SharedToggleButton = TreatmentPoolModalLocators.SharedToggleButtonByXpath;
    private final String SharedToggleLabel = TreatmentPoolModalLocators.SharedLabelByXpath;
    private final String CreateNewLibraryFormLabel = TreatmentPoolModalLocators.CreateNewLibraryFormLabelByID;
    private final String EditTreatmentPoolLibraryFormLabel = TreatmentPoolModalLocators.EditTreatmentPoolLibraryFormLabelByXpath;

    public CreateTreatmentPoolLibraryModal(WebDriver driver){
        super(driver);
    }

    public void createTreatmentPool(String randomTreatmentPoolName)  {
        wait.untilElementClickable(LocatorsType.ByName, TreatmentPoolLibraryName);
        setText(By.name(TreatmentPoolLibraryName), randomTreatmentPoolName);
        wait.untilElementVisible(LocatorsType.ByName, TreatmentPoolLibraryDescription).sendKeys(randomTreatmentPoolName);
        safeClick(By.xpath(CreateButton));
        //wait.untilElementDisappear(LocatorsType.ByXpath, ScenarioTreatmentPoolTabLocators.treatmentPoolCreatedSuccessMessage);

    }

    public void createTreatmentPoolWithoutDescription(String randomTreatmentPoolName) {
        setText(By.name(TreatmentPoolLibraryName), randomTreatmentPoolName);
        safeClick(By.xpath(CreateButton));
    }

    public void createTreatmentPoolWithoutName(String randomTreatmentPoolName) {
        wait.untilElementVisible(LocatorsType.ByName, TreatmentPoolLibraryDescription).sendKeys(randomTreatmentPoolName);
        safeClick(By.xpath(CreateButton));
    }

    public void clickCreateButton() {
        wait.untilElementVisible(LocatorsType.ByXpath,CreateButton);
        safeClick(By.xpath(CreateButton));
    }

    public void setTreatmentPoolAllRequiredFields(String randomTreatmentPoolName) {
        wait.untilElementVisible(LocatorsType.ByName, TreatmentPoolLibraryName).sendKeys(randomTreatmentPoolName);
        wait.untilElementVisible(LocatorsType.ByName, TreatmentPoolLibraryDescription).sendKeys(randomTreatmentPoolName);
    }

    public void clickCancelButton() {
        WebElement element = wait.untilElementClickable(LocatorsType.ByXpath, CancelButton);
        clickElement(element);
    }

    public String getNameFieldValidationMessage(){
        return getText(By.id(NameFieldValidationMessage));
    }

    public WebElement getSharedToggleLabel(){
        return findElement(LocatorsType.ByXpath, SharedToggleLabel);
    }

    public WebElement getEditTreatmentPoolLibraryFormLabel(){
        return wait.untilElementClickable(LocatorsType.ByXpath, EditTreatmentPoolLibraryFormLabel);
    }

    public WebElement getSharedToggleButton(){
        return findElement(LocatorsType.ByXpath, SharedToggleButton);
    }

    public String getDescriptionFieldValidationMessage(){
        return getText(By.id(DescriptionFieldValidationMessage));
    }

    public WebElement getNameField(){

        return wait.untilElementVisible(LocatorsType.ByName, TreatmentPoolLibraryName);
    }


    public WebElement getDescriptionField(){
        return findElement(LocatorsType.ByName, TreatmentPoolLibraryDescription);
    }

    public WebElement getNameFieldFromEditForm(){

        return wait.untilElementVisible(LocatorsType.ByXpath, TreatmentPoolLibraryEditFormName);
    }


    public WebElement getDescriptionFieldFromEditForm(){
        return findElement(LocatorsType.ByXpath, TreatmentPoolLibraryEditFormDescription);
    }

    public WebElement getCancelButton(){
        return findElement(LocatorsType.ByXpath, CancelButton);
    }

    public WebElement getCancelButtonFromEditForm(){
        return findElement(LocatorsType.ByXpath, CancelButtonEditForm);
    }

    public WebElement getCloseIcon(){
        return findElement(LocatorsType.ByXpath, CloseIcon);
    }

    public WebElement getCloseIconEditForm(){
        return findElement(LocatorsType.ByXpath, CloseIconEditForm);
    }

    public WebElement getCreateButton(){
        return findElement(LocatorsType.ByXpath, CreateButton);
    }

    public void clickUpdateButton(){
        safeClick(By.xpath(UpdateButton));
    }

    public WebElement getUpdateButton(){
        return findElement(LocatorsType.ByXpath, UpdateButton);
    }

    public boolean isCreateNewLibraryFormLabelDisplayed(){
        try {
            WebElement element = findElement(LocatorsType.ByID, CreateNewLibraryFormLabel);
            if(element.isDisplayed())
            {
                return true;
            }
        }catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}
