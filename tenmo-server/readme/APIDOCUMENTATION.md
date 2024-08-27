# APIDocs

---
## API BASE_URL 
** BASE_URL is the URL that will prefix all endpoints**

http://localhost/8080



---

## Open Endpoints

Open endpoints require no Authentication.

* [Login](authorization/login.md) : `POST /login`

* [Register](authorization/register.md) : `POST /register`

---

## Endpoints that require Authentication

Closed endpoints require a valid Token to be included in the header of the
request `Authorization: Bearer {token}` A Token can be acquired from the Login view above.

### User Account Related

Each endpoint manipulates or displays information related to the User whose
Token is provided with the request:

* [Get account Balance](account/getBalance.md) : `GET /account/balance`

---


### User Related 

* [Get List of users not including current User](user/getUsersExludingCurrent) : `GET /users/exclude_current`

### Transfer related

Endpoints for viewing and manipulating the Transfers that the Authenticated User has permissions to access.

* [Send TE Bucks](transfer/sendTEBucks.md) : `POST /transfer/send`
* [Get List of user Transfers](transfer/getTransfers.md) : `GET /transfer`
* [Get Transfer Details by ID](transfer/getTransferById.md) : `GET /transfer/{id}`
* [Request TE Bucks](transfer/requestTEBucks.md) : `POST /transfer/request`
* [Get List of pending Transfer Requests](transfer/getPendingTransfers.md) : `GET /transfer/pending/`
* [Approve Transfer Requests](transfer/updateTransfer.md) : `PUT /transfer/{transfer_id}/update_transfer/`