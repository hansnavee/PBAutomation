package PageObjects.Locators;

public class CreateTreatmentLocators {
    public static String CreateTreatmentModalLabelByID = "createtreatmentlabel";
    public static String CancelButtonByXpath = "//button[text()='Cancel']";
    public static String CloseIconByXpath = "//h5[@id='createtreatmentlabel']//following-sibling::button[@aria-label='Close']";
    public static String TreatmentFieldNameByCssSelector = "input#treatment";
    public static String ConstructionCostByCssSelector = "input#cost";
    public static String IndirectCostUtilitiesByCssSelector = "input#indirectcostutilities";
    public static String IndirectCostDesignByCssSelector = "input#indirectcostdesign";
    public static String IndirectCostRowByCssSelector = "input#indirectcostrow";
    public static String IndirectCostOthersByCssSelector = "input#indirectcostothers";
    public static String RiskByCssSelector = "input#risk";
    public static String CreateTreatmentButtonByXpath = "//button[@id='createtrtBtn']";
    public static String MinYearByCssSelector = "input#minyear";
    public static String MaxYearByCssSelector = "input#maxyear";
    public static String AssetTypeSelectByCssSelector = "select#assettype";
    public static String TreatmentTypeSelectByCssSelector = "select#treatmenttype";
    public static String PriorityOrderSelectByCssSelector = "select#pariorityorder";
    public static String PreferredYearSelectByCssSelector = "select#preferredyear";
    public static String DistrictSelectByCssSelector = "form#createtreatment select#district";
    public static String CountySelectByCssSelector = "form#createtreatment select#county";
    public static String RouteSelectByCssSelector = "form#createtreatment select#route";
    public static String SectionSelectByCssSelector = "form#createtreatment select#section";
}
