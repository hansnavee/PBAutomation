package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ScenarioPage extends Pages {

    private final String CreateNewScenarioButton = ScenarioLocators.CreateNewScenarioButtonByID;
    private final String SearchField = ScenarioLocators.SearchScenarioFieldByXpath;
    private final String SearchButton = ScenarioLocators.SearchScenarioButtonByXpath;
    private final String ResetButton = ScenarioLocators.ResetScenarioButtonByXpath;
    private final String ActionColumnThreeDots = ScenarioLocators.ActionsThreeDotsByID;
    private final String ExportOption = ScenarioLocators.ExportOptionByXpath;
    private final String ImportOption = ScenarioLocators.ImportOptionByXpath;
    private final String TreatmentPoolLibraryOption = ScenarioLocators.TreatmentPoolLibraryOptionByXpath;
    private final String SettingsOption = ScenarioLocators.SettingsOptionByXpath;
   private final String AnalysisOption = ScenarioLocators.AnalysisOptionByXpath;
   
    private final String RunOption = ScenarioLocators.RunOptionByXpath;
    private final String TableRows = ScenarioLocators.ScenarioTableRows;
    private final String NoDataAvailableInTable = ScenarioLocators.NoDataAvailableInTableByXpath;
    private final String TableRowsData = ScenarioLocators.ScenarioTableRowsData;
    private final String ScenarioCreatedSuccessFullyToast = ScenarioLocators.ScenarioCreatedSuccessFullyToastMessageByXpath;


    public ScenarioPage(WebDriver driver) {
        super(driver);
    }

    // ---------------------- Screenshot Helper ----------------------

    private void attachScreenshot(String name) {
        try {
            Allure.addAttachment(
                    name + " - Screenshot",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))
            );
        } catch (Exception ignored) {}
    }

    // ---------------------- Page Actions with Allure Logs ----------------------

    @Step("Clicking on 'Create New Scenario' button")
    public void clickCreateNewScenarioButton() {
        Allure.step("Attempting to click 'Create New Scenario' button");

        try {
            clickElement(LocatorsType.ByID, CreateNewScenarioButton);
            AllureLogger.passStep("Clicked Create New Scenario button successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to click Create New Scenario button", e);
            attachScreenshot("Create Scenario Click Failure");
            throw e;
        }
    }

    @Step("Searching scenario name: {scenarioName}")
    public void searchScenarioNameField(String scenarioName) {
        Allure.step("Entering scenario name in search field: " + scenarioName);

        try {
            WebElement searchBox = wait.untilElementVisible(LocatorsType.ByXpath, SearchField);
            searchBox.clear();
            searchBox.sendKeys(scenarioName);
            AllureLogger.passStep("Entered scenario name successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to enter scenario name", e);
            attachScreenshot("Search Field Failure");
            throw e;
        }
    }

    @Step("Clicking 'Search' button")
    public void clickSearchButton() {
        Allure.step("Attempting to click Search button");

        try {
            WebElement element = wait.untilElementClickable(LocatorsType.ByXpath, SearchButton);
            element.click();
            wait.waitForAllLoaderContainersToDisappear(60);
            AllureLogger.passStep("Clicked Search button successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to click Search button", e);
            attachScreenshot("Search Button Failure");
            throw e;
        }
    }

    @Step("Clicking 'Reset' button")
    public void clickResetButton() {
        Allure.step("Attempting to click Reset button");

        try {
            clickElement(LocatorsType.ByXpath, ResetButton);
            AllureLogger.passStep("Clicked Reset button successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to click Reset button", e);
            attachScreenshot("Reset Button Failure");
            throw e;
        }
    }

    @Step("Fetching scenario table rows")
    public List<WebElement> getScenariosTableRows() {
        Allure.step("Retrieving scenario rows from table");

        try {
            List<WebElement> rows = findElements(LocatorsType.ByXpath, TableRows);
            AllureLogger.passStep("Fetched " + rows.size() + " scenario rows successfully");
            return rows;
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch table rows", e);
            attachScreenshot("Table Rows Failure");
            throw e;
        }
    }

    @Step("Fetching message No Data Available In Table")
    public WebElement getMessageNoDataAvailableInTable() {
        Allure.step("Retrieving no data available message from table");

        try {
            WebElement noDataAvailable = findElement(LocatorsType.ByXpath, NoDataAvailableInTable);
            AllureLogger.passStep("Fetched " + "No Data Available In Table");
            return noDataAvailable;
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch No Data Available In Table", e);
            attachScreenshot("Table Rows Failure");
            throw e;
        }
    }

    @Step("Click Action menu (...) three dots")
    public void clickActionColumnThreeDots() {
        Allure.step("Fetching action three dots");

        try {
            WebElement element = wait.untilElementClickable(LocatorsType.ByID, ActionColumnThreeDots);
            element.click();
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get action three dots", e);
            //captureScreenshot("Action Three Dots Failure");
            throw e;
        }
    }

    @Step("Click Export option from Action menu")
    public WebElement getExportOption() {
        Allure.step("Fetching Export option");

        try {
            return findElement(LocatorsType.ByXpath, ExportOption);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Export option", e);
            //captureScreenshot("Edit Option Failure");
            throw e;
        }
    }

    @Step("Click Import option from Action menu")
    public WebElement getImportOption() {
        Allure.step("Fetching Import option");

        try {
            return findElement(LocatorsType.ByXpath, ImportOption);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Import option", e);
            //captureScreenshot("Edit Option Failure");
            throw e;
        }
    }

    @Step("Click Import option from Action menu")
    public WebElement getTreatmentPoolLibraryOption() {
        Allure.step("Fetching Treatment Pool Library option");

        try {
            return findElement(LocatorsType.ByXpath, TreatmentPoolLibraryOption);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Import option", e);
            //captureScreenshot("Edit Option Failure");
            throw e;
        }
    }

     @Step("Click Settings Option from Action menu")
    public WebElement getSettingsOption() {
        Allure.step("Fetching Settings option");

        try {
            return findElement(LocatorsType.ByXpath, SettingsOption);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Settings option", e);
            throw e;
        }
    }

     @Step("Click Analysis Option from Action menu")
    public WebElement getAnalysisOption() {
        Allure.step("Fetching Analysis option");

        try {
            return wait.untilElementPresent(LocatorsType.ByXpath, AnalysisOption);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Analysis option", e);
            throw e;
        }
    }

    @Step("Click Run option from Action menu")
    public WebElement getRunOption() {
        Allure.step("Fetching Run option");

        try {
            return wait.untilElementClickable(LocatorsType.ByXpath, RunOption);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Import option", e);
            //captureScreenshot("Edit Option Failure");
            throw e;
        }
    }

    public List<String> getValuesOfScenario() {
        return Allure.step("Get values of all scenarios from table", () -> {
            List<WebElement> elements = wait.untilAllElementsVisible(LocatorsType.ByXpath, TableRowsData);
            List<String> tableData = new ArrayList<>();
            for (WebElement element : elements) {
                tableData.add(element.getText().trim());
            }
            return tableData;
        });
    }

    public boolean waitUntilTableContains(String expectedValue) {
        return wait.waitUntilCondition(driver ->
                        driver.findElements(By.xpath(TableRowsData))
                                .stream()
                                .map(element -> element.getText().trim())
                                .anyMatch(text -> text.contains(expectedValue)),
                Duration.ofSeconds(30),
                Duration.ofMillis(500));
    }

    public boolean waitUntilStatusAppears(String expectedStatus) {

     Allure.step("Wait until status changes to " + expectedStatus);

    return wait.waitUntilCondition(driver ->
            driver.findElements(By.xpath(TableRowsData))
                    .stream()
                    .map(e -> e.getText().trim())
                    .anyMatch(text -> text.equalsIgnoreCase(expectedStatus)),
            Duration.ofMinutes(5),
            Duration.ofSeconds(5));
}


    public boolean isSuccessfulMessageVisible(String scenarioName) {
        return Allure.step("Check if success message for scenario '" + scenarioName + "' is visible", () -> {
            WebElement toastLocator = wait.untilElementVisible(LocatorsType.ByXpath, ScenarioLocators.ScenarioRunSuccessFullyByXpath(scenarioName));
            return waitForToastMessage(toastLocator.getText(), 200);
        });
    }

    public boolean isSuccessfulMessageDisappear(String scenarioName) {
        return Allure.step("Check if success message for scenario '" + scenarioName + "' disappeared", () -> {
            By toastLocator = By.xpath(ScenarioLocators.ScenarioRunSuccessFullyByXpath(scenarioName));
            return waitForToastDisappear(toastLocator);
        });
    }

    public WebElement getElementVisible() {
        return Allure.step("Get 'Create New Scenario' button element", () ->
                wait.untilElementPresent(LocatorsType.ByID, CreateNewScenarioButton)
        );
    }

    public boolean isOptionDisplayed(String optionName) {
        return Allure.step("Check if action option '" + optionName + "' is displayed", () -> {
            WebElement element = findElement(LocatorsType.ByXpath, ScenarioLocators.actionOptions(optionName));
            return element.isDisplayed();
        });
    }

    public WebElement getOption(String optionName) {
        return Allure.step("Get action option element: '" + optionName + "'", () ->
                findElement(LocatorsType.ByXpath, ScenarioLocators.actionOptions(optionName))
        );
    }

    public boolean isSuccessToastMessage(String expectedMessage) {
        return Allure.step("Check if success toast message is visible", () ->
                waitForToastMessage(expectedMessage, 60)
        );
    }

    public boolean isSuccessToastMessageDisappear() {
        return Allure.step("Check if success toast message disappeared", () ->
                waitForToastDisappear(By.xpath(ScenarioCreatedSuccessFullyToast))
        );
    }

    public WebElement getSearchBox() {
        return Allure.step("Get search box element", () ->
                wait.untilElementVisible(LocatorsType.ByXpath, SearchField)
        );
    }
    
}