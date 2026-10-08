package stepDefinition;

import static org.junit.Assert.*;

import PageObjects.Pages.CreateTreatmentPoolModal.CreateTreatmentModal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Treatment_Modal_Steps extends Base_Steps {

    CreateTreatmentModal createTreatmentModal = pages.getCreateTreatmentModal();

    @Then("I verify that {string} modal is displayed")
    public void iVerifyThatCreateTreatmentModalIsDisplayed(String modalName) {
        assertEquals(modalName , createTreatmentModal.getCreateTreatmentLabel().getText());
    }
    
    @When("I click on Cancel button in Create Treatment modal")
    public void iClickOnCancelButtonInCreateTreatmentModal() {
        createTreatmentModal.getCancelButton().click();;   
    }

    @When("I click on Close icon in Create Treatment modal")
    public void iClickOnloseIconInCreateTreatmentModal() {
        createTreatmentModal.getCloseIcon().click();;   
    }

    @Then("I verify that Create Treatment modal is not displayed")
    public void iVerifyThatCreateTreatmentModalIsNotDisplayed() {
        assertFalse(createTreatmentModal.isCreateTreatmentLabelVisible());
    }

}
