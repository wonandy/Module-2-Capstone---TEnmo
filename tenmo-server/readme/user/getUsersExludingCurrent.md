# Balance


**Description** : Retrieves a list of all users excluding the current user

**URL** : `BASE_URL/user/exclude_current`

**Method** : `GET`

**Auth required** : YES



**No Body Required**

---

## Success Response

**Code** : `200 OK`

**Content Example**

```
[
    {
        "id": 1002,
        "username": "user2",
        "authorities": [
            {
                "name": "ROLE_USER"
            }
        ]
    },
    {
        "id": 1003,
        "username": "user3",
        "authorities": [
            {
                "name": "ROLE_USER"
            }
        ]
    }
]
```

---

## Error Response

**Condition** : If Not Authenticated.

**Code** : `401 Unauthorized`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:30:19.725+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Full authentication is required to access this resource",
  "path": "/user/exclude_current"
}
```
