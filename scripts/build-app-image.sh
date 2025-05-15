#!/bin/bash
set -euo pipefail
# Build mock image using podman
# Usage: ./scripts/build-mock-image.sh

image_name=issues-handler

# Check if version is set in environment variable APP_VERSION
if [ -z "${APP_VERSION:-}" ]; then
  echo "APP_VERSION is not set. Using default version 'latest'."
  version=latest
else
  version=${APP_VERSION}
  echo "Building with version ${APP_VERSION}"
fi

podman build --tag=${image_name}:${version} --file=./Dockerfile .
#  --build-arg=GIT_COMMIT=$(git rev-parse HEAD) \
#  --build-arg=GIT_BRANCH=$(git rev-parse --abbrev-ref HEAD) \
#  --build-arg=GIT_TAG=$(git describe --tags --abbrev=0) \


#podman run -p 8080:8080 -e GITHUB_TOKEN=1234 -e GITHUB_BASEURL=http://localhost:9080 issues-handler:latest
