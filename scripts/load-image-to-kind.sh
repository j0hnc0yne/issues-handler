#!/bin/bash

export KIND_EXPERIMENTAL_PROVIDER=podman
export KIND_CLUSTER_NAME=a-kind-cluster

# Check if version is set in environment variable APP_VERSION
if [ -z "${APP_VERSION:-}" ]; then
  echo "APP_VERSION is not set. Using default version 'latest'."
  version=latest
else
  version=${APP_VERSION}
  echo "Using version to save tars: ${APP_VERSION}"
fi

TAR=issues-handler.tar
echo "Saving issues-handler image as ${TAR}"
podman save -o ${TAR} issues-handler:${version}
echo "Loading issues-handler image into kind cluster"
kind load image-archive ${TAR}

TAR=issues-mocks.tar
echo "Saving issues-mocks image as ${TAR}"
podman save -o ${TAR} issues-mocks:${version}
echo "Loading issues-mocks image into kind cluster"
kind load image-archive ${TAR}
