    **Bank System Spring Boot Project**
This is spring boot project for bank system.
It provides Restful APIs to perform operation and get data by jdbc template.
###Create new User (Register new user)
**Endpoint:** /save-user/user-details
**Method:** POST
**Authentication:** Not required
**RequestBody:** JSON
```json
{
    "firstName": "Jhon",
    "lastName": "Deo",
    "email": "jhon@gmail.com",
    "phone": "9865287877",
    "address": "Udaipur",
    "userName": "Deo",
    "password": "12345",
    "role": "USER"
}
```
**Response** 
```json
{
    "message": "User Registered successfully"
}
```
**Description:** Creates a new user record and saves it into the database without authentication.

###Login with userName and password
**Endpoint** /login/user
**Method:** GET
**Authentication:** Required (basic auth)
**Response:** JSON
```json
{
    "message": "User login successfully"
}
```
**Description:** Login using username and password with Basic Auth. Supports roles (USER, ADMIN).

###Get list of all admin users
**Endpoint** /save-user/user-admin?role=ADMIN
**Method:** GET
**Authentication:** Required (basic auth)
**Response:** JSON
```json
[
    {
        "userId": 19,
        "firstName": "Anita",
        "lastName": "Soni",
        "email": "anita@gmail.com",
        "phone": "9865287665",
        "role": "ADMIN",
        "address": "udaipur",
        "password": "$2a$10$p2Uj6qPIEHyOTjjgNpyCcOAuST9V9C1XxYVAuYHi/VwUdrb.W.iN.",
        "userName": "Anita"
    },
    {
        "userId": 21,
        "firstName": "Jhon",
        "lastName": "Deo",
        "email": "jhon@gmail.com",
        "phone": "9865287876",
        "role": "ADMIN",
        "address": "Kota",
        "password": "$2a$10$dS2zO3bD.XCgtpaZ4DaUT.lJtKexfqQfpvDpEX5xjGAcc1PcVqmJ2",
        "userName": "Jhon"
    }
]
```
**Description:** Fetches all users who have the ADMIN role.

###Create new account 
**Endpoint:** /save-account/details
**Method:** POST
**Authentication:** Required (basic auth)
**RequestBody:** JSON
```json
{
  "user_id": 25,
  "account_type": "SAVINGS",
  "accountNumber": 123456789,
  "balance": 8000,
  "openedDate": "2025-09-22",
  "status": "ACTIVE"
}
```
**Response** Json
```json
{
    "resourceId": "31",
    "message": "User account created successfully"
}
```
**Description:** Creates a new account record and saves it into the database with authentication.

###Update account
**Endpoint:** /save-account/update-account?account_id=23
**Method:** PUT
**RequestBody:** JSON
```json
{
  "userId": 25,
  "account_type": "SAVINGS",
  "status": "ACTIVE",
  "accountNumber": 1234567890,
  "balance": 11000,
  "openedDate": "2025-09-24"
}
```
**Description:** Updates the details of an existing account specified by the account_id.

###Get all users account details
**Endpoint:** /save-account/get-user-account
**Method:** GET
**Authentication:** Required (basic auth)
**Response:** JSON
```json
[
    {
        "accountId": 1,
        "userId": 1,
        "accountNumber": 100001,
        "accountType": "SAVINGS",
        "status": "ACTIVE",
        "balance": 50000,
        "openedDate": "2025-01-10"
    },
    {
        "accountId": 2,
        "userId": 2,
        "accountNumber": 100002,
        "accountType": "CURRENT",
        "status": "INACTIVE",
        "balance": 75000,
        "openedDate": "2025-02-12"
    }
]
```
**Description:** Retrieves a list of all user accounts with details, such as account ID, account number, balance, status, and account type.

###Get account details by id
**Endpoint:** /save-account/get-account-id?account_id=23
**Method:** GET
**Authentication:** Required (basic auth)
**Response:** JSON
```json
[
    {
        "accountId": 23,
        "userId": 25,
        "accountNumber": 1234567890,
        "accountType": "SAVINGS",
        "status": "ACTIVE",
        "balance": 11000,
        "openedDate": "2025-09-24"
    }
]
```
**Description:** Fetches the details of a specific account using account_id.

###Close account by id
**Endpoint:** /save-account/add-inactive-account-id
**Method:** POST
**Authentication:** Required (basic auth)
**RequestBody:** JSON
```json
{
  "accountId": 22
}
```
**Response:** JSON
```json
{
    "ResourceId": "22",
    "message": "Inactive account"
}
```
**Description:** Marks an existing account as inactive (closes the account) using the specified account_id. Once closed, the account cannot be used for transactions until reactivated.

###Create new Transaction 
**Endpoint:** /save-txn/details
**Method:** POST
**Authentication:** Required (basic auth)
**RequestBody:** JSON
```json
{
  "accountId": 23,
  "amount": 30000,
  "type": "WITHDRAWAL",
  "txnDate": "2025-09-21",
  "receiverAccountId": 18
}
```
**Response:**
```json
{
  "resourceId": "99",
  "message": "New Transaction created successfully"
}
```
**Description:** Creates a new transaction record and saves it into the database with authentication.

###Add transaction-withdraw amount
**Endpoint:** /save-txn/trans-withdraw-amount
**Method:** POST
**Authentication:** Required (basic auth)
**RequestBody:** JSON
```json
{
  "accountId": 23,
  "amount": 1500
}
```
**Response**
```json
{
  "message": "Withdraw money transaction"
}
```
**Description:** Creates a withdrawal transaction for a specific account.

###Add transaction-deposit amount
**Endpoint:** /save-txn/trans-deposit-amount
**Method:** POST
**Authentication:** Required (basic auth)
**RequestBody:** JSON
```json
{
  "accountId": 24,
  "amount": 1000
}
```
**Response:**
```json
{
    "message": "Deposit money transaction"
}
```
**Description:** Creates a deposit transaction for a specific account.

###Get transaction history by account id
**Endpoint:** /save-txn/trans-history?account_id=23
**Method:** GET
**Authentication:** Required (basic auth)
**Response:** JSON
```json
[
    {
        "txnDate": "2025-09-23T09:28:54.000+00:00",
        "amount": 1000,
        "type": "DEPOSIT"
    },
    {
        "txnDate": "2025-09-23T09:36:30.000+00:00",
        "amount": 2000,
        "type": "DEPOSIT"
    }
]
```
**Description:** Retrieves all transactions for a specific account includes transaction dates, amounts, and types.

###Get transaction history by ADMIN role
**Endpoint:** /save-txn/trans-admin-history?role=ADMIN
**Method:** GET
**Authentication:** Required (basic auth)
**Response:** JSON
```json
[
    {
        "txn_date": "2025-09-01",
        "role": "Admin"
    },
    {
        "txn_date": "2025-09-03",
        "role": "Admin"
    },
    {
        "txn_date": "2025-09-07",
        "role": "Admin"
    },
    {
        "txn_date": "2025-09-08",
        "role": "Admin"
    }
]
```
**Description:** Retrieves all transactions for users with the ADMIN role.

###Transfer amount between accounts
**Endpoint:** /save-txn/trans-amount-acc
**Method:** POST
**Authentication:** Required (basic auth)
**RequestBody:** JSON
```json
{
  "senderAccountId": 24,
  "receiverAccountId": 25,
  "amount": 10000
}
```
**Response:**
```json
{
    "message": "Money transfer one account to another"
}
```
**Description:** Transfers amount from one account (sender) to another account (receiver). Validates account statuses, sufficient balance, and ensures both accounts are active before completing the transfer. Save transaction details for both sender and receiver.
