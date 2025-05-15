# issuers-handler

Demo Project to show mocking dependencies with Wiremock to use in Narrow integration testing.



The following will setup a KIND cluster using Podman as the container runtime for running the cluster.

```bash
export KIND_EXPERIMENTAL_PROVIDER=podman
kind create cluster --name my-cluster
kubectl cluster-info --context kind-my-cluster
export KIND_CLUSTER_NAME=my-cluster
```

Create a new namespace for the application to run in.

```bash
kubectl create namespace my-system
```
Deploy the application to the cluster.

```bash
kubectl apply -f k8s/deployment.yaml
```
Check the status of the deployment.

```bash
kubectl get pods
```
