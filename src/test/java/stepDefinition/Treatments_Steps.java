package stepDefinition;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.Assert;

import PageObjects.Pages.TreatmentsPage;


public class Treatments_Steps extends Base_Steps{
    TreatmentsPage treatmentsPage = pages.getTreatmentsPage();  

    @Then("I verify treatment page is empty")
    public void iVerifyTreatmentPageIsEmpty(){
        Allure.step("Verifying that treatment page is empty", () ->
                Assert.assertTrue("Treatment page is not empty", treatmentsPage.getNoRecordFoundMessageDisplayed().isDisplayed())
        );
    }

    @Then("I verify treatment page is not empty")
    public void iVerifyTreatmentPageIsNotEmpty(){
        Allure.step("Verifying that treatment page has data", () ->
                Assert.assertFalse("Treatment page is empty", treatmentsPage.getTableData().isEmpty())
        );
    }

    @When("I click on Create New Treatment button")
    public void iClickOnCreateTreatmentButton() {   
        Allure.step("Clicking on Create Treatment button", () ->
                treatmentsPage.getCreateTreatmentButton().click()
        );
    }
}
