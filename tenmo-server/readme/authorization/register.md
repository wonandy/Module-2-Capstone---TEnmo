# Register

**Description** : Used to collect a Token for a registered User.

**URL** : `/api/register/`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

```json
{
    "username": "username must be unique",
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

**Code** : `201 Created`

**No Content Returned**


## Error Response

**Condition** : If 'username' has already been taken.

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
  "timestamp": "2024-08-09T23:45:05.073+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "User already exists.",
  "path": "/register"
}
```
