package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitializeBrowser {

    private WebDriver driver;

    public WebDriver getDriver() {
        return getDriver(XMLFileUtility.getBrowserName());
    }

    public WebDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(BrowserHelpers.chromeOptions());
                break;
            case "firefox":
                driver = new FirefoxDriver(BrowserHelpers.firefoxOptions());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
