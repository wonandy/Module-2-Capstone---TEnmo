# Login

**Description** : Used to collect a Token for a registered User.

**URL** : `/login/`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

```json
{
    "username": "username",
    "password": "password in plain text"
}
```

**Data example**

```json
{
    "username": "Harrison",
    "password": "password"
}
```

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXJyaXNvbiIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE3MjMzMjcxMzB9.OlgH0V6X2BH2sk57aimsEXhMcP1euSHgnNh45Ri3nkbyEFDTIXczlmOpu-4DG6aqje1NWh0OAKsaaMCjQV9_3w",
  "user": {
    "id": 1004,
    "username": "harrison",
    "authorities": [
      {
        "name": "ROLE_USER"
      }
    ]
  }
}
```

## Error Response

**Condition** : If 'username' and 'password' combination is wrong.

**Code** : `401 Unauthorized`

**Content** :

```json
{
  "timestamp": "2024-08-09T21:59:38.387+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Cannot invoke \"com.techelevator.tenmo.model.User.isActivated()\" because \"user\" is null",
  "path": "/login"
}
```
