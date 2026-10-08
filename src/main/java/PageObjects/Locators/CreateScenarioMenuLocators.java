package PageObjects.Locators;

public class CreateScenarioMenuLocators {
    public static String LinkXpath(String menuName) {
        return String.format("//nav[@id='sidebarMenu']//li//span[text()='%s']//ancestor::a", menuName);
    }
}
