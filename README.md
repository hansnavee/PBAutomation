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

Passwords are not stored in the repo. Emails differ by environment and every role shares one password.

Copy `src/test/resources/config/environment.xml.example` to `src/test/resources/config/environment.local.xml` (gitignored) and put each environment's emails there. Set `PB_PASSWORD` once. The sign-in URL selects the environment: a manual workflow run passes `application_url`, which is `PB_URL`, and the matching `<Environment>` supplies the emails.

| Variable | Purpose |
| --- | --- |
| `PB_URL` | Sign-in URL. Chooses the environment with the same URL |
| `PB_ENV` | `test` or `stage`, when you want to choose the environment by name |
| `PB_PASSWORD` | Shared password for every role |
| `PB_BROWSER` | `Chrome` or `Firefox` |
| `PB_OPERATOR_USERNAME` | Operator email on `test` |
| `PB_GUEST_USERNAME` | Guest email on `test` |
| `PB_ADMIN_USERNAME` | Admin email on `test` |
| `PB_INVALID_USERNAME` | Invalid email on `test` |
| `PB_STAGE_URL` | Stage sign-in URL |
| `PB_STAGE_OPERATOR_USERNAME` | Operator email on `stage` |
| `PB_STAGE_GUEST_USERNAME` | Guest email on `stage` |
| `PB_STAGE_ADMIN_USERNAME` | Admin email on `stage` |
| `PB_STAGE_INVALID_USERNAME` | Invalid email on `stage` |

A role-specific variable such as `PB_OPERATOR_PASSWORD` still overrides `PB_PASSWORD` for that role. GitHub Actions reads the same names from repository secrets. Rotate any password that was previously committed in `EnvironmentConstants.xml`.

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

Allure results are written to `target/allure-results`. Generate the HTML report with `mvn allure:report` (output under `target/site/allure-maven-plugin`) or open it with `mvn allure:serve`. The GitHub workflow uploads Allure results on every run and the HTML report after the tests finish. It then posts a summary to Google Chat through the Chat API (not an incoming webhook): total, executed, passed, failed, skipped, failed scenario titles, the workflow run link, and the Allure report link.

Add these repository secrets before the message can be sent:

| Secret | Purpose |
| --- | --- |
| `GOOGLE_CHAT_SPACE` | Space id, `spaces/AAAA...`, from the space URL |
| `GOOGLE_CHAT_CREDENTIALS` | Authorized-user JSON: `client_id`, `client_secret`, and `refresh_token` |
| `GOOGLE_CHAT_USER` | Only for a service account. Workspace user email to impersonate |

The message is sent with `https://www.googleapis.com/auth/chat.messages.create`. That scope is in Google's OAuth catalog. `https://www.googleapis.com/auth/chat.bot` is not, and the token endpoint rejects it.

In Google Cloud, enable the Google Chat API and create an OAuth client (Desktop). On the consent screen, add the scope above. If the project belongs to your Workspace domain, set the app to Internal so the refresh token stays valid. Run this once on your machine and paste the printed JSON into `GOOGLE_CHAT_CREDENTIALS`. The Google account you sign in with must already be a member of the space.

```
pip install google-auth-oauthlib
python scripts/authorize_google_chat.py path\to\client_secret.json
```

A service account can be used instead when a Workspace admin has authorized domain-wide delegation for `chat.messages.create`. Put the service-account JSON in `GOOGLE_CHAT_CREDENTIALS` and the member's email in `GOOGLE_CHAT_USER`. Do not register `chat.bot` as the delegation scope.

The run link opens the workflow run. The report link opens the `allure-report` artifact on that run.

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
