package PageObjects.Locators;

public class TreatmentPoolModalLocators {
    public static String TreatmentLibraryNameFieldByName = "libraryname";
    public static String TreatmentLibraryEditFormNameFieldByXpath = "//form[@id='editlibrary']//input[@name='libraryname']";
    public static String TreatmentLibraryDescriptionFieldByName = "librarydescription";
    public static String TreatmentLibraryEditFormDescriptionFieldByXpath = "//form[@id='editlibrary']//input[@name='librarydescription']";
    public static String CreateButtonByXpath = "//button[text()='Create']";
    public static String CancelButtonByXpath = "//div[@class='modal-footer']/button[text()='Create']/preceding-sibling::button";
    public static String CancelButtonFromEditFormByXpath = "//form[@id='editlibrary']//button[text()='Cancel']";
    public static String CloseIconByXpath = "//h5[@id='CreateNewLibraryFormlabel']/following-sibling::button";
    public static String CloseIconEditFormByXpath = "//div[@id='EditLibraryForm']//h5[@id='editlibraryheader']/following-sibling::button";
    public static String UpdateByXpath = "//button[text()='Update']";
    public static String DescriptionFieldValidationMessageByID = "descriptionfeedback";
    public static String NameFieldValidationMessageByID = "namefeedback";
    public static String SharedToggleButtonByXpath = "//form[@id='createlibraryModal']//input[@id='isshared']";
    public static String SharedLabelByXpath = "//form[@id='createlibraryModal']//label[@for='isshared']";
    public static String CreateNewLibraryFormLabelByID = "CreateNewLibraryFormlabel";
    public static String EditTreatmentPoolLibraryFormLabelByXpath = "//h5[text()='Edit Treatment Pool Library']";
}
