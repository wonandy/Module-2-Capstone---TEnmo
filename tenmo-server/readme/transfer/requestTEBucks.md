# RequestTEBucks

**Description** : Used to request TE Bucks from another user

**URL** : `/transferDetail/request`

**Method** : `POST`

**Auth required** : YES

**Data constraints**

```json
{
  "account_from": "integer",
  "amount": "numeric"
}
```

**Data example**

```json
{
  "account_from": 1002,
  "amount": 399.44
}
```

## Success Response

**Code** : `201 Created`

**Content Example**

```json
{
  "account_from": 1002,
  "amount": 399.44,
  "transfer_status": "Pending"
}
```


## Error Response

**Condition** : Invalid amount or requester cannot be yourself

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:15:05.073+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid amount or requester cannot be yourself.",
  "path": "transferDetail/request/"
}
```

