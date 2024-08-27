# Balance


**Description** : Retrieves the balance of current user The response body contains the `BigDecimal` value directly.

**URL** : `BASE_URL/account/balance`

**Method** : `GET`

**Auth required** : YES



**No Body Required**

---

## Success Response

**Code** : `200 OK`

**Content Example**

```
{
1000.00
}
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
  "path": "/account/balance"
}
```
