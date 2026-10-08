package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreatmentPoolLibrariesPage extends Pages {

    private final String CreateNewTreatmentPoolLibraryButton = TreatmentPoolLocators.CreateNewTreatmentPoolLibraryButtonByID;
    private final String SearchField = TreatmentPoolLocators.SearchFieldByXpath;
    private final String SearchButton = TreatmentPoolLocators.SearchButtonByXpath;
    private final String ResetButton = TreatmentPoolLocators.ResetButtonByXpath;
    private final String TableRows = TreatmentPoolLocators.TableRows;
    private final String HeadingTextNoDataAvailable = TreatmentPoolLocators.HeadingTextNoDataAvailableByXpath;
    private final String ActionColumnThreeDots = TreatmentPoolLocators.ActionsThreeDotsByID;
    private final String EditOption = TreatmentPoolLocators.EditOptionByXpath;
    private final String CreateNewLibraryForm = TreatmentPoolLocators.CreateNewLibraryFormByXpath;
    private final String DeleteOption = TreatmentPoolLocators.DeleteOptionByXpath;
    private final String CopyOption = TreatmentPoolLocators.CopyOptionByXpath;
    private final String ImportOption = TreatmentPoolLocators.ImportOptionByXpath;
    private final String TreatmentsOption = TreatmentPoolLocators.TreatmentOptionByXpath;
    private final String TableRowsData = TreatmentPoolLocators.TreatmentTableRowsData;

    public TreatmentPoolLibrariesPage(WebDriver driver) {
        super(driver);
    }

    // ---------------------- Screenshot Helper ----------------------

    private void captureScreenshot(String name) {
        try {
            Allure.addAttachment(
                    name,
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))
            );
        } catch (Exception ignored) {}
    }

    // ---------------------- Page Actions with Allure Logs ----------------------

    @Step("Click 'Create New Treatment Pool Library' button")
    public void clickCreateNewTreatmentPoolButton() {
        Allure.step("Attempting to click Create New Treatment Pool Library button");

        try {
            clickElement(LocatorsType.ByID, CreateNewTreatmentPoolLibraryButton);
            AllureLogger.passStep("Clicked Create New Treatment Pool Library button successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to click Create New Treatment Pool Library button", e);
            captureScreenshot("Create New Treatment Pool Click Failure");
            throw e;
        }
    }

    @Step("Enter search text in Treatment Pool search field: {name}")
    public void searchNameField(String name) {
        Allure.step("Entering Treatment Pool Library name into search field");

        try {
            WebElement searchInput = wait.untilElementVisible(LocatorsType.ByXpath, SearchField);
            searchInput.clear();
            searchInput.sendKeys(name);
            AllureLogger.passStep("Entered Treatment Pool name successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to enter Treatment Pool name", e);
            captureScreenshot("Search Field Failure");
            throw e;
        }
    }

    @Step("Click 'Search' button")
    public void clickSearchButton() {
        Allure.step("Attempting to click Search button");

        try {
            WebElement element =wait.untilElementClickable(LocatorsType.ByXpath, SearchButton);
            element.click();
            wait.waitForAllLoaderContainersToDisappear(60);
            AllureLogger.passStep("Clicked Search button successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to click Search button", e);
            captureScreenshot("Search Button Click Failure");
            throw e;
        }
    }

    @Step("Click 'Reset' button")
    public void clickResetButton() {
        Allure.step("Attempting to click Reset button");

        try {
            clickElement(LocatorsType.ByXpath, ResetButton);
            AllureLogger.passStep("Clicked Reset button successfully");
        } catch (Exception e) {
            AllureLogger.failStep("Failed to click Reset button", e);
            captureScreenshot("Reset Button Click Failure");
            throw e;
        }
    }

    @Step("Get all Treatment Pool table rows")
    public List<WebElement> getTableRows() {
        Allure.step("Fetching Treatment Pool table rows");

        try {
            List<WebElement> rows = wait.untilAllElementsVisible(LocatorsType.ByXpath, TableRows);
            AllureLogger.passStep("Fetched " + rows.size() + " table rows");
            return rows;
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Treatment Pool table rows", e);
            captureScreenshot("Table Rows Fetch Failure");
            throw e;
        }
    }

    @Step("Get 'No Data Available' heading text")
    public WebElement getHeadingTextNoDataAvailable() {
        Allure.step("Fetching 'No Data Available' heading");

        try {
            return findElement(LocatorsType.ByXpath, HeadingTextNoDataAvailable);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch No Data Available heading", e);
            captureScreenshot("No Data Heading Failure");
            throw e;
        }
    }

    @Step("Click Action menu (...) three dots")
    public WebElement getActionColumnThreeDots() {
        Allure.step("Fetching action three dots");

        try {
            return wait.untilElementVisible(LocatorsType.ByID, ActionColumnThreeDots);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to get action three dots", e);
            captureScreenshot("Action Three Dots Failure");
            throw e;
        }
    }

    @Step("Click Edit option from Action menu")
    public WebElement getEditOption() {
        Allure.step("Fetching Edit option");

        try {
            return findElement(LocatorsType.ByXpath, EditOption);
        } catch (Exception e) {
            AllureLogger.failStep("Failed to fetch Edit option", e);
            captureScreenshot("Edit Option Failure");
            throw e;
        }
    }

    public WebElement getCreateNewLibraryForm() {
        return Allure.step("Get 'Create New Library' form element", () ->
                wait.untilElementVisible(LocatorsType.ByXpath, CreateNewLibraryForm)
        );
    }

    public boolean waitForAttributeValueToBeUpdated(String newValue) {
       return Allure.step("Wait for 'Create New Library' form style attribute to be updated to: " + newValue, () ->
                waitForStyleToBe(By.xpath(CreateNewLibraryForm), newValue)
        );
    }

    public boolean isOptionDisplayed(String optionName) {
        return Allure.step("Check if action option '" + optionName + "' is displayed", () -> {
            WebElement element = wait.untilElementVisible(
                    LocatorsType.ByXpath, TreatmentPoolLocators.actionOptions(optionName)
            );
            return element.isDisplayed();
        });
    }

    public boolean isDeleteOptionDisabled() {
        return Allure.step("Check if 'Delete' option is disabled", () -> {
            WebElement element = wait.untilElementVisible(LocatorsType.ByXpath, DeleteOption);
            return Objects.requireNonNull(element.getAttribute("class")).contains("disabled");
        });
    }

    public WebElement getCopyOption() {
        return Allure.step("Get 'Copy' option element", () ->
                wait.untilElementVisible(LocatorsType.ByXpath, CopyOption)
        );
    }

    public WebElement getImportOption() {
        return Allure.step("Get 'Import' option element", () ->
                wait.untilElementVisible(LocatorsType.ByXpath, ImportOption)
        );
    }

    public WebElement getTreatmentsOption() {
        return Allure.step("Get 'Treatments' option element", () ->
                wait.untilElementPresent(LocatorsType.ByXpath, TreatmentsOption)
        );
    }

    public WebElement getSearchBox() {
        return Allure.step("Get Search box element", () ->
                wait.untilElementVisible(LocatorsType.ByName, SearchField)
        );
    }

     public boolean waitForTreatmentSuccessfullyCompleted(String message) {
        return waitForToastMessage(message, 200);
    }

    public boolean waitForTreatmentSuccessfullyCompletedMessageDisappeared(String message) {
        return waitForToastDisappear(By.xpath("//div[contains(@class,'toast-body')]"));
    }

    public List<String> getValuesOfSelectedTreatments() {
        return Allure.step("Get values of selected treatments from table", () -> {
            List<String> tableData = new ArrayList<>();
            wait.untilElementHasText(LocatorsType.ByXpath, TableRowsData);
            List<WebElement> elements = wait.untilAllElementsVisible(LocatorsType.ByXpath, TableRowsData);
            for (WebElement element : elements) {
                tableData.add(element.getText());
            }
            return tableData;
        });
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

    @Step("Check if toaster message is visible: {errorMessage}")
    public boolean isToasterMessageVisible(String expectedMessage) {

    try {

        By toastLocator = By.xpath(
                "//div[@class='toast-body' and contains(normalize-space(), '"+expectedMessage+"')]"
        );

        boolean istoast = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.invisibilityOfElementWithText(toastLocator, expectedMessage));
                
        return istoast;

    } catch (TimeoutException e) {

        AllureLogger.failStep("Toast message not visible: " + expectedMessage, e);
        captureScreenshot("Toaster Message Visibility Failure");
        return false;
    }
}
}