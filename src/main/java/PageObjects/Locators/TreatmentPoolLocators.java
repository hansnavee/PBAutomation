package PageObjects.Locators;

public class TreatmentPoolLocators {
    public static String CreateNewTreatmentPoolLibraryButtonByID = "createnewlibrarybut";
    public static String SearchFieldByXpath = "//div[@class='candidatepools_header']//input[@name='SearchString']";
    public static String SearchButtonByXpath = "//button[text()='Search']";
    public static String ResetButtonByXpath = "//button[text()='Reset']";
    public static String Table = "//table";
    public static String TableHeadingColumns = Table + "//thead//th";
    public static String TableRows = Table + "//tbody//tr";
    public static String HeadingTextNoDataAvailableByXpath = "//div[@class='candidatepools_maincontent']/h5";
    public static String ActionsThreeDotsByID = "dropdownMenuButton1";
    public static String EditOptionByXpath = "//a[@class='dropdown-item editCandidatePool']";
    public static String CreateNewLibraryFormByXpath = "//div[@id='CreateNewLibraryForm']";
    public static String DeleteOptionByXpath = "//ul[@class='dropdown-menu show']//span[text()='Delete']//ancestor::a";
    public static String CopyOptionByXpath = "//ul[@class='dropdown-menu show']//span[text()='Copy']//ancestor::a";
    public static String ImportOptionByXpath = "//ul[@class='dropdown-menu show']//span[text()='Import']//ancestor::a";
    public static String TreatmentOptionByXpath = "//ul[@class='dropdown-menu show']//span[text()='Treatments']//ancestor::a";
    public static String TreatmentTableRowsData = Table + "//tbody//tr//td";

    public static String actionOptions(String optionName) {
        return String.format("//ul[@class='dropdown-menu show']//span[text()='%s']", optionName);
    }

    public static String TreatmentToasterMessageXpath(String toasterMessage) {
        return String.format("//*[contains(normalize-space(), '%s')]", toasterMessage);
    }
}
