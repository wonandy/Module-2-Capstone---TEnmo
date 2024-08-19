# GetTransfers

---

**Description** : Used to retrieve a list of transferDetail sent and recieved by user

**URL** : `/transferDetail`

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
    "fromUser": "Harrison (string)",
    "toUser": "Anya (string)",
    "amount": "100.00 (numeric)"
  },
  {
    "transferId": "2003 (integer)",
    "fromUser": "Jeff (string)",
    "toUser": "George (string)",
    "amount": "333.33 (numeric)" 
  }
]
```

---

## Error Response
[//]: # ()
[//]: # (TODO Figure out error Response possibilities) 

[//]: # (**Condition** : User does not have sufficient funds to complete transferDetail)

[//]: # ()
[//]: # (**Code** : `400 BAD REQUEST`)

[//]: # ()
[//]: # (**Content** :)

[//]: # ()
[//]: # (```json)

[//]: # ({)

[//]: # (  "timestamp": "2024-08-10T00:30:19.725+00:00",)

[//]: # (  "status": 400,)

[//]: # (  "error": "Insufficient Funds",)

[//]: # (  "message": "Account does not have sufficient funds to complete this transferDetail",)

[//]: # (  "path": "/transferDetail/send")

[//]: # (})

[//]: # (```)
