package PageObjects.Locators;

public class AdministratorLocators {
    public static String menuLinkXpath(String linkText) {
        return String.format("//span[normalize-space(text())='%s']", linkText);
    }
}
