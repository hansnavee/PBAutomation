#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

MODE="${1:-serve}"

case "$MODE" in
  serve)
    mvn -B allure:serve
    ;;
  report)
    mvn -B allure:report
    ;;
  *)
    echo "Usage: scripts/allure-report.sh [serve|report]" >&2
    exit 1
    ;;
esac
