package Hooks;

import Utils.DriverManager;
import Utils.InitializeBrowser;
import Utils.ScenarioContext;
import Utils.XMLFileUtility;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PBHooks {

    private static final Logger log = LoggerFactory.getLogger(PBHooks.class);

    private InitializeBrowser browser;

    @Before
    public void beforeScenario(Scenario scenario) {
        ScenarioContext.clear();
        log.info("Starting Scenario: {}", scenario.getName());

        browser = new InitializeBrowser();
        WebDriver driver = browser.getDriver();
        DriverManager.setDriver(driver);
        driver.get(XMLFileUtility.getURL());
    }

    @AfterStep
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (!scenario.isFailed()) {
            return;
        }
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            return;
        }
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
        } catch (Exception e) {
            log.error("Screenshot capture failed", e);
        }
    }

    @After(value = "@cleanup", order = 1000)
    public void deleteCreatedData(Scenario scenario) {
        if (scenario.isFailed()) {
            return;
        }
        String scenarioName = ScenarioContext.get("scenarioName");
        if (scenarioName == null || scenarioName.isEmpty()) {
            return;
        }
        try {
            deleteData(scenarioName);
            log.info("Deleted scenario data: {}", scenarioName);
        } catch (Exception e) {
            log.error("Cleanup failed for {}", scenarioName, e);
        }
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                log.error("Scenario FAILED: {}", scenario.getName());
            } else {
                log.info("Scenario PASSED: {}", scenario.getName());
            }
        } finally {
            if (browser != null) {
                browser.quitDriver();
            }
            DriverManager.clear();
        }
    }

    public void deleteData(String scenarioName) {
        DriverManager.getPages().getAdministratorPage().clickMenu("Scenarios");
        DriverManager.getPages().getScenarioPage().searchScenarioNameField(scenarioName);
        DriverManager.getPages().getScenarioPage().clickActionColumnThreeDots();
        DriverManager.getPages().getScenarioPage().getOption("Delete").click();
        DriverManager.getPages().getScenarioPage().acceptAlert();
    }
}
