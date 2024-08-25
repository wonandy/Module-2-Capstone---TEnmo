# GetTransfers

---

**Description** : Used to retrieve a list of transferDetail sent or recieved by current user

**URL** : `/transfer`

**Method** : `GET`

**Auth required** : YES

**No Body Required**

---

## Success Response

**Code** : `200 OK`

**Content example**
```json
[
  {
    "transferId": "2001 (integer)",
    "accountFrom": "Harrison (string)",
    "accountTo": "Anya (string)",
    "amount": "100.00 (numeric)"
  },
  {
    "transferId": "2003 (integer)",
    "fromUser": "Jeff (string)",
    "toUser": "Harrison (string)",
    "amount": "333.33 (numeric)" 
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
  "path": "/account/balance"
}
```

