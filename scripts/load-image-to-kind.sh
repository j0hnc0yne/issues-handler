TAR=issues-handler.tar
podman save -o ${TAR} issues-handler:latest
kind load image-archive ${TAR}

TAR=issues-mocks.tar
podman save -o ${TAR} issues-mocks:latest
kind load image-archive ${TAR}
