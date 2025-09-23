This is spring boot project for bank system.
It provides Restful APIs to perform operation and get data by jdbc template.
###Create new User (Register new user)
**Endpoint:** /save-user/user-details
**Method:** POST
**RequestBody:** JSON
```json
{
    "firstName": "Deo",
    "lastName": "mittal",
    "email": "kanika@gmail.com",
    "phone": "9865287877",
    "address": "Jaipur",
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
