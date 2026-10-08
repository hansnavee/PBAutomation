package stepDefinition;

import Hooks.PBHooks;
import PageObjects.Pages.CreateScenariosPage;
import PageObjects.Pages.ScenarioPage;
import Utils.RandomHelper;
import Utils.ScenarioContext;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

public class CreateNewScenarios_Steps extends Base_Steps{
    CreateScenariosPage createScenariosPage = pages.getCreateScenariosPage();
    ScenarioPage scenarioPage = pages.getScenarioPage();


    @When("I create a new scenario")
    public void iCreateNewScenario() {
        String scenarioName = "Auto " +RandomHelper.randomString(10);
        String treatmentName = "Auto " + RandomHelper.randomString(10);
        String expectedMessage = "Scenario was created successfully";

        ScenarioContext.set("scenarioName", scenarioName);
        ScenarioContext.set("treatmentName", treatmentName);
        Allure.step("I create a scenario: " + scenarioName + treatmentName, () -> {
            try {
                createScenariosPage.createNewScenario(scenarioName, treatmentName);
                if(scenarioPage.isSuccessToastMessage(expectedMessage)){
                        return;
                }
            } catch (AssertionError | Exception e) {
                String message = "Scenario is not created: " + scenarioName +  treatmentName + "\nReason: " + e.getMessage();
                Allure.addAttachment("Scenario is not created", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @When("I create a scenario with deleted scenario details")
    public void iCreateNewScenarioWithDeletedScenarioDetails() {
        String scenarioName = ScenarioContext.get("scenarioName");
        String treatmentName = "Auto " + RandomHelper.randomString(10);

        ScenarioContext.set("treatmentName", treatmentName);

        Allure.step("I create a scenario: " + scenarioName + treatmentName, () -> {
            try {
                createScenariosPage.createNewScenario(scenarioName, treatmentName);
            } catch (AssertionError | Exception e) {
                String message = "Scenario is not created: " + scenarioName +  treatmentName + "\nReason: " + e.getMessage();
                Allure.addAttachment("Scenario is not created", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }
}
