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


