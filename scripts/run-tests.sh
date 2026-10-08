#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

SUITE="smoke"
TAGS=""
HEADLESS="true"

while [[ $# -gt 0 ]]; do
  case "$1" in
    smoke|regression)
      SUITE="$1"
      shift
      ;;
    -Tags|--tags)
      TAGS="${2:-}"
      shift 2
      ;;
    -Headless|--headless)
      HEADLESS="${2:-true}"
      shift 2
      ;;
    *)
      echo "Unknown argument: $1" >&2
      echo "Usage: scripts/run-tests.sh [smoke|regression] [-Tags \"@login\"] [-Headless true|false]" >&2
      exit 1
      ;;
  esac
done

if [[ -n "$TAGS" ]]; then
  mvn test -Dheadless="$HEADLESS" -Dcucumber.tags="$TAGS"
elif [[ "$SUITE" == "regression" ]]; then
  mvn test -Pregression -Dheadless="$HEADLESS"
else
  mvn test -Psmoke -Dheadless="$HEADLESS"
fi
