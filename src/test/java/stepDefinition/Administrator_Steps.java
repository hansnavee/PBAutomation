package stepDefinition;

import PageObjects.Pages.AdministratorPage;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

public class Administrator_Steps extends Base_Steps {

    AdministratorPage administratorPage = pages.getAdministratorPage();

    @When("I click on {string}")
    public void iClickOnMenu(String menuText) {
        Allure.step("I click on menu item: " + menuText, () -> {
            try {
               administratorPage.clickMenu(menuText);
            } catch (AssertionError | Exception e) {
                String message = "Menu item is not displayed: " + menuText + "\nReason: " + e.getMessage();
                Allure.addAttachment("Menu item is not displayed", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }
}
