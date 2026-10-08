package PageObjects.Locators;

public class DashboardLocators {
    public static String SkipButtonByXPATH = "//div[@class='contact-box maintextdiv skipBtn']";
    public static String ContactUsButtonByXPATH = "//div[@class='contact-box maintextdiv contactBtn']";

    public static String applicationNamesXpath(String applicationName) {
        return String.format("//a[normalize-space(text())='%s']", applicationName);
    }
}
