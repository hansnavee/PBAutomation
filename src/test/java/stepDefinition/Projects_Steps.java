package stepDefinition;

import org.junit.Assert;

import PageObjects.Pages.Analysis.LeftTabMenu;
import PageObjects.Pages.Analysis.ChartsTabs.ChartsTabs;
import PageObjects.Pages.Analysis.Projects.ProjectsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;

public class Projects_Steps extends Base_Steps{

    ProjectsPage projectPage = pages.getProjectsPage();
    LeftTabMenu leftTabMenu = pages.getLeftTabMenu();
    ChartsTabs chartsTabs = pages.getChartsTabs();

    @Then("I verify that button Export to excel button is enabled")
    public void iVerifyThatExportToExcelButtonIsEnabled() {

        Allure.step("I verify that button Export to excel button " , () -> {
            Assert.assertTrue(projectPage.getExportToExcelButton().isEnabled());
        });
    }

    @Then("I verify that button Download Json button is enabled")
    public void iVerifyThatExportToJsonButtonIsEnabled() {

        Allure.step("I verify that button Export to json button " , () -> {
            Assert.assertTrue(projectPage.getExportToJsonButton().isEnabled());
        });
    }

    @When("I click on Project Treatments button")
    public void iClickOnProjectTreatmentsButton() {

        Allure.step("I click on Project Treatments button", () -> {
            projectPage.getProjectTreatmentsButton().click();
        });
    }

    @Then("I verify that No records found message is displayed")
public void iVerifyThatNoRecordsFoundMessageIsDisplayed() {

    long rows = projectPage.getProjectsTableRowCount();

    Assert.assertEquals("Expected no records, but table contains " + rows + " rows", 0L, rows);
}

    @Then("I verify that download button is displayed disabled on {string} under Charts tab")
    public void iVerifyThatDownloadButtonIsDisable(String tabName) {
        Allure.step("verify that download button is disabled on" + tabName , () -> {
            Assert.assertFalse(chartsTabs.getDownloadButton().isEnabled());
        });
    }

    @When("I click on {string} tab from charts dropdown menu")
    public void iClickOnChartsDropdownButtonToExpandMenu(String menuName) {
    
        Allure.step("I expand charts menu if not expanded", () ->
                leftTabMenu.openChartsSubMenu(menuName)
        );
    }

    @When("I click on {string} tab from reports dropdown menu")
    public void iClickOnReportsDropdownButtonToExpandMenu(String menuName) {
    
        Allure.step("I expand reports menu if not expanded", () ->
                leftTabMenu.openReportsSubMenu(menuName)
        );
    }

    @When("I click on Map tab")
    public void iClickOnMapsDropdownButtonToExpandMenu() {
    
        Allure.step("I click on Map tab" , () -> {
            leftTabMenu.clickOnMap();
        });
    }
    
}
