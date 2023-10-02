# conference-organizer

This project helps you to organize your conferecnes.
Please import ***_Conference-Organizer.postman_collection.json_*** collection export file to the your Postman.

Here is the using steps
1. Create an Conference object with name and the date. ***Create Conference request in the postman collection***
2. Create the Presantations of the Conference with duration in minutes and name. ***Save Presentation to Conference request in the postman collection***
3. Then the api will organize your presentations with given only your conference. ***Organize The Conference request in the postman collection***
4. You can view the existing organizations that belongs your conferences in tracks. ***Retrieve Existing Tracks request in the postman collection***

   ## This project has swagger file and you can get the swagger when you run the project on your local, here the url for swagger **http://localhost:9292/conference-organizer/swagger-ui.html#/**
   ## This project has h2 embeded database engine and you can login the database portal when you run the prject on your local, here is the url of h2 console **http://localhost:9292/conference-organizer/h2-console**
   H2 database credentials info
   **JDBC URL: jdbc:h2:~/conference-organizer
   User: sa
   Password: Passw0rd**
   ##

   Here is the screen shot of the h2-console

   ![image](https://github.com/emreakoglu/conference-organizer/assets/2580264/d6c19c3f-22bc-496d-b41d-ec9b1fcb474f)


