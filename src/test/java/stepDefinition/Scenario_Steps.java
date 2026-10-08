package stepDefinition;

import PageObjects.Pages.ScenarioPage;
import PageObjects.Pages.CreateScenarioTabs.BudgetConstraints;
import Utils.FileUtility;
import Utils.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.Assert;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Scenario_Steps extends Base_Steps{

    ScenarioPage scenarioPage = pages.getScenarioPage();
    BudgetConstraints budgetConstraints = pages.getBudgetConstraints();
    int countBeforeReset = 0;

    @When("I search the added scenario")
    public void searchAddedScenario() {
        String scenarioName = ScenarioContext.get("scenarioName");
        Allure.step("Searching for scenario: " + scenarioName, () -> {
            countBeforeReset = scenarioPage.getValuesOfScenario().size();
            scenarioPage.searchScenarioNameField(scenarioName);
            scenarioPage.clickSearchButton();
        });
    }

    @When("I click on create new scenario button")
    public void iClickOnCreateNewScenarioButton() {
        Allure.step("Clicking on create new scenario button", () -> {
            try {
                scenarioPage.clickCreateNewScenarioButton();
            } catch (AssertionError | Exception e) {
                String message = "Create new scenario button not displayed\nReason: " + e.getMessage();
                Allure.addAttachment("Error", "text/plain", message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @Then("I validate that {string} treatment imported successfully")
    public void iValidateThatTreatmentFileImportedSuccessfully(String treatmentValues) {
        Allure.step("Validating that treatment is imported successfully: " + treatmentValues, () -> {
            Assert.assertTrue(
                    "Treatment value not found",
                    scenarioPage.waitUntilTableContains(treatmentValues)
            );
        });
    }

    @When("I click on run option scenario page")
    public void iClickOnRunOptionScenarioPage() {
        Allure.step("Clicking on Run option in scenario page", () -> scenarioPage.getRunOption().click());
    }

    @Then("I verify that added scenario is displayed under Scenarios list")
    public void iVerifyAddedScenarioDisplayedUnderScenarioList() {
        String scenarioName = ScenarioContext.get("scenarioName");
        String treatmentName = ScenarioContext.get("treatmentName");
        Allure.step("Verifying scenario is displayed: " + scenarioName + " | " + treatmentName, () -> {
            try {
            Assert.assertTrue(
                    "Scenario not displayed: " + scenarioName,
                    scenarioPage.waitUntilTableContains(scenarioName)
            );
            } catch (AssertionError | Exception e) {
                String message = "Scenario not displayed: " + scenarioName + "--treatment: " + treatmentName + "\nReason: " + e.getMessage();
                Allure.addAttachment("Scenario display error", "text/plain", message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @Then("I verify that added scenario is not displayed under Scenarios list")
    public void iVerifyAddedScenarioNotDisplayedUnderScenarioList() {
        String scenarioName = ScenarioContext.get("scenarioName");
        String treatmentName = ScenarioContext.get("treatmentName");
        Allure.step("Verifying scenario is NOT displayed: " + scenarioName + " | " + treatmentName, () -> {
            try {
                if (scenarioPage.getElementVisible().isDisplayed()) {
                    scenarioPage.searchScenarioNameField(scenarioName);
                    scenarioPage.clickSearchButton();
                    Assert.assertTrue(scenarioPage.getMessageNoDataAvailableInTable().isDisplayed());
                }
            } catch (AssertionError | Exception e) {
                String message = "Scenario exists: " + scenarioName + "--treatment: " + treatmentName + "\nReason: " + e.getMessage();
                Allure.addAttachment("Scenario should not exist", "text/plain", message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @When("I click on Actions three dots of scenario page")
    public void iClickOnActionsThreeDotsOfScenarioPage() {
        Allure.step("Clicking on Actions three dots in scenario page", () -> scenarioPage.clickActionColumnThreeDots());
    }

    @When("I click on export option scenario page")
    public void iClickOnExportOptionScenarioPage() {
        Allure.step("Clicking on Export option", () -> scenarioPage.getExportOption().click());
    }

    @When("I click on import option scenario page")
    public void iClickOnImportOptionScenarioPage() {
        Allure.step("Clicking on Import option", () -> scenarioPage.getImportOption().click());
    }

    @When("I click on Treatment Pool Library option from action menu")
    public void iClickOnTreatmentPoolLibraryFromActionMenu() {
        Allure.step("Clicking on Treatment Pool Library option", () -> scenarioPage.getTreatmentPoolLibraryOption().click());
    }

     @When("I click on Settings option from action menu")
    public void iClickOnSettingsFromctionMenu() {
        Allure.step("Clicking on Settings option", () -> scenarioPage.getSettingsOption().click());
    }

      @When("I click on Analysis option from action menu")
    public void iClickOnAnalysisFromctionMenu() {
        Allure.step("Clicking on Analysis option", () -> scenarioPage.getAnalysisOption().click());
    }

    @Then("I click {string} the scenario")
    public void iClickOptionByText(String optionName) {
        Allure.step("Clicking on option: " + optionName, () -> {
            scenarioPage.getOption(optionName).click();
            scenarioPage.acceptAlert();
        });
    }

    @Then("I Verify that {string} option is displayed")
    public void iVerifyThatOptionIsDisplayed(String name) {
        Allure.step("Verifying that option is displayed: " + name, () -> Assert.assertTrue(scenarioPage.isOptionDisplayed(name)));
    }

    @Then("I Verify that Scenario Parameter page is displayed")
    public void iVerifyScenarioParameterPageIsDisplayed(String name) {
        Allure.step("Verifying that option is displayed: " + name, () -> Assert.assertTrue(scenarioPage.isOptionDisplayed(name)));
    }

    @Then("I verify status of Scenario is change to {string}")
    public void iValidateStatusOfScenarioSuccess(String status) {
        String scenarioName = ScenarioContext.get("scenarioName");
        Allure.step("Verifying scenario status is: " + status, () -> {
            boolean statusUpdated = scenarioPage.waitUntilStatusAppears(status);
            Assert.assertTrue("Scenario status not found", statusUpdated);
        });
    }

    @When("I click Reset button to reset scenarios")
    public void clickResetButton() {
        Allure.step("Clicking Reset button to reset scenarios", () -> scenarioPage.clickResetButton());
    }

    @Then("I verify that scenario search field is empty")
    public void iValidateSearchFieldIsEmpty() {
        Allure.step("Validating that scenario search field is empty", () ->
                Assert.assertTrue(scenarioPage.getSearchBox().getText().isEmpty()));
    }

    @Then("I verify that scenarios table data is reset")
    public void iValidateTableDataIsReset() {
        Allure.step("Verifying that scenarios table data is reset", () -> {
            int countAfterReset = scenarioPage.getValuesOfScenario().size();
            Assert.assertEquals("Scenario table count mismatch", countBeforeReset, countAfterReset);
        });
    }

    @When("I upload the budget constraints file {string}")
    public void iUploadBudgetConstraintsFile(String fileName) {
        Allure.step("Uploading budget constraints file: " + fileName, () -> {
            budgetConstraints.uploadBudgetConstraintsFile(fileName);
        });
    }

    @When("I click on download template link")
    public void iClickOnDownloadTemplateLink() {
        Allure.step("Clicking on download template link", () -> {
            budgetConstraints.getDownloadButton().click();
        });
    }

    @Then("I validate that budget constraints template file downloaded successfully")
    public void iValidateBudgetConstraintsTemplateDownloaded() {
        Allure.step("Validating that budget constraints template file downloaded successfully", () -> {
            String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";
            File downloadedFile = FileUtility.waitForNewDownloadedFile(downloadPath, 30);
            Assert.assertNotNull("Template file was not downloaded", downloadedFile);
            Assert.assertTrue("Downloaded file is empty", downloadedFile.length() > 0);
        });
    }

    @When("I click on upload budget constraints file and upload the file {string}")
    public void iClickOnUploadBudgetConstraintsFile(String fileName) {
        Allure.step("Uploading budget constraints file: " + fileName, () -> {
            budgetConstraints.uploadBudgetConstraintsFile(fileName);
        });
    }

    @When("I click on Import budget button")
    public void iClickOnUploadBudgetButton() {
        Allure.step("Uploading budget constraints file: ", () -> {
            budgetConstraints.getImportBudgetButton().click();
        });
    }

    @When("I click on Import button")
    public void iClickOnImportButton() {
        Allure.step("Click Import button ", () -> {
            budgetConstraints.clickImportButton();
        });
    }

    @Then("I validate that note {string} is displayed when no treatments are added")
    public void iValidateBudgetConstraintsNoteIsDisplayed(String note) {
        Allure.step("Validating that budget constraints file uploaded successfully", () -> {
            // Add validation logic here, e.g., checking for a success message or updated UI state
            Assert.assertEquals(note, budgetConstraints.getUploadBudgetConstraintsNote().getText());
        });
    }

     @Then("I validate that note {string} is not displayed when no treatments are added")
    public void iValidateBudgetConstraintsNoteIsNotDisplayed(String note) {
        Allure.step("Validating that budget constraints file uploaded successfully", () -> {
            // Add validation logic here, e.g., checking for a success message or updated UI state
            Assert.assertFalse(budgetConstraints.isUploadBudgetConstraintsNoteDisplayed());
        });
    }
}
