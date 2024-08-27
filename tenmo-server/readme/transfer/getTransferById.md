# GetTransferById

---

**Description** : Used to retrieve transferDetail details by transfer_id

**URL** : `/transferDetail/{id}`

**Method** : `GET`

**Auth required** : YES

**No Body Required**

**URL Params Required** : transfer_id (integer): Id of transferDetail to retrieve

---

## Success Response

**Code** : `200 OK`

**Content example**
```json
{
  "transfer_id": "2001 (integer)",
  "account_from": "Harrison (string)",
  "account_to": "Anya (string)",
  "transfer_type": "Send (string)",
  "transfer_status": "Approved (string)",
  "amount": "543.33 (numberic)"
}
```
---
## Error Response

**Condition** : If Transfer ID does not exist

**Code** : `404 Not Found`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:10:05.073+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Transfer not found.",
  "path": "transferDetail/{transfer_id}/"
}
```
