package PageObjects.Pages.CreateTreatmentPoolModal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;
import io.cucumber.java.be.I;
import io.cucumber.java.it.Ma;
import io.qameta.allure.Allure;

public class CreateTreatmentModal extends Pages{
    private final String CreateTreatmentLabel = CreateTreatmentLocators.CreateTreatmentModalLabelByID;
    private final String CancelButton = CreateTreatmentLocators.CancelButtonByXpath;
        private final String CloseIcon = CreateTreatmentLocators.CloseIconByXpath;
    private final String AssetTypeSelect = CreateTreatmentLocators.AssetTypeSelectByCssSelector;
    private final String DistrictSelect = CreateTreatmentLocators.DistrictSelectByCssSelector;
    private final String IndirectCostDesign = CreateTreatmentLocators.IndirectCostDesignByCssSelector;
    private final String ndirectCostOthers = CreateTreatmentLocators.IndirectCostOthersByCssSelector;
    private final String IndirectCostRow = CreateTreatmentLocators.IndirectCostRowByCssSelector;
    private final String IndirectCostUtilities = CreateTreatmentLocators.IndirectCostUtilitiesByCssSelector;
    private final String ConstructionCost = CreateTreatmentLocators.ConstructionCostByCssSelector;
    private final String CountySelect = CreateTreatmentLocators.CountySelectByCssSelector;
    private final String MaxYear = CreateTreatmentLocators.MaxYearByCssSelector;
    private final String PreferredYearSelect = CreateTreatmentLocators.PreferredYearSelectByCssSelector;



    public CreateTreatmentModal(WebDriver driver) {
        super(driver);
    }

    public WebElement getCreateTreatmentLabel() {
        Allure.step("Waiting for Create Treatment label to be visible");
        WebElement element = wait.untilElementVisible(LocatorsType.ByID, CreateTreatmentLabel);
        Allure.step("Create Treatment label is visible: " + element.isDisplayed());
        return element;
    }

    public WebElement getCancelButton() {
        Allure.step("Waiting for Cancel button to be visible");
        WebElement element = wait.untilElementVisible(LocatorsType.ByXpath, CancelButton);
        Allure.step("Cancel button is visible: " + element.isDisplayed());
        return element;
    }

    public WebElement getCloseIcon() {
        Allure.step("Waiting for Close icon to be visible");
        WebElement element = wait.untilElementVisible(LocatorsType.ByXpath, CloseIcon);
        Allure.step("Close icon is visible: " + element.isDisplayed());
        return element;
    }


     public boolean isCreateTreatmentLabelVisible() {
        try{
        Allure.step("Waiting for Create Treatment label to be visible");
        boolean elementVisible = wait.untilElementVisible(LocatorsType.ByID, CreateTreatmentLabel).isDisplayed();
        Allure.step("Create Treatment label is visible: " + elementVisible);
        return elementVisible;
        
        } catch (Exception e) {
            Allure.step("Create Treatment label is not visible");
            return false;
        }
    }

    public void selectAssetType(String AssetTypeSelect) {
        WebElement element = wait.untilElementPresent(LocatorsType.ByCss, AssetTypeSelect);
        Select select = new Select(element);
        select.selectByValue(AssetTypeSelect);
        
    }

    public void selectDistrict(String DistrictSelect) {
        WebElement element = wait.untilElementPresent(LocatorsType.ByCss, DistrictSelect);
        Select select = new Select(element);
        select.selectByValue(DistrictSelect);
    }

    public void setIndirectCostDesign(String IndirectCostDesign) {
        wait.untilElementPresent(LocatorsType.ByCss, IndirectCostDesign)
        .sendKeys(IndirectCostDesign);
    }

    public void setIndirectCostOthers(String IndirectCostOthers) {
        wait.untilElementPresent(LocatorsType.ByCss, IndirectCostOthers).sendKeys(IndirectCostOthers);
    }

    public void setIndirectCostRow(String indirectCostRow) {
         wait.untilElementPresent(LocatorsType.ByCss, IndirectCostRow).sendKeys(IndirectCostRow);;
    }

    public void setIndirectCostUtilities(String IndirectCostUtilities) {
         wait.untilElementPresent(LocatorsType.ByCss, IndirectCostUtilities).sendKeys(IndirectCostUtilities);
    }

    public void setConstructionCost(String ConstructionCostValue) {
         wait.untilElementPresent(LocatorsType.ByCss, ConstructionCost).sendKeys(ConstructionCostValue);
    }

    public void selectCountySelect(String county) {
        WebElement element = wait.untilElementPresent(LocatorsType.ByCss, CountySelect);
        Select select =new Select(element);
        select.selectByValue(county);
    }

    public void setMaxYear(String MaxYear) {
        wait.untilElementPresent(LocatorsType.ByCss, MaxYear).sendKeys(MaxYear);
    }

    public void setPreferredYearSelect(String preferredYear) {
        wait.untilElementPresent(LocatorsType.ByCss, PreferredYearSelect).sendKeys(preferredYear);
    }

    public void fillCreateTreatmentForm(String assetType, String district, String indirectCostDesign, String indirectCostOthers, String indirectCostRow, String indirectCostUtilities, String constructionCost, String county, String maxYear, String preferredYear) {
        Allure.step("Filling Create Treatment form with provided data");
        selectAssetType(assetType);
        selectDistrict(district);
        setIndirectCostDesign(indirectCostDesign);
        setIndirectCostOthers(indirectCostOthers);
        setIndirectCostRow(indirectCostRow);
        setIndirectCostUtilities(indirectCostUtilities);
        setConstructionCost(constructionCost);
        selectCountySelect(county);
        setMaxYear(maxYear);
        setPreferredYearSelect(preferredYear);
    }

}
