# SendTEBucks

---

**Description** : Used to send TE Bucks to another user

**URL** : `/transferDetail/send`

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
  "message": "Account does not have sufficient funds to complete this transferDetail",
  "path": "/transferDetail/send"
}
```
