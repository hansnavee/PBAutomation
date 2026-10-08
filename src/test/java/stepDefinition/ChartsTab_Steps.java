package stepDefinition;

import org.junit.Assert;

import PageObjects.Pages.Analysis.ChartsTabs.ChartsTabs;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;

public class ChartsTab_Steps extends Base_Steps{

    ChartsTabs chartsTabs = pages.getChartsTabs();

    @Then("I verify that {string} message is displayed on {string} under Charts tab")
    public void iVerifyThatNoRecordsFoundMessageIsDisplayedOnNeedsPage(String noRecordsFoundMessage, String tabName) {
        Allure.step("verify that message is displayed on " + tabName +  ""  + noRecordsFoundMessage , () -> {
            Assert.assertEquals(noRecordsFoundMessage, chartsTabs.getNoRecordFoundMessage().getText());
        });
    }
    
}
