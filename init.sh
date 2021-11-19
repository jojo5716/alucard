#!/usr/bin/env bash

set -e

ENVIRONMENT_NAME="${ENVIRONMENT_NAME:-"dev"}"
JVM_OPS="${JVM_OPS:-""}"
SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE:-"prod"}"

COMMAND=${1:-"web"}
echo $COMMAND


case "$COMMAND" in
  migrate|web)
    export SPRING_DATASOURCE_URL="${DATABASE_URL}"
    ;;
  *)
    exec sh -c "$*"
    ;;
esac