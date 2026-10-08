package Utils;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<PageObjectManager> PAGES = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
        PAGES.set(new PageObjectManager(driver));
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static PageObjectManager getPages() {
        PageObjectManager pages = PAGES.get();
        if (pages == null) {
            throw new IllegalStateException("Browser is not started. PBHooks did not initialize the driver.");
        }
        return pages;
    }

    public static void clear() {
        PAGES.remove();
        DRIVER.remove();
    }
}
