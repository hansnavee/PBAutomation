package PageObjects.Pages.CreateScenarioTabs;

import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;

public class TreatmentPoolLibraryTab extends Pages {
    private final String CreateNewTreatmentPoolLibrary = ScenarioTreatmentPoolTabLocators.CreateNewTreatmentPoolLibraryButtonByID;


    public TreatmentPoolLibraryTab(WebDriver driver){
        super(driver);
    }

    public WebElement getCreateNewTreatmentPoolLibrary(){
        return findElement(LocatorsType.ByID, CreateNewTreatmentPoolLibrary);
    }

    // public List<WebElement> getTreatmentPoolLibrary(String columnName){
    //    int index = getColumnIndex(columnName) + 1;
    // return wait.untilAllElementsVisible(LocatorsType.ByXpath, (ScenarioTreatmentPoolTabLocators
    //         .tableRows + ScenarioTreatmentPoolTabLocators.tableData) + "[" + index + "]");
    // }

  public void selectTreatmentPoolLibrary(String colName, String treatmentPoolName) {

    int columnIndex = getColumnIndex(colName);
    if (columnIndex < 0) {
        throw new RuntimeException("Column not found: " + colName);
    }
    columnIndex++; // XPath is 1-based

    String rowsXpath =
        ScenarioTreatmentPoolTabLocators.tableRows;

    // ✅ wait only for PRESENCE
    wait.untilAllElementsPresent(LocatorsType.ByXpath, rowsXpath);

    int rowCount = driver.findElements(By.xpath(rowsXpath)).size();

    for (int i = 1; i <= rowCount; i++) {

        // 🔥 RE-LOCATE row EVERY iteration
        WebElement cell = driver.findElement(
            By.xpath(
                rowsXpath + "[" + i + "]//td[" + columnIndex + "]"
            )
        );

        if (cell.getText().trim().equals(treatmentPoolName)) {

            wait.untilElementClickable(LocatorsType.ByXpath, rowsXpath + "[" + i + "]//input[@type='radio']").click();
            return;
        }
    }

    throw new RuntimeException(
        "Treatment Pool not found: " + treatmentPoolName
    );
}

public List<String> getTreatmentPoolLibrary(String columnName) {

    int columnIndex = getColumnIndex(columnName);
    if (columnIndex < 0) {
        throw new RuntimeException("Column not found: " + columnName);
    }
    columnIndex++;

    String rowsXpath =
        ScenarioTreatmentPoolTabLocators.tableRows;

    wait.untilAllElementsPresent(LocatorsType.ByXpath, rowsXpath);

    int rowCount = driver.findElements(By.xpath(rowsXpath)).size();

    List<String> values = new ArrayList<>();

    for (int i = 1; i <= rowCount; i++) {
        WebElement cell = driver.findElement(
            By.xpath(
                rowsXpath + "[" + i + "]//td[" + columnIndex + "]"
            )
        );
        values.add(cell.getText().trim());
    }

    return values;
}



    public int getColumnIndex(String columnName){
        List<WebElement> headings = findElements(LocatorsType.ByXpath, ScenarioTreatmentPoolTabLocators.tableHeadings);
        List<String> columnNames = new ArrayList<>();
        for(WebElement heading : headings)
        {
            columnNames.add(heading.getText().trim());
        }
        return columnNames.indexOf(columnName);
    }
}