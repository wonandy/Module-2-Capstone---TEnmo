# GetPendingTransfers

---

**Description** : Used to retrieve a list of pending transferDetail requests for user

**URL** : `/transferDetail/pending`

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
    "transfer_id": "2001 (integer)",
    "account_from": "Anya (string)",
    "amount": "100.00 (numeric)"
  },
  {
    "transfer_id": "2003 (integer)",
    "account_from": "George (string)",
    "amount": "333.33 (numeric)" 
  }
]
```

---

## Error Response

**Condition** : Token missing or invalid

**Code** : `401 Unauthorized`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:20:05.073+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Token is missing or invalid.",
  "path": "/api/transferDetail/pending/"
}
```
