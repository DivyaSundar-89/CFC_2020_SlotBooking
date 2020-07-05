** Pre-requisites for running the project

1. Download and install an IDE such as Eclipse or Netbeans or IntelliJ.
2. Download and install web container such as tomcat 7.0 for Windows.
3. MYSQL database setup required in windows.
4. Watson assistant to be created and dialog skill to be imported from this project.
5. Watson discovery to be provisioned with the default pre-enriched data set and create a cloud function for the node js script
   to access the external REST API.

** Sanpshots of the working POC is given in the file "Snapshots of working poc" present in this project

** Steps to execute the webapp project

1. Import the project comm in an IDE and execute it in tomcat or, deploy the war file comm.war available in the same path 
in tomcat webapps.
2. Run the SQL statements from the file Sql_Statements in MYSQL DB to setup and create the tables and DB required.
3. Launch the application in http://localhost:8080/comm/index.jsp. Please note that vendor registration and customer registration are mandatory
before launching the dashboard and order details view.

** Launch the Spring boot

1. Launch the jar file cfcrest-0.0.1-SNAPSHOT.jar (java -jar cfcrest-0.0.1-SNAPSHOT.jar) or build the project "demo" through maven (mvn clean install which generates the jar file cfcrest-0.0.1-SNAPSHOT.jar in target).
2. Running the jar will launch the REST API in localhost:8083. The REST API is http://localhost:8083/getslotdetail/${customer_name} where customer_name is the path parameter based on which the slot and the vendor id is retrieved and response is sent.

** Create the required IBM functions

1. Create a watson assistant chatbot using the procedures given in the link https://developer.ibm.com/tutorials/create-a-covid-19-chatbot-embedded-on-a-website/
2. Import the dialog skill given in this project skill-CDC-COVID-FAQ.json (which has custom intents and dialogs to get the slot details booked for the customer) and associate it with the watson assistant.
3. Create a watson discovery with the pre-enriched data set using the instructions given in the link https://developer.ibm.com/tutorials/create-a-covid-19-chatbot-embedded-on-a-website/.
4. Create a cloud function and action with runtime as Node 10. Use the script as action code - discovery-covid-async.js in this project which makes the external REST call to fetch the vendor slot id.
5. Enable the cloud function as a web action and make the public URL as a webhook.
6. Customize the dialog "GetSlotBooked" (under CDC-COVID-FAQ) and enable the webhook. The parameter name "cust_name" is not dynamic from the chat-bot, so add the customer_name as a parameter in the dialog customization, example: cust_name key has value Alex (which will be passed as Path parameter to the external REST call).
7. IBM cloudant database is used which stores the details of the record for vendor id, customer id and the slot booked with the vendor. The cloudant database is accessed by the node10 runtime cloud function which gives the response to the watson assistant chat-bot.

** Testing the chat-bot

1. Preview the assistant by launching it and type the sample question assuming Alex has logged on in the chat-window - "What is the slot booked with the vendor ?". This would invoke the web-hook and issue REST call externally to get the vendor slot id which is linked to Alex. 

Previous to this, it is expected that the vendor has registered the stock and inventory details and customer (Alex) registers in the app and books a slot for the necessary inventory. A slot id from the vendor is associated to Alex ensuring no two customers get the same slot id when slot booking is done.
