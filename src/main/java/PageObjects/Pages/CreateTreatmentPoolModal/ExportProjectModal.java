package PageObjects.Pages.CreateTreatmentPoolModal;

import Driver.Driver;
import PageObjects.Locators.*;
import PageObjects.Pages.Pages;
import PageObjects.models.LocatorsType;
import Utils.FileUtility;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Comparator;
import java.util.Optional;

public class ExportProjectModal extends Pages {

    private final String ExportButton = ProjectExportLocators.ExportButtonByXpath;
    private final String CancelButton = ProjectExportLocators.CancelButtonByXpath;
    private final String treatmentTypeDropdown = ProjectExportLocators.treatmentTypeDropdowntByID;
    String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";

    public ExportProjectModal(WebDriver driver) {
        super(driver);
    }


    public void selectFileType(String type) {
        WebElement element = wait.untilElementClickable(LocatorsType.ByID, treatmentTypeDropdown);
        Select select = new Select(element);
        select.selectByVisibleText(type);
    }

    public void clickExportButton() {
        FileUtility.cleanDownloadFolder(downloadDir);

        WebElement element = wait.untilElementClickable(LocatorsType.ByXpath, ExportButton);
        element.click();

        File downloadedFile = FileUtility.waitForNewDownloadedFile(downloadDir, 60);

        if (downloadedFile == null) {
            throw new AssertionError("❌ XLSX file was not downloaded");
        }

        System.out.println("Downloaded File = " + downloadedFile.getAbsolutePath());
    }


    public void clickCancelButton(){
        clickElement(LocatorsType.ByXpath, CancelButton);
    }

    public boolean isFileDownloaded() {
        long startTime = System.currentTimeMillis();
        return FileUtility.waitForNewDownloadedFile(downloadDir, 30).exists();
    }

}
