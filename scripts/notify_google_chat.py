"""Post a test-run summary to Google Chat using the Chat API.

This does not use an incoming webhook. The caller authenticates as a
Google user with the catalogued scope
https://www.googleapis.com/auth/chat.messages.create.
"""

import glob
import json
import os
import sys

RESULTS_DIR = os.environ.get("ALLURE_RESULTS", "target/allure-results")
MAX_FAILURES = 25
CHAT_SCOPE = "https://www.googleapis.com/auth/chat.messages.create"
TOKEN_URL = "https://oauth2.googleapis.com/token"


def load_results():
    paths = glob.glob(os.path.join(RESULTS_DIR, "*-result.json"))
    results = []
    for path in paths:
        with open(path, encoding="utf-8") as handle:
            results.append(json.load(handle))
    return results


def scenario_title(result):
    name = (result.get("name") or "Unnamed scenario").strip()
    parameters = result.get("parameters") or []
    values = []
    for parameter in parameters:
        value = str(parameter.get("value") or "").strip()
        if value:
            label = str(parameter.get("name") or "").strip()
            values.append(f"{label}={value}" if label else value)
    if values:
        return f"{name} ({', '.join(values)})"
    return name


def summarize(results):
    passed = failed = skipped = 0
    failed_titles = []
    for result in results:
        status = (result.get("status") or "unknown").lower()
        if status == "passed":
            passed += 1
        elif status == "skipped":
            skipped += 1
        else:
            failed += 1
            failed_titles.append(scenario_title(result))
    total = len(results)
    executed = total - skipped
    return {
        "total": total,
        "executed": executed,
        "passed": passed,
        "failed": failed,
        "skipped": skipped,
        "failed_titles": failed_titles,
    }


def summary_text(summary):
    lines = [
        f"Total: {summary['total']}",
        f"Executed: {summary['executed']}",
        f"Passed: {summary['passed']}",
        f"Failed: {summary['failed']}",
        f"Skipped: {summary['skipped']}",
    ]
    titles = summary["failed_titles"]
    if titles:
        lines.append("")
        lines.append("Failed scenarios:")
        shown = titles[:MAX_FAILURES]
        for index, title in enumerate(shown, start=1):
            lines.append(f"{index}. {title}")
        remaining = len(titles) - len(shown)
        if remaining:
            lines.append(f"...and {remaining} more")
    return "\n".join(lines)


def chat_message(summary):
    outcome = "FAILED" if summary["failed"] else "PASSED"
    event = os.environ.get("RUN_EVENT", "workflow")
    suite = os.environ.get("SUITE_NAME", "Cucumber")
    lines = [f"{suite} {outcome} ({event})", "", summary_text(summary)]
    run_url = os.environ.get("RUN_URL", "").strip()
    report_url = os.environ.get("REPORT_URL", "").strip().rstrip("/")
    if report_url.endswith("/artifacts"):
        report_url = ""
    if run_url:
        lines.extend(["", f"Workflow run: {run_url}"])
    if report_url:
        lines.append(f"Allure report: {report_url}")
    return {"text": "\n".join(lines)}


def space_name():
    space = os.environ.get("GOOGLE_CHAT_SPACE", "").strip()
    if not space:
        return ""
    if not space.startswith("spaces/"):
        space = "spaces/" + space
    return space


def load_credentials():
    raw = os.environ.get("GOOGLE_CHAT_CREDENTIALS", "").strip()
    if not raw:
        return None
    info = json.loads(raw)
    if not isinstance(info, dict):
        raise RuntimeError("GOOGLE_CHAT_CREDENTIALS must be a JSON object")
    return info


def access_token(info):
    if info.get("refresh_token") and info.get("client_id"):
        return user_access_token(info)
    if info.get("type") == "service_account" or info.get("private_key"):
        return delegated_access_token(info)
    raise RuntimeError(
        "GOOGLE_CHAT_CREDENTIALS must be an authorized-user JSON "
        "(client_id, client_secret, refresh_token) created with "
        f"{CHAT_SCOPE}."
    )


def user_access_token(info):
    import requests

    response = requests.post(
        info.get("token_uri") or TOKEN_URL,
        data={
            "grant_type": "refresh_token",
            "client_id": info["client_id"],
            "client_secret": info.get("client_secret", ""),
            "refresh_token": info["refresh_token"],
        },
        timeout=30,
    )
    if response.status_code >= 300:
        raise RuntimeError(f"Google token endpoint returned {response.status_code}: {response.text}")
    token = response.json().get("access_token")
    if not token:
        raise RuntimeError("Google token response did not include an access token")
    return token


def delegated_access_token(info):
    import google.auth.transport.requests
    from google.oauth2 import service_account

    subject = os.environ.get("GOOGLE_CHAT_USER", "").strip()
    if not subject:
        raise RuntimeError(
            "chat.bot is not a catalogued OAuth scope, so Google rejects it. "
            f"Authorize domain-wide delegation for {CHAT_SCOPE} and set "
            "GOOGLE_CHAT_USER to a Workspace user who is a member of the space. "
            "Or replace GOOGLE_CHAT_CREDENTIALS with a user refresh token."
        )
    credentials = service_account.Credentials.from_service_account_info(
        info, scopes=[CHAT_SCOPE], subject=subject
    )
    credentials.refresh(google.auth.transport.requests.Request())
    return credentials.token


def send(message, info):
    import requests

    space = space_name()
    token = access_token(info)
    response = requests.post(
        f"https://chat.googleapis.com/v1/{space}/messages",
        headers={"Authorization": f"Bearer {token}", "Content-Type": "application/json"},
        json=message,
        timeout=30,
    )
    if response.status_code >= 300:
        raise RuntimeError(f"Google Chat API returned {response.status_code}: {response.text}")


def main():
    summary = summarize(load_results())
    print(summary_text(summary))
    info = load_credentials()
    if not space_name() or not info:
        print("Google Chat is not configured. Skipping notification.")
        return 0
    send(chat_message(summary), info)
    print("Google Chat notification sent.")
    return 0


if __name__ == "__main__":
    try:
        sys.exit(main())
    except Exception as error:
        print(f"Google Chat notification failed: {error}", file=sys.stderr)
        sys.exit(1)
