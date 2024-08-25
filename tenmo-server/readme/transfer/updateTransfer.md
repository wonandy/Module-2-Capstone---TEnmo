# SendTEBucks

---

**Description** : Used to update the Transfer Status Description to Approved or Rejected

**URL** : `/transfer/{transfer_id}/update_transfer`

**Method** : `PUT`

**Auth required** : YES

**Data constraints**


```json
{
  "transfer_status_desc": "Rejected(String)"
}
```

**Data example**

```json
{
  "transfer_status_desc": "Approved"
}
```
---

## Success Response

**Code** : `200 OK`

**Content Example**

```
{
 "Transfer successfully approved and completed."
}
```

---


## Error Response

**Condition** : insufficient funds to complete transferDetail request

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:25:05.073+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Insufficient funds for the transfer.",
  "path": "transfer{transfer_id}/update_transfer"
}
```
