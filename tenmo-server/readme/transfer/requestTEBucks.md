# RequestTEBucks

**Description** : Used to request TE Bucks from another user

**URL** : `/transfer/request`

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

```

{
  "Transfer request sent."
}
```

## Error Response

**Condition** : If attempting to request money from yourself

**Code** : `400 Bad Request`

**Content** :

```json
{
  "timestamp": "2024-08-10T00:10:05.073+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Cannot request money from yourself.",
  "path": "transfer/{transfer_id}/"
}
```


