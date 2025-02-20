#!/bin/bash

USER_JSON=$(cat <<EOF
{
  "name": "bob", 
  "roles": ["admin"],
  "location": {
    "country": "US",
    "ip": "8.8.8.8"
  }
}
EOF
)

echo curl -X POST http://localhost:8081/v1/users \
  -H "Content-Type: application/json" \
  -d "$USER_JSON"
