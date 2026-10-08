package PageObjects.Pages;

import PageObjects.Locators.*;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdministratorPage extends Pages {

    public AdministratorPage(WebDriver driver) {
        super(driver);
    }

    public WebElement menuItem(String linkName){
            wait.untilElementVisible(LocatorsType.ByXpath, AdministratorLocators.menuLinkXpath(linkName));
            WebElement element =  wait.untilElementClickable(LocatorsType.ByXpath, AdministratorLocators.menuLinkXpath(linkName));
            try {
             return element;
            }catch (Exception e){
              e.printStackTrace();
            }
            return element;
    }

    public void clickMenu(String menuText) {
        clickElement(menuItem(menuText));
    }

}
