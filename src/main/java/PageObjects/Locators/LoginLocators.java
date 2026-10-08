package PageObjects.Locators;

public class LoginLocators {
    public static String UsernameOrEmailByName = "email";
    public static String PasswordByID = "password";
    public static String SignInByXpath = "//button[@id='next']";
    public static String ForgotYourPasswordLinkByXpath = "//a[@id='forgotPassword']";
    public static String EmailFieldValidationByXpath = "//label[@for='email']/following-sibling::div/p";
    public static String PasswordFieldValidationByXpath = "//label[@for='password']/ancestor::div[@class='password-label']//following-sibling::div/p";
    public static String ValidationErrorMessageByXpath = "//div[@class='error pageLevel']/p";
}
