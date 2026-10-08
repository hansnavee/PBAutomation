package stepDefinition;
import PageObjects.Pages.CreateTreatmentPoolModal.CreateTreatmentPoolLibraryModal;
import PageObjects.Pages.CreateTreatmentPoolModal.ExportProjectModal;
import PageObjects.Pages.CreateTreatmentPoolModal.ImportFileModal;
import PageObjects.Pages.ScenarioPage;
import PageObjects.Pages.TreatmentPoolLibrariesPage;
import PageObjects.Pages.CreateScenarioTabs.BudgetConstraints;
import PageObjects.Pages.CreateScenarioTabs.LeftSideBarMenu;
import Utils.RandomHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

public class TreatmentPoolLibrary_Steps extends Base_Steps {
    TreatmentPoolLibrariesPage treatmentPoolLibrariesPage = pages.getTreatmentPoolLibrariesPage();
    ScenarioPage scenarioPage = pages.getScenarioPage();
    LeftSideBarMenu leftSideBarMenu = pages.getLeftSideBarMenu();   
    CreateTreatmentPoolLibraryModal createTreatmentPoolLibraryModal = pages.getCreateTreatmentPoolLibraryModal();
    ExportProjectModal exportProjectModal = pages.getExportProjectModal();
    ImportFileModal importFileModal = pages.getImportFileModal();
    BudgetConstraints  budgetConstraints = pages.getBudgetConstraints();
    int countBeforeReset = 0;
    String treatmentName;
    String treatmentDescription;

    @When("I create a new treatment pool {string} {string} field")
    public void iCreateNewTreatmentPool(String text, String fieldName) {
        treatmentName = "Auto " + RandomHelper.randomString(20);

        Allure.step("Creating new treatment pool (type: " + text + " field: " + fieldName + ") name: " + treatmentName, () -> {
            try {
                iClickCreateNewTreatmentPoolButton();

                if (text.equalsIgnoreCase("with") && fieldName.equalsIgnoreCase("all"))
                    createTreatmentPoolLibraryModal.createTreatmentPool(treatmentName);

                else if (text.equalsIgnoreCase("without") && fieldName.equalsIgnoreCase("description"))
                    createTreatmentPoolLibraryModal.createTreatmentPoolWithoutDescription(treatmentName);

                else if (text.equalsIgnoreCase("without") && fieldName.equalsIgnoreCase("name"))
                    createTreatmentPoolLibraryModal.createTreatmentPoolWithoutName(treatmentName);

            } catch (AssertionError | Exception e) {
                String message = "Treatment creation failed for: " + treatmentName + "\nReason: " + e.getMessage();
                Allure.addAttachment("Treatment creation failed", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @When("I search the added treatment pool")
    public void searchAddedScenario() {
        countBeforeReset = treatmentPoolLibrariesPage.getValuesOfSelectedTreatments().size();
        treatmentPoolLibrariesPage.searchNameField(treatmentName);
        treatmentPoolLibrariesPage.clickSearchButton();
    }

    @Then("I verify that added treatment pool is displayed under treatment pool library list")
    public void iVerifyThatAddedTreatmentPoolIsDisplayedUnderTreatmentPoolLibraryList() {
        Allure.step("Verifying newly added treatment pool exists: " + treatmentName, () -> {
            try {

                Assert.assertFalse(treatmentPoolLibrariesPage.getTableRows().isEmpty());

            } catch (AssertionError | Exception e) {
                String message = "Added treatment pool NOT found: " + treatmentName + "\nReason: " + e.getMessage();
                Allure.addAttachment("Treatment pool missing", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @When("I click on Create New Treatment Pool Button")
    public void iClickCreateNewTreatmentPoolButton() {
        Allure.step("Clicking Create New Treatment Pool button", () ->
                treatmentPoolLibrariesPage.clickCreateNewTreatmentPoolButton()
        );
    }

    @When("I enter characters more than max limit of name field")
    public void iEnterCharactersMoreThanMaxLimitOfNameField() {

        treatmentName = RandomHelper.randomString(52);

        Allure.step("Entering more than max characters in Name field: length=" + treatmentName.length(), () -> {
            createTreatmentPoolLibraryModal.getNameField().sendKeys(treatmentName);
        });
    }

    @When("I click on create button")
    public void iClickOnCreateButton() {
        Allure.step("Clicking Create button", () ->
                createTreatmentPoolLibraryModal.clickCreateButton()
        );
    }

    @When("I update the Name of treatment pool library")
    public void iUpdateNameOfTreatmentPoolLibrary() {

        String oldName = treatmentName;
        treatmentName = oldName + "Updated";

        Allure.step("Updating treatment pool name from: " + oldName + " to: " + treatmentName, () -> {
            createTreatmentPoolLibraryModal.getNameFieldFromEditForm().clear();
            createTreatmentPoolLibraryModal.getNameFieldFromEditForm().sendKeys(treatmentName);
            createTreatmentPoolLibraryModal.clickUpdateButton();
        });
    }

    @When("I click on update button")
    public void iClickOnUpdateButton() {
        Allure.step("Clicking Update button", () ->
                createTreatmentPoolLibraryModal.clickUpdateButton()
        );
    }

    @When("I click on {string} left side menu")
    public void iClickOnCloseIcon(String tabName) {
        Allure.step("Clicking Close icon", () ->
            leftSideBarMenu.getMenuItem(tabName).click()
        );
    }

     @When("I click on close icon")
    public void iClickOnCloseIcon() {
        Allure.step("Clicking Close icon", () ->
                createTreatmentPoolLibraryModal.getCloseIcon().click()
        );
    }

    @Then("I verify Name Field not allow to copy more than max limit characters")
    public void iVerifyNameFieldNotAllowToCopyMoreThanMaxLimit() {
        Allure.step("Verifying Name field does NOT allow more than max characters", () -> {
            Assert.assertNotEquals(treatmentName, createTreatmentPoolLibraryModal.getNameField().getText());
        });
    }

    @Then("I verify that validation error {string} message displayed for all required fields")
    public void iVerifyThatValidationErrorMessageDisplayedForRequiredFields(String error) {
        Allure.step("Verifying validation error for ALL fields: " + error, () -> {
            Assert.assertEquals(error, createTreatmentPoolLibraryModal.getDescriptionFieldValidationMessage());
            Assert.assertEquals(error, createTreatmentPoolLibraryModal.getNameFieldValidationMessage());
        });
    }

    @Then("I verify that validation error {string} message displayed for name field")
    public void iVerifyThatValidationErrorMessageDisplayedForNameField(String error) {
        Allure.step("Verifying validation error for Name field: " + error, () -> {
            Assert.assertEquals(error, createTreatmentPoolLibraryModal.getNameFieldValidationMessage());
        });
    }

    @Then("I verify that validation error {string} message displayed for description field")
    public void iVerifyThatValidationErrorMessageDisplayedForDescriptionField(String error) {
        Allure.step("Verifying validation error for Description field: " + error, () -> {
            Assert.assertEquals(error, createTreatmentPoolLibraryModal.getDescriptionFieldValidationMessage());
        });
    }

    @Then("I verify default message {string} is displayed")
    public void iVerifyDefaultMessageDisplyed(String defaultMessage) {
        Allure.step("Verifying navigated to page: " + defaultMessage, () ->
                Assert.assertEquals(defaultMessage, budgetConstraints.getBudgetConstraintsDefaultMessage().getText())
        );
    }

    @Then("I verify success message {string} is displayed")
    public void iVerifyDefaultMessageNotDisplyed(String succesMessage) {
        Allure.step("Verifying navigated to page: " + succesMessage, () ->
                Assert.assertEquals(succesMessage, budgetConstraints.getBudgetConstraintsSuccessMessage().getText()
                .replaceAll("\\s+", " ").trim())
        );
    }

    @Then("I Create New Treatment Pool dialog displayed")
    public void iVerifyCreateNewTreatmentPoolLibraryDialogIsDisplayed() {
        Allure.step("Verifying Create New Treatment Pool dialog is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.isCreateNewLibraryFormLabelDisplayed())
        );
    }

    @Then("I Edit Treatment Pool dialog displayed")
    public void iVerifyEditExistingTreatmentPoolLibraryDialogIsDisplayed() {
        Allure.step("Verifying Edit Treatment Pool dialog is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getEditTreatmentPoolLibraryFormLabel().isDisplayed())
        );
    }

    @Then("I verify Create New Treatment Pool library dialog is closed")
    public void iVerifyCreateNewTreatmentPoolLibraryDialogIsClosed()  {
            Allure.step("Verifying Create New Treatment Pool dialog is closed", () ->
                    Assert.assertTrue(treatmentPoolLibrariesPage.waitForAttributeValueToBeUpdated("display: none;"))
            );
    }

    @Then("I verify Name field is displayed")
    public void iVerifyNameFieldDisplayed() {
        Allure.step("Verifying Name field is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getNameField().isDisplayed())
        );
    }

    @Then("I verify shared toggle button is displayed")
    public void iVerifySharedToggleButtonIsDisplayed() {
        Allure.step("Verifying Shared toggle button is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getSharedToggleButton().isDisplayed())
        );
    }

    @Then("I verify shared toggle label is displayed")
    public void iVerifySharedToggleLabelIsDisplayed() {
        Allure.step("Verifying Shared toggle label is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getSharedToggleLabel().isDisplayed())
        );
    }

    @Then("I verify Description field is displayed")
    public void iVerifyDescriptionFieldDisplayed() {
        Allure.step("Verifying Description field is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getDescriptionField().isDisplayed())
        );
    }

    @Then("I verify Cancel button is displayed")
    public void iVerifyCancelButtonIsDisplayed() {
        Allure.step("Verifying Cancel button is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getCancelButton().isDisplayed())
        );
    }

    @Then("I verify Cancel button is displayed on edit form")
    public void iVerifyCancelButtonIsDisplayedOnEditForm() {
        Allure.step("Verifying Cancel button is displayed on edit form", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getCancelButtonFromEditForm().isDisplayed())
        );
    }

    @Then("I verify Create button is displayed")
    public void iVerifyCreateButtonIsDisplayed() {
        Allure.step("Verifying Create button is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getCreateButton().isDisplayed())
        );
    }

    @Then("I verify Close icon is displayed")
    public void iVerifyCloseIconIsDisplayed() {
        Allure.step("Verifying Close icon is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getCloseIcon().isDisplayed())
        );
    }

    @Then("I verify Close icon is displayed on edit form")
    public void iVerifyCloseIconIsDisplayedOnEditForm() {
        Allure.step("Verifying Close icon is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getCloseIconEditForm().isDisplayed())
        );
    }

    @When("I fill all required fields for new treatment pool creation")
    public void iFillAllRequiredFieldsForTreatmentPoolCreation() {

        treatmentName = RandomHelper.randomString(20);

        Allure.step("Filling all required fields for creation, Name: " + treatmentName, () ->
                createTreatmentPoolLibraryModal.setTreatmentPoolAllRequiredFields(treatmentName)
        );
    }

    @When("I click on cancel button")
    public void iClickOnCancelButton() {
        Allure.step("Clicking Cancel button", () ->
                createTreatmentPoolLibraryModal.clickCancelButton()
        );
    }

    @When("I click on selected three dots under Action column")
    public void iClickOnSelectedThreeDotsUnderActionColumn() {
        Allure.step("Clicking three dots under Action column", () ->
                treatmentPoolLibrariesPage.getActionColumnThreeDots().click()
        );
    }

    @When("I select on edit option")
    public void iSelectEditOption() {
        Allure.step("Selecting Edit option", () ->
                treatmentPoolLibrariesPage.getEditOption().click()
        );
    }

    @Then("I verify that treatment pool is not displayed under treatment pool library list")
    public void iVerifyThatTreatmentPoolIsNotDisplayedUnderTreatmentPoolLibraryList() {
        Allure.step("Verifying treatment pool does NOT exist : " + treatmentName, () -> {
            try {
                treatmentPoolLibrariesPage.searchNameField(treatmentName);
                treatmentPoolLibrariesPage.clickSearchButton();

                Assert.assertTrue(treatmentPoolLibrariesPage.getHeadingTextNoDataAvailable().isDisplayed());

            } catch (AssertionError | Exception e) {
                String message = "Treatment pool FOUND unexpectedly: " + treatmentName + "\nReason: " + e.getMessage();
                Allure.addAttachment("Treatment pool unexpectedly present", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @Then("I verify Name field value is retained")
    public void iVerifyNameFieldValueIsRetained() {
        Allure.step("Verifying Name field value retained: " + treatmentName, () ->
                Assert.assertEquals(treatmentName, createTreatmentPoolLibraryModal.getNameFieldFromEditForm().getAttribute("value"))
        );
    }

    @Then("I verify Update button is displayed")
    public void iVerifyUpdateButtonIsDisplayed() {
        Allure.step("Verifying Update button is displayed", () ->
                Assert.assertTrue(createTreatmentPoolLibraryModal.getUpdateButton().isDisplayed())
        );
    }

    @Then("I verify Description field value is retained")
    public void iVerifyDescriptionFieldValueIsRetained() {
        Allure.step("Verifying Description field value retained: " + treatmentDescription, () ->
                Assert.assertEquals(treatmentName, createTreatmentPoolLibraryModal.getDescriptionFieldFromEditForm().getAttribute("value"))
        );
    }

    @Then("I Verify that {string} option is displayed under treatments actions")
    public void iVerifyThatOptionIsDisplayed(String name) {
        Allure.step("Verifying that option '" + name + "' is displayed under treatments actions", () ->
                Assert.assertTrue(treatmentPoolLibrariesPage.isOptionDisplayed(name))
        );
    }

    @Then("I Verify that Delete option is displayed under treatments actions")
    public void iVerifyDeleteOptionIsDisabled() {
        Allure.step("Verifying that Delete option is disabled under treatments actions", () ->
                Assert.assertTrue(treatmentPoolLibrariesPage.isDeleteOptionDisabled())
        );
    }

    @When("I click on Copy option")
    public void iClickOnCopyOption() {
        Allure.step("Clicking on Copy option under treatments actions", () ->
                treatmentPoolLibrariesPage.getCopyOption().click()
        );
    }

    @When("I click on Import option from treatment page")
    public void iClickOnImportOption() {
        Allure.step("Clicking on Import option from treatment page", () ->
                treatmentPoolLibrariesPage.getImportOption().click()
        );
    }

    @When("I click on Treatments option from treatment page")
    public void iClickOnTreatmentsOption() {
        Allure.step("Clicking on Treatments option from treatment page", () ->
                treatmentPoolLibrariesPage.getTreatmentsOption().click()
        );
    }

    @Then("I validate that {string} treatment imported successfully from treatment page")
    public void iValidateThatTreatmentFileImportedSuccessfully(String treatmentValues) {
        Allure.step("Validating that treatment '" + treatmentValues + "' imported successfully from treatment page", () -> {
            boolean valueExists = treatmentPoolLibrariesPage.getValuesOfSelectedTreatments().contains(treatmentValues);
            Assert.assertTrue("Treatment values not exists", valueExists);
        });
    }

    @When("I click on Reset button")
    public void clickResetButton() {
        Allure.step("Clicking on Reset button to reset search and table data", () ->
                treatmentPoolLibrariesPage.clickResetButton()
        );
    }

    @Then("I verify that search field is empty")
    public void iValidateSearchFieldIsEmpty() {
        Allure.step("Validating that search field is empty after reset", () ->
                Assert.assertTrue(treatmentPoolLibrariesPage.getSearchBox().getText().isEmpty())
        );
    }

    @Then("I verify that table data is reset")
    public void iValidateTableDataIsReset() {
        Allure.step("Validating that table data is reset after clicking Reset", () -> {
            int countAfterReset = treatmentPoolLibrariesPage.getValuesOfSelectedTreatments().size();
            Assert.assertEquals("Table row count does not match after reset", countBeforeReset, countAfterReset);
        });
    }

    @When("I export {string} the file")
    public void iExportFile(String fileType) {
        Allure.step("Exporting treatment file of type: " + fileType, () -> {
            exportProjectModal.selectFileType(fileType);
            exportProjectModal.clickExportButton();
        });
    }

    @When("I import {string} file {string}")
    public void iImportFile(String fileType, String fileName) {
        Allure.step("Importing file '" + fileName + "' of type '" + fileType + "'", () -> {
            importFileModal.selectFileType(fileType);
            importFileModal.uploadFile(fileName);
            waitForImportTobeCompleted();
        });
    }

    public void waitForImportTobeCompleted() {
        treatmentPoolLibrariesPage.waitForTreatmentSuccessfullyCompleted("Treatments were imported successfuly");
        treatmentPoolLibrariesPage.waitForTreatmentSuccessfullyCompletedMessageDisappeared("Treatments were imported successfuly");
    }

    // public void waitForScenarioPageDisplayed(){
    //     scenarioPage.getSearchBox().isDisplayed();
    // }

    @When("I import {string} file {string} with {string}")
    public void iImportFile(String fileType, String fileName, String tabName) {
        Allure.step("Importing file '" + fileName + "' of type '" + fileType + "' with tab '" + tabName + "'", () -> {
            importFileModal.selectFileType(fileType);
            importFileModal.uploadFile(fileName, tabName);
            waitForImportTobeCompleted();
        });
    }

    @Then("I validate that treatment file exported successfully")
    public void iValidateThatTreatmentFileExportedSuccessfully() {
        Allure.step("Validating that treatment file exported successfully", () -> {
            boolean downloaded = exportProjectModal.isFileDownloaded();
            Assert.assertTrue("File did not download!", downloaded);
        });
    }

    @Then("I validate that system through import error {string}")
    public void iValidateThatTreatmentFileExportError(String errorMessage) {
        Allure.step("Validating system shows import error: '" + errorMessage + "'", () -> {
            Assert.assertTrue("Expected import error message not visible", treatmentPoolLibrariesPage.isToasterMessageVisible(errorMessage));
        });
    }

    @Then("I validate that system display inprogress message invisible {string}")
    public void iValidateThatSystemDisplaySuccessfulMessage(String inProgressMessage) {
        Allure.step("Validating system shows import success: '" + inProgressMessage + "'", () -> {
            Assert.assertTrue("Expected import success message not visible", treatmentPoolLibrariesPage.isToasterMessageVisible(inProgressMessage));
        });
    }
}