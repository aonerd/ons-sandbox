ONS Sandbox - Opal POC
--------


## Opal Client Operations

---

## Features

1. **User Access Verification**  
   This service communicates with an external Opal API using REST to check whether a user has permission to perform a
   requested action on a specific object and type.

2. **User Management**  
   This service provides an API to manage users, including creating users.

---

## Opal Client API Operations

1. The `checkUserAccess` method is the main function provided by this microservice.
2. 
2. It takes the following parameters:
    - `user`: The username or identifier of the user requesting access.
    - `action`: The action the user wants to perform (e.g., "read", "write").
    - `object`: The target object on which the action will be performed (e.g., "file", "resource").
    - `type`: The type of the target object (e.g., "document", "API").
   
3. Constructs a `POST` HTTP request to the RBAC API endpoint at `/v1/data/app/rbac/allow`.

4. Sends the request with appropriate headers (e.g., JSON content type) and the body containing the `AccessRequest`
   data.

5. Returns `true` if the API permits the action; otherwise, it returns `false`. In case of exceptions (e.g., HTTP
   errors), it also returns `false` by default.


## Configuration

- **Base URL of Opal API**: `http://localhost:8181`  
  This is configured statically, but it can be modified based on the deployment environment.

---


## UserController API Documentation

### Endpoints

1. **GET /v1/users**
   - Description: Retrieves all users.
   - Response: Returns a `DataResponse` object containing user data.

2. **POST /v1/users**
   - Description: Adds a new user.
   - Request Body: A `User` object containing user details.
   - Response: Returns a `DataResponse` object containing the updated user data.

3. **GET /v1/users/{username}/accounts/{accountId}**
   - Description: Retrieves account information for a specific user and account.
   - Path Variables:
     - `username`: The username of the user.
     - `accountId`: The ID of the account.
   - Response: Returns an `Account` object if the user is authorized to access the account; otherwise, returns an unauthorized response.

---


---


## OPAL Resources

| Pod/Resource | Description                   | Documentation                                                                                            | Repo                                                                                                                         |
|--------------|-------------------------------|----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| opal-server  | Opal Server/Orchestrator      | [Opal Server](https://docs.opal.ac/getting-started/running-opal/run-opal-server/get-server-image)        | [Helm](https://github.com/aonerd/ons-opa-poc/tree/main/helm) [Opal Repo](https://github.com/permitio/opal-helm-chart)        |
| opal-client  | Opal Client                   | [Client](https://docs.opal.ac/getting-started/running-opal/run-opal-client/get-client-image)             | ^^                                                                                                                           |
| opal-pgsql   | Backbone                      | [PSQL Broadcast] (https://docs.opal.ac/getting-started/running-opal/run-opal-server/broadcast-interface) | ^^                                                                                                                           |
| ons-sandbox  | Test microservice (micronaut) | [README.md] (https://github.com/aonerd/ons-sandbox/blob/opal-spike-1/README.md)                          | [Git](https://github.com/aonerd/ons-sandbox/tree/opal-spike-1)                                                               |
| Opal K8s     | Opal K8s setup                | [Tutoral](https://docs.opal.ac/tutorials/helm-chart-for-kubernetes)                                      | -                                                                                                                            |
| -            | --                            | ---                                                                                                      | --                                                                                                                           |
| rbac.rego    | Test microservice (micronaut) | [Play ground](https://play.openpolicyagent.org/p/5Jt9gKuczC)                                             | [Git](https://github.com/aonerd/ons-opa-poc/blob/main/rbac.rego) [OPA](https://github.com/permitio/opal-example-policy-repo) |



### Run book:


#### Deploy Test Microservice
Clone and run from: https://github.com/aonerd/ons-sandbox/tree/opal-spike-1


Create namespace
```shell
kubectl create ns opal-ns
kubens opal-ns
```

Deploy test microservice namespace
```shell
ons-sandbox on  opal-spike-1 [$!] via ☕ v21.0.3 on ☁️  eu-west-2
❯ ./build-local.sh
deployment.apps "ons-sandbox" deleted
service "ons-sandbox" deleted
...
BUILD SUCCESSFUL in 5s
7 actionable tasks: 7 executed
deployment.apps/ons-sandbox created
service/ons-sandbox created

❯ stern -n opal-ns ons-sandbox

+ ons-sandbox-7489775578-lk6b9 › ons-sandbox
ons-sandbox-7489775578-lk6b9 ons-sandbox  __  __ _                                  _
ons-sandbox-7489775578-lk6b9 ons-sandbox |  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_
ons-sandbox-7489775578-lk6b9 ons-sandbox | |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
ons-sandbox-7489775578-lk6b9 ons-sandbox | |  | | | (__| | | (_) | | | | (_| | |_| | |_
ons-sandbox-7489775578-lk6b9 ons-sandbox |_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
ons-sandbox-7489775578-lk6b9 ons-sandbox 13:26:01.748 [main] INFO  i.m.c.DefaultApplicationContext$RuntimeConfiguredEnvironment - Established active environments: [k8s, cloud, cv-k8s]
ons-sandbox-7489775578-lk6b9 ons-sandbox 13:26:01.984 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 306ms. Server Running: http://ons-sandbox-7489775578-lk6b9:80

```

Port forward to test microservice
```shell
~ on ☁️  eu-west-2 took 2h27m15s
❯ kubectl port-forward svc/ons-sandbox 8081:80 &
[1] 60435

~ on ☁️  eu-west-2
✦ ❯ Forwarding from 127.0.0.1:8081 -> 80
Forwarding from [::1]:8081 -> 80
Handling connection for 8081


```

Test the test microservice
```shell
✦ ❯ curl --request GET --url http://localhost:8081/v1/users  | jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0Handling connection for 8081
100  1126  100  1126    0     0  97955      0 --:--:-- --:--:-- --:--:--   99k
{
  "users": {
    "sunil": {
      "name": "Sunil",
      "roles": [
        "guest"
      ]
```


#### Deploy Opal
Clone and run from: https://github.com/aonerd/ons-opa-poc

```shell
ons-opa-poc on  main on ☁️  eu-west-2

❯ kubens opal-ns

❯ cd helm

❯ helm install -f values.yaml -n opal-ns opal .


❯ kubeclt get pods
NAME                           READY   STATUS    RESTARTS   AGE
ons-sandbox-7489775578-lk6b9   1/1     Running   0          5m30s
opal-client-6b7b5c7568-rjnx8   1/1     Running   0          59m
opal-pgsql-56d8998595-kxttd    1/1     Running   0          68m
opal-server-67985568fb-fg5qn   1/1     Running   0          60m

```
**Note if the client fails to start bounce as it can be a timing issue**


On Startup the `opal-server` will 

> Load the `rbac.rego` policy from git (and check for updates)

Based on the config:
https://github.com/aonerd/ons-opa-poc/blob/main/helm/values.yaml#L16
```
    policyRepoUrl: "https://github.com/aonerd/ons-opa-poc"
```

> Load the data from a external source (Note: Test microservice exposing api to do this)

```yaml
    config:
      entries:
      - url: "http://ons-sandbox/v1/users"
        topics: ["policy_data"]
        periodic_update_interval: 30
                
```

The test microservice has Pre-Canned data already as per:
https://github.com/aonerd/ons-sandbox/blob/opal-spike-1/src/main/java/com/ons/user/util/DataResponseUtil.java

Which aligns to what Opal provided as part of the tutorial only accounts are added:
https://github.com/permitio/opal-example-policy-repo/blob/master/rbac.rego


The server distributes this data to the OPA Client's.    
The current design the test microservice calls the OPA Client to request permission to access data (account data) for a user.

#### Testing Opal

Port forward to Microservice
```shell
kubectl port-forward svc/ons-sandbox 8081:80 
```



Get Alice's account "Account2"  
This will perform a Opal client check to see if Alice has access to Account2 if so it will return the account
```shell


❯ curl --request GET \
  --url http://localhost:8081/v1/users/alice/accounts/account2 \
  --header 'Content-Type: application/json'

{"accountId":"account2","accountNumber":"87654321","sortCode":"65-43-21"}%
  
```

Check if Pete has access (which he Shouldn't) to "Account2" (expect 401):
```shell

❯ curl -v --request GET \
  --url http://localhost:8081/v1/users/pete/accounts/account2 \
  --header 'Content-Type: application/json'

>
* Request completely sent off
< HTTP/1.1 401 Unauthorized
< date: Thu, 20 Feb 2025 13:47:13 GMT
< content-length: 0

```
Give Pete access to "Account2":

```shell
curl --request POST \
  --url http://localhost:8081/v1/users \
  --header 'Content-Type: application/json' \
  --data '{
  "name": "pete",
  "roles": ["admin"],
  "location": {
    "country": "US",
    "ip": "8.8.8.8"
  },
	"accountIds": [
				"account2"
			]
}'
```

Retru Check if Pete has access to "Account2" (expect 200):
**Note the Server polls for data every 30 seconds so it may take a few seconds to update**
```shell

opal-example-policy-repo on  master on ☁️  eu-west-2
❯ curl -v --request GET \
  --url http://localhost:8081/v1/users/pete/accounts/account2 \
  --header 'Content-Type: application/json'

...

* Request completely sent off
< HTTP/1.1 200 OK
< date: Thu, 20 Feb 2025 14:41:28 GMT
< content-type: application/json
< content-length: 73
<
* Connection #0 to host localhost left intact
{"accountId":"account2","accountNumber":"87654321","sortCode":"65-43-21"}%

```
