package stepDefinition;

import org.junit.Assert;

import PageObjects.Pages.Analysis.MapPage;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;

public class Map_Steps extends Base_Steps{
    MapPage mapPage = pages.getMapPage();

    @Then("I verify that Map page is loaded successfully")
    public void iVerifyThatMapPageIsDisplayed() {
        Allure.step("verify that Map page loaded successfully", () -> {
            Assert.assertTrue(mapPage.getProjectTreatmentsHeadingText().isDisplayed());
        });
    }
    
}
