package PageObjects.Locators;

public class ScenarioLocators {
    public static String CreateNewScenarioButtonByID = "createnewscenario";
    public static String SearchScenarioFieldByXpath = "//div[@class='scenarios_options-container']//div[@class='scenarios_options-search']//input[@name='SearchString']";
    public static String SearchScenarioButtonByXpath = "//button[text()='Search']";
    public static String ResetScenarioButtonByXpath = "//button[text()='Reset']";
    public static String ScenarioTable = "//table";
    public static String ScenarioTableHeadingColumns = ScenarioTable + "//thead//th";
    public static String ActionsThreeDotsByID = "dropdownMenuButton1";
    public static String ImportOptionByXpath = "//span[text()='Import']//ancestor::a";
    public static String TreatmentPoolLibraryOptionByXpath = "//span[text()='Treatment Pool Library']//ancestor::a";
    public static String SettingsOptionByXpath = "//span[text()='Settings']//ancestor::a";
    public static String AnalysisOptionByXpath = "//span[text()='Analysis']//ancestor::a";
    public static String RunOptionByXpath = "//span[text()='Run']//ancestor::button";
    public static String ExportOptionByXpath = "//span[text()='Export']//ancestor::a";
    public static String ScenarioTableRows = ScenarioTable + "//tbody//tr";
    public static String NoDataAvailableInTableByXpath = "//td[text()='No data available in table']";
    public static String ScenarioTableRowsData = ScenarioTable + "//tbody//tr//td";
    public static String ScenarioCreatedSuccessFullyToastMessageByXpath = "//*[contains(normalize-space(), 'Scenario was created successfully')]";

    public static String actionOptions(String optionName) {
        return String.format("//span[text()='%s']", optionName);
    }

    public static String ScenarioRunSuccessFullyByXpath(String scenarioName) {
        return String.format("//div[contains(@class,'toaster-body') and normalize-space() = 'Scenario %s has been run successfully.']",
                scenarioName);
    }
}
