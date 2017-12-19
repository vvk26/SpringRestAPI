# SpringRestAPI

First Run Below Query


CREATE TABLE USERDETAILS
   (	USERNAME VARCHAR2(50 BYTE), 
        PASSWRD VARCHAR2(50 BYTE), 
        INTEREST VARCHAR2(50 BYTE),
         CONSTRAINT "USERPK_KEY" PRIMARY KEY ("USERNAME")
   ) 
   
   
 Copy config directory from dbPropertiesFile which is in Woekspace and Paste it in Server.
 change DB_PROPERTIES.xml as per Database.
 
 Run Project (Tomcat)
 
 Igonore First Browser Request.(Since We have not used welcome-file jsp/html)
 
 Use Postman.
 
 Content Type :  x-www-form-urlencoded
 method : POST
 
 for adding User 
 
http://localhost:8000/SpringRestAuth/RestAPI/newUser

keys            values
userName  		abc
password      	abc@123

for Login user

http://localhost:8000/SpringRestAuth/RestAPI/login

keys            values
userName  		abc
password      	abc@123
prAmt			1000
fromDate		12-11-2017 (dd-MM-yyyy)
toDate			12-12-2019 (dd-MM-yyyy)
