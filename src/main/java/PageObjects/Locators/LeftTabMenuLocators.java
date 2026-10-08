package PageObjects.Locators;

public class LeftTabMenuLocators {
    public static String ChartsByXpath = "//span[text()='Charts']//ancestor::button";
    public static String ReportsByXpath = "//span[text()='Reports']//ancestor::button";
    public static String NoDataAvailableByXpath = "//td[@class='dataTables_empty']";
    public static String MapByXpath = "//span[text()='Map']//ancestor::a";

    public static String chartSubMenuLinkXpath(String linkText) {
        return String.format("//div[@id='charts-collapsable']//ul//li//span[text()='%s']//ancestor::a", linkText);
    }

    public static String reportsSubMenuLinkXpath(String linkText) {
        return String.format("//div[@id='reports-collapsable']//ul//li//span[text()='%s']//ancestor::a", linkText);
    }
}
