

# Check if version is set in environment variable APP_VERSION
if [ -z "${APP_VERSION:-}" ]; then
  echo "APP_VERSION is not set. Using default version 'latest'."
  version=latest
else
  version=${APP_VERSION}
  echo "Building with version ${APP_VERSION}"
fi

TAR=issues-handler.tar
podman save -o ${TAR} issues-handler:${version}
kind load image-archive ${TAR}

TAR=issues-mocks.tar
podman save -o ${TAR} issues-mocks:${version}
kind load image-archive ${TAR}
