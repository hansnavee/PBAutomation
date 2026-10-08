package PageObjects.Locators;

public class BudgetConstraintsLocators {
    public static String BudgetConstraintDefaultMessageByXpath = "//div[@class='card-body']/div";
    public static String BudgetConstraintSuccessMessageByXpath = "//div[@class='alert alert-success alert-dismissible fade show']";
    public static String DownloadButtonByXpath = "//a[text()='Download Template']";
    public static String UploadBudgetConstraintsFileByName = "file";
    public static String ImportBudgetButtonByXpath = "//button[text()='Import Budget']";
    public static String ImportButtonByXpath = "//button[text()='Import']";
    public static String ImportBudgetConstraintNoteByXpath = "//input[@name='file']//following::p";
}
