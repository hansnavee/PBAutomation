package stepDefinition;

import org.junit.Assert;
import PageObjects.Pages.Analysis.Reports.ReportsTab;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;

public class ReportsTab_Steps extends Base_Steps{

     ReportsTab reportsTabs = pages.getReportsTab();

    @Then("I verify that {string} message is displayed on {string} under Reports tab")
    public void iVerifyThatNoDataAvailableMessageIsDisplayed(String noDataAvailableMessage, String tabName) {
        Allure.step("verify that message is displayed on " + tabName +  ""  + noDataAvailableMessage , () -> {
            Assert.assertEquals(noDataAvailableMessage, reportsTabs.getNoDataAvailableMessage().getText());
        });
    
    }

   @Then("I verify that download button is not displayed on {string} under Reports tab")
    public void iVerifyThatDownloadButtonIsNotDisplayed(String tabName) {
        Allure.step("verify that download button is disabled on" + tabName , () -> {
            Assert.assertFalse(reportsTabs.isDownloadButtonDisplayed());
        });
    }

}
