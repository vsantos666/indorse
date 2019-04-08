# profile
profile for Indorse

1.- Data Base
On a Postgress DataBase run the nexts Scripts:

--CREATE DATABASE indorse

--CREATE SCHEMA indorse
  --AUTHORIZATION postgres;

2.- The aplication 
2.1.- Please generate the war with maven and publish it on a server.

2.2.- The services:
-- header   key:User            value:{the consumer}
            key:Content-Type    value:application/json
// to add a new user POST
    http://localhost:8080/b-profile/api/user
// the body examples:
{
"name":"Raul",
"lastName":"Gongora",
"email":"rgongora@gmail.com",
"userLogin":"rgongora",
"password":"123456"
}
------------------------------
{
"name":"Mijael",
"lastName":"Salinas",
"email":"msalinas@gmail.com",
"userLogin":"msalinas",
"password":"msalinas"
}
//the userLogin method POST
    http://localhost:8080/b-profile/api/user/userLogin
// the body examples:
{
"user":"msalinas",
"password":"msalinas"
}
-- it will return you a token where on the others methots you need to use
{
    "success": true,
    "result": "80a3b85a-d416-4066-9337-b7b126dc71ad",   <-- token
    "message": "Success"
}
--------------------------------------------------
//add a header with the token for consume the others services:
-- header   key:Token            value:{token}
// the method to add a friend POST
http://localhost:8080/b-profile/api/friend
// the body examples:
{
"userId":1,
"friendId":2
}
//the method to search GET
http://localhost:8080/b-profile/api/user?name=o&page=0&size=1

