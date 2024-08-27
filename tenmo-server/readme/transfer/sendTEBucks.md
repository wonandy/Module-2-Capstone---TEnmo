# SendTEBucks

---

**Description** : Used to send TE Bucks to another user

**URL** : `/transfer/send`

**Method** : `POST`

**Auth required** : YES

**Data constraints**

```json
{
  "toUserId": "integer",
  "amount": "BigDecimal"
}
```

**Data example**

```json
{
  "toUserId": 1003,
  "amount": 984.00
}
```

---

## Success Response

**Code** : `200 OK`

**No Content Returned**

```
{
Transfer successful.
}
```

---


## Error Response

**Condition** : User does not have sufficient funds to complete transferDetail

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:30:19.725+00:00",
  "status": 400,
  "error": "Insufficient Funds",
  "message": "Insufficient funds for the transfer.",
  "path": "/transfer/send"
}
```
