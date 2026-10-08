package PageObjects.Pages.Analysis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;

public class LeftTabMenu extends Pages{

    public static final String Charts = LeftTabMenuLocators.ChartsByXpath;
    public static final String Reports = LeftTabMenuLocators.ReportsByXpath;
    public static final String Map = LeftTabMenuLocators.MapByXpath;

    public LeftTabMenu(WebDriver driver) {
        super(driver);
    }

    public WebElement getCharts() {
        return wait.untilElementClickable(LocatorsType.ByXpath, Charts);
    }

     public WebElement getReports() {
        return wait.untilElementClickable(LocatorsType.ByXpath, Reports);
    }

      public void clickOnMap() {
        wait.untilElementClickable(LocatorsType.ByXpath, Map).click();
    }

    public WebElement getChartsSubMenu(String menuName) {
        return wait.untilElementClickable(LocatorsType.ByXpath, LeftTabMenuLocators.chartSubMenuLinkXpath(menuName));
    }

    public WebElement getReportsSubMenu(String menuName) {
        return wait.untilElementClickable(LocatorsType.ByXpath, LeftTabMenuLocators.reportsSubMenuLinkXpath(menuName));
    }

    public void openChartsSubMenu(String menuName) {
        WebElement charts = getCharts();
        if (String.valueOf(charts.getAttribute("class")).contains("collapsed")) {
            charts.click();
        }
        getChartsSubMenu(menuName).click();
    }

    public void openReportsSubMenu(String menuName) {
        WebElement reports = getReports();
        if (String.valueOf(reports.getAttribute("class")).contains("collapsed")) {
            reports.click();
        }
        getReportsSubMenu(menuName).click();
    }
}
