# Welcome to the email-service application!
## This app is a microservice, that allows sending emails via Simple Mail Transfer Protocol in short SMTP.
###  The application exposes a few endpoints to interact with. All of them can be found in documentation at http://localhost:8080/swagger-ui/index.html

1. [GET]    /email/all - Gets all emails addresses
2. [POST]   /email     - Add email address
3. [GET]    /email/{id} - Get email address by id
4. [PUT]    /email/{id} - Update email address by id
5. [DELETE] /email/{id} - Delete email address by id
6. [POST]   /send-email-to-all - Email all email addresses 

# Pre requirements
## To run the application build it with maven and run it with parameters:
- spring.mail.host=your smtp host
- spring.mail.port=your smtp port (default is 587)
- spring.mail.username=your smtp username
- spring.mail.password=your smtp password
### Here is an example
```
mvn clean package
java -jar target/email-service-0.0.1-SNAPSHOT.jar --spring.mail.host="smtp-provider.com" --spring.mail.port=587 --spring.mail.username="username" --spring.mail.password="password"
```
