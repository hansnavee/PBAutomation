package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.Pages.CreateScenarioTabs.LeftSideBarMenu;
import PageObjects.Pages.CreateScenarioTabs.TreatmentPoolLibraryTab;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PageObjects.Pages.CreateTreatmentPoolModal.CreateTreatmentPoolLibraryModal;
import org.openqa.selenium.WebElement;

import java.time.Year;
import java.util.Arrays;

import static java.time.Year.now;

public class CreateScenariosPage extends Pages {

    private final String ScenarioNameField = CreateScenarioLocators.ScenarioNameFieldByID;
    private final String FirstYearField = CreateScenarioLocators.FirstYearFieldByID;
    private final String LastYearField = CreateScenarioLocators.LastYearFieldByID;
    private final String CreateScenarioButton = CreateScenarioLocators.CreateScenarioButtonByXpath;
    LeftSideBarMenu leftSideBarMenu;
    TreatmentPoolLibraryTab treatmentPoolLibraryTab;
    CreateTreatmentPoolLibraryModal createTreatmentPoolLibraryModal;
    public CreateScenariosPage(WebDriver driver) {
        super(driver);
    }

    // ---------------------------------------------
    // Lazy Getters
    // ---------------------------------------------
    public LeftSideBarMenu leftMenu() {
        if (leftSideBarMenu == null) {
            leftSideBarMenu = new LeftSideBarMenu(driver);
        }
        return leftSideBarMenu;
    }

    public TreatmentPoolLibraryTab treatmentPoolTab() {
        if (treatmentPoolLibraryTab == null) {
            treatmentPoolLibraryTab = new TreatmentPoolLibraryTab(driver);
        }
        return treatmentPoolLibraryTab;
    }

    public CreateTreatmentPoolLibraryModal treatmentPoolModal() {
        if (createTreatmentPoolLibraryModal == null) {
            createTreatmentPoolLibraryModal = new CreateTreatmentPoolLibraryModal(driver);
        }
        return createTreatmentPoolLibraryModal;
    }

    // ---------------------------------------------
    // Main Action
    // ---------------------------------------------
    public void createNewScenario(String scenarioName, String treatmentName) {

        wait.untilElementVisible(LocatorsType.ByID, ScenarioNameField)
                .sendKeys(scenarioName);

        setText(By.id(FirstYearField), String.valueOf(now().getValue()));
        setText(By.id(LastYearField), String.valueOf(Year.now().getValue()+10));


        safeClick(By.xpath(
                CreateScenarioMenuLocators.LinkXpath("Treatment Pool Library")
        ));

        clickElement(treatmentPoolTab().getCreateNewTreatmentPoolLibrary());

        treatmentPoolModal().createTreatmentPool(treatmentName);
        leftMenu().waitForDomToStabilize();
        
        treatmentPoolTab().selectTreatmentPoolLibrary(
                "Treatment Pool Library Name",
                treatmentName
        );
        leftMenu().waitForDomToStabilize();
        clickCreateScenarioButton();
        leftMenu().waitForDomToStabilize();
    }

    public void clickCreateScenarioButton(){
        WebElement element = wait.untilElementClickable(LocatorsType.ByXpath, CreateScenarioButton);
        clickElement(element);
    }

}