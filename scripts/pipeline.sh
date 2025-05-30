
# Step 1 - Build and unit test
./gradlew clean candidate > build.log 2>&1

# Extract version from build.log
export APP_VERSION=$(grep 'version:' build.log | awk -F'version: ' '{print $2}')
echo "APP_VERSION: $APP_VERSION"

# Step 2 - Build Container Image
./scripts/build-app-image.sh

# Step 3- Build Mock Image
./scripts/build-mock-image.sh

# Step 4 - Deploy to Kubernetes
./scripts/load-image-to-kind.sh
envsubst < k8s/deployment.yaml | kubectl apply -f -

# Step 5 - Run Integration Tests
nohup kubectl -n projectcontour port-forward service/envoy 8888:80 &
export BASE_URL=http://issues-handler.local:8888
./gradlew integrationTest
kill -9 $(ps -ef | grep 'port-forward' | grep -v grep | awk '{print $2}')
