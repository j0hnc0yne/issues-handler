export GITHUB_BASEURL=http://localhost:9080

nohup ./gradlew mockServer &

./gradlew bootRun

# Issue graceful shutdown to wiremock server
curl -X POST http://localhost:9080/__admin/shutdown
