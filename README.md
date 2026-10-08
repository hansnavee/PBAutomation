# PBAutomation

Cucumber 7 and Selenium 4 tests for PB Web. Java 17. ChromeDriver is resolved by Selenium Manager when Chrome is installed.

## Layout

- `src/main/java/Driver` — WebDriver wrappers and waits
- `src/main/java/PageObjects` — pages and per-screen locators
- `src/main/java/Utils` — browser, config, page manager, scenario context
- `src/test/java/Hooks` — `PBHooks` and `TestRunner`
- `src/test/java/stepDefinition` — step definitions
- `src/test/resources/Feature` — feature files
- `src/test/resources/config` — environment config (no secrets in git)
- `src/test/resources/testdata` — import workbooks, when present
- `scripts` — local run and Allure helpers
- `.github/workflows/cucumber.yml` — pull-request smoke and manual tag runs

## Credentials

Passwords are not stored in the repo. Set environment variables, or copy `src/test/resources/config/environment.xml.example` to `src/test/resources/config/environment.local.xml` (gitignored) and fill it in.

| Variable | Purpose |
| --- | --- |
| `PB_URL` | Sign-in URL |
| `PB_BROWSER` | `Chrome` or `Firefox` |
| `PB_OPERATOR_USERNAME` / `PB_OPERATOR_PASSWORD` | Operator User |
| `PB_GUEST_USERNAME` / `PB_GUEST_PASSWORD` | Guest User |
| `PB_ADMIN_USERNAME` / `PB_ADMIN_PASSWORD` | Admin User |
| `PB_INVALID_USERNAME` / `PB_INVALID_PASSWORD` | Invalid User |

Environment variables override the XML file. Rotate any password that was previously committed in `EnvironmentConstants.xml`.

GitHub Actions reads the same names from repository secrets.

## Run tests

From the repo root:

```text
mvn test -Psmoke
mvn test -Pregression
mvn test -Dcucumber.tags="@login"
```

Headless is on by default. For a visible browser:

```text
mvn test -Psmoke -Dheadless=false
```

Windows:

```text
.\scripts\run-tests.ps1
.\scripts\run-tests.ps1 -Suite regression
.\scripts\run-tests.ps1 -Tags "@login" -Headless $false
.\scripts\allure-report.ps1
.\scripts\allure-report.ps1 -Mode report
```

Linux or macOS:

```text
./scripts/run-tests.sh smoke
./scripts/run-tests.sh regression
./scripts/run-tests.sh -Tags "@login" -Headless false
./scripts/allure-report.sh serve
./scripts/allure-report.sh report
```

`mvn test` with no tag runs every scenario. Pull requests on GitHub run `@smoke` only. The **Cucumber** workflow also runs `@regression` every day at 07:00 IST. A manual run asks for a tag expression and an application sign-in URL. That URL is passed to the tests as `PB_URL`. Scheduled and pull-request runs keep using the `PB_URL` repository secret.

## Tags

- `@smoke` — short path used by pull-request CI
- `@regression` — full suite, daily at 07:00 IST and manual workflow
- `@login`, `@scenarios`, `@treatment_pool`, `@password_reset` — domain filters
- `@cleanup` — scenario creates data that is deleted after a pass

## Reports

Allure results are written to `target/allure-results`. Generate the HTML report with `mvn allure:report` (output under `target/site/allure-maven-plugin`) or open it with `mvn allure:serve`. The GitHub workflow uploads results on every run and the HTML report when a run fails or is started manually.

## Import workbooks

These files are loaded from `src/test/resources/testdata/` and are not in the repo:

- `BAMS_TestData.xlsx`
- `PAMS_TestData.xlsx`
- `BAMS_TestData_With_Multiple_Tabs.xlsx`
- `PAMS_TestData_With_Multiple_Tabs.xlsx`
- `Budget_Constraints_TestData.xlsx`

Scenarios that import those files are not tagged `@smoke`. Add the real workbooks before running them.

## Notes

- A new browser starts for each scenario.
- Screenshots are attached to failed steps.
- Do not commit `environment.local.xml`, `target/`, `logs/`, or `downloads/`.
