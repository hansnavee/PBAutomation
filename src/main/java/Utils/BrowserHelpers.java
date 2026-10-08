package Utils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.HashMap;

public class BrowserHelpers {

    public static ChromeOptions chromeOptions(){
        // Define download folder inside project
        String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";


        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadPath);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        chromePrefs.put("safebrowsing.enabled", true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--safebrowsing-disable-download-protection");
        chromeOptions.addArguments("--disable-popup-blocking");
        if (headless()) {
            chromeOptions.addArguments("--headless=new");
        }
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--force-device-scale-factor=1");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-gpu");
        return chromeOptions;
    }

    public static FirefoxOptions firefoxOptions(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--incognito");
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-popup-blocking");
        if (headless()) {
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;
    }

    private static boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }
}
