####  Instructions to run Server ####
Prerequisites:
Make sure database is running on port 1527.
Don't forget to provide clean/empty database with schema name 
PA165 with credentials: 
username:"pa165"
password:"pa165"

To run server go to directory
"trunk/tireservice/tireservice-web"

and run command 
mvn tomcat7:run

Server should start on address:
http://localhost:8080/pa165

so open some browser and insert  http://localhost:8080/pa165



####  Instruction to run Client ####  

Go to "trunk/tireservice/tireservice-rest-client/" directory, then run command:
mvn tomcat7:run

You should be able to see running client on this address:
http://localhost:8081/tireservice-rest-client


so open some browser and insert http://localhost:8081/tireservice-rest-client