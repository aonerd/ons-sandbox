#!/bin/bash

COGNITO_REGION="eu-west-3"
COGNITO_POOL_ID="eu-west-3_d1dvijN15"
CLIENT_ID="35415ghqua56oikie5nfo8m14n"
CLIENT_SECRET="1nsirtdi4j96j6hqmrcb1alvo2j6b3elagbocca9lunfg8bgjnh3"
USERNAME="bob"
PASSWORD="PetStore1234"

AUTH_URL="https://cognito-idp.${COGNITO_REGION}.amazonaws.com/"

# Calculate SECRET_HASH
SECRET_HASH=$(echo -n "${USERNAME}${CLIENT_ID}" | openssl dgst -sha256 -hmac "${CLIENT_SECRET}" -binary | base64)

# Echo the whole command before executing
echo "curl -X POST \"${AUTH_URL}\" -H \"Content-Type: application/x-amz-json-1.1\" -H \"X-Amz-Target: AWSCognitoIdentityProviderService.InitiateAuth\" -d '{\"AuthParameters\":{\"USERNAME\":\"${USERNAME}\",\"PASSWORD\":\"${PASSWORD}\",\"SECRET_HASH\":\"${SECRET_HASH}\"},\"AuthFlow\":\"USER_PASSWORD_AUTH\",\"ClientId\":\"${CLIENT_ID}\"}'"

curl -X POST \"${AUTH_URL}\" -H \"Content-Type: application/x-amz-json-1.1\" -H \"X-Amz-Target: AWSCognitoIdentityProviderService.InitiateAuth\" -d '{\"AuthParameters\":{\"USERNAME\":\"${USERNAME}\",\"PASSWORD\":\"${PASSWORD}\",\"SECRET_HASH\":\"${SECRET_HASH}\"},\"AuthFlow\":\"USER_PASSWORD_AUTH\",\"ClientId\":\"${CLIENT_ID}\"}'


# Get the JWT token
TOKEN=$(curl -X POST "${AUTH_URL}" \
  -H "Content-Type: application/x-amz-json-1.1" \
  -H "X-Amz-Target: AWSCognitoIdentityProviderService.InitiateAuth" \
  -d "{\"AuthParameters\":{\"USERNAME\":\"${USERNAME}\",\"PASSWORD\":\"${PASSWORD}\",\"SECRET_HASH\":\"${SECRET_HASH}\"},\"AuthFlow\":\"USER_PASSWORD_AUTH\",\"ClientId\":\"${CLIENT_ID}\"}" | jq -r '.AuthenticationResult.AccessToken')

echo "Your test token is: ${TOKEN}"
