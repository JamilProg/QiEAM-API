# QiEAM-API

Simple API with Java, SPARK and PostGreSQL.
Also added: password to get API access.

# To run the API app

Preconditions:
- Use PostGreSQL;
- Having a database which contains 3 tables: Friend, Libgame and Storegame, each table containing an integer and a string column;
- Change the username, password and databaseName in Main.java to configure with yours.

Steps:
- Run Main.java;
- Type the password which is "qieam";
- The API is running.

# How to test it

Test done with Postman while Main.java was running.

In case your tables are empty, three post features are added to populate them:
- POST /api/post/addFriends
- POST /api/post/addLibGames
- POST /api/post/addStoreGames

Screenshot:

![alt text](http://uppix.3dvf.com/images/2020/07/22/Capture.png)
