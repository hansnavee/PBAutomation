package stepDefinition;

import Utils.StepActions;
import org.junit.Assert;

import io.cucumber.java.en.Then;

public class Navigation_Steps extends Base_Steps {

    @Then("I verify navigate to {string} page")
    @Then("I verify user navigate to {string} page")
    @Then("I verify that user navigate to {string} page")
    public void verifyNavigatedToPage(String expectedPage) {
        StepActions.run("Verify navigation to " + expectedPage, () -> {
            if ("Project Treatments".equals(expectedPage)) {
                Assert.assertEquals(
                        expectedPage,
                        pages.getProjectTreatmentsPage().getPageHeading().getText().trim()
                );
                return;
            }

            String title = pages.getLoginPage().getTitle();
            if (expectedPage.equals(title == null ? "" : title.trim())) {
                return;
            }

            Assert.assertEquals(expectedPage, pages.getProjectsPage().getPageHeading().getText().trim());
        });
    }
}
