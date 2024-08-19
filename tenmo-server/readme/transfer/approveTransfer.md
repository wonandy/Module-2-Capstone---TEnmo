# SendTEBucks

---

**Description** : Used to update the Transfer Status Description to Approved

**URL** : `/transfer/{transfer_id}/approve`

**Method** : `PUT`

**Auth required** : YES

**Data constraints**


```json
{
  "transfer_status_desc": "Pending (String)"
}
```

**Data example**

```json
{
  "transfer_status_desc": "Approved(String)"
}
```
---

## Success Response

**Code** : `200 OK`

**Content Example**

```json
{
  "transfer_status_id": 3001,
  "transfer_status_desc": "Approved"
}
```

---


## Error Response

**Condition** : Invalid Status or insufficient funds to complete transferDetail request

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:25:05.073+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid status or insufficient funds.",
  "path": "transferDetail/{transfer_id}/status/"
}
```
