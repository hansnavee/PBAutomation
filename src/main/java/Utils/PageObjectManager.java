package Utils;

import PageObjects.Pages.*;
import PageObjects.Pages.Analysis.*;
import PageObjects.Pages.Analysis.ChartsTabs.*;
import PageObjects.Pages.Analysis.Projects.*;
import PageObjects.Pages.Analysis.Reports.*;
import PageObjects.Pages.CreateScenarioTabs.*;
import PageObjects.Pages.CreateTreatmentPoolModal.CreateTreatmentModal;
import PageObjects.Pages.CreateTreatmentPoolModal.*;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver driver;

    // ===============================
    // 🔹 COMMON PAGES
    // ===============================
    private CreateScenariosPage createScenariosPage;
    private ScenarioPage scenarioPage;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private AdministratorPage administratorPage;
    private TreatmentPoolLibrariesPage treatmentPoolLibrariesPage;
    private PasswordResetPage passwordResetPage;
    private TreatmentsPage treatmentsPage;

    // ===============================
    // 🔹 CREATE SCENARIO
    // ===============================
    private LeftSideBarMenu leftSideBarMenu;
    private TreatmentPoolLibraryTab treatmentPoolLibraryTab;
    private BudgetConstraints budgetConstraints;

    // ===============================
    // 🔹 MODALS
    // ===============================
    private ExportProjectModal exportProjectModal;
    private ImportFileModal importFileModal;
    private CreateTreatmentPoolLibraryModal createTreatmentPoolLibraryModal;
    private CreateTreatmentModal createTreatmentModal;

    // ===============================
    // 🔹 ANALYSIS
    // ===============================
    private ProjectsPage projectsPage;
    private ProjectTreatmentsPage projectTreatmentsPage;
    private LeftTabMenu leftTabMenu;
    private NeedsTab needsTab;
    private ChartsTabs chartsTabs;
    private ReportsTab reportsTab;
    private MapPage mapPage;

    // ===============================
    // 🔹 CONSTRUCTOR
    // ===============================
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    // ===============================
    // 🔹 COMMON PAGES
    // ===============================
    public CreateScenariosPage getCreateScenariosPage() {
        return createScenariosPage == null
                ? createScenariosPage = new CreateScenariosPage(driver)
                : createScenariosPage;
    }

    public ScenarioPage getScenarioPage() {
        return scenarioPage == null
                ? scenarioPage = new ScenarioPage(driver)
                : scenarioPage;
    }

    public DashboardPage getDashboardPage() {
        return dashboardPage == null
                ? dashboardPage = new DashboardPage(driver)
                : dashboardPage;
    }

    public LoginPage getLoginPage() {
        return loginPage == null
                ? loginPage = new LoginPage(driver)
                : loginPage;
    }

    public AdministratorPage getAdministratorPage() {
        return administratorPage == null
                ? administratorPage = new AdministratorPage(driver)
                : administratorPage;
    }

    public TreatmentPoolLibrariesPage getTreatmentPoolLibrariesPage() {
        return treatmentPoolLibrariesPage == null
                ? treatmentPoolLibrariesPage = new TreatmentPoolLibrariesPage(driver)
                : treatmentPoolLibrariesPage;
    }

    public PasswordResetPage getPasswordResetPage() {
        return passwordResetPage == null
                ? passwordResetPage = new PasswordResetPage(driver)
                : passwordResetPage;
    }

    public TreatmentsPage getTreatmentsPage() {
        return treatmentsPage == null
                ? treatmentsPage = new TreatmentsPage(driver)
                : treatmentsPage;
    }

    // ===============================
    // 🔹 CREATE SCENARIO
    // ===============================
    public LeftSideBarMenu getLeftSideBarMenu() {
        return leftSideBarMenu == null
                ? leftSideBarMenu = new LeftSideBarMenu(driver)
                : leftSideBarMenu;
    }

    public TreatmentPoolLibraryTab getTreatmentPoolLibraryTab() {
        return treatmentPoolLibraryTab == null
                ? treatmentPoolLibraryTab = new TreatmentPoolLibraryTab(driver)
                : treatmentPoolLibraryTab;
    }

    public BudgetConstraints getBudgetConstraints() {
        return budgetConstraints == null
                ? budgetConstraints = new BudgetConstraints(driver)
                : budgetConstraints;
    }

    // ===============================
    // 🔹 MODALS
    // ===============================
    public ExportProjectModal getExportProjectModal() {
        return exportProjectModal == null
                ? exportProjectModal = new ExportProjectModal(driver)
                : exportProjectModal;
    }

    public ImportFileModal getImportFileModal() {
        return importFileModal == null
                ? importFileModal = new ImportFileModal(driver)
                : importFileModal;
    }

    public CreateTreatmentPoolLibraryModal getCreateTreatmentPoolLibraryModal() {
        return createTreatmentPoolLibraryModal == null
                ? createTreatmentPoolLibraryModal = new CreateTreatmentPoolLibraryModal(driver)
                : createTreatmentPoolLibraryModal;
    }

    public CreateTreatmentModal getCreateTreatmentModal() {
        return createTreatmentModal == null
                ? createTreatmentModal = new CreateTreatmentModal(driver)
                : createTreatmentModal;
    }

    // ===============================
    // 🔹 ANALYSIS
    // ===============================
    public ProjectsPage getProjectsPage() {
        return projectsPage == null
                ? projectsPage = new ProjectsPage(driver)
                : projectsPage;
    }

    public ProjectTreatmentsPage getProjectTreatmentsPage() {
        return projectTreatmentsPage == null
                ? projectTreatmentsPage = new ProjectTreatmentsPage(driver)
                : projectTreatmentsPage;
    }

    public LeftTabMenu getLeftTabMenu() {
        return leftTabMenu == null
                ? leftTabMenu = new LeftTabMenu(driver)
                : leftTabMenu;
    }

    public NeedsTab getNeedsTab() {
        return needsTab == null
                ? needsTab = new NeedsTab(driver)
                : needsTab;
    }

    public ChartsTabs getChartsTabs() {
        return chartsTabs == null
                ? chartsTabs = new ChartsTabs(driver)
                : chartsTabs;
    }

    public ReportsTab getReportsTab() {
        return reportsTab == null
                ? reportsTab = new ReportsTab(driver)
                : reportsTab;
    }

    public MapPage getMapPage() {
        return mapPage == null
                ? mapPage = new MapPage(driver)
                : mapPage;
    }
}
