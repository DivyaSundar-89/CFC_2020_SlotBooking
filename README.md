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



** Demo recording of use case 1 

Demo recording video link - https://www.youtube.com/watch?v=PdwA0tA-LeI&feature=youtu.be


** Steps to run the project (locally)
1.	Install angular cli 10 and NPM in the enviroment. Create two angular projects called helper-application and receiver-application using ng new helper-application, ng new receiver-application
2.	Copy the src/app folder and src/assets/img from the github link - https://github.com/DivyaSundar-89/CFC_2020_SlotBooking/tree/master/helper_application/src  and paste it in the newly created angular project for helper-application.
3.	Copy the src/app folder and src/assets/img from the github link - https://github.com/DivyaSundar-89/CFC_2020_SlotBooking/tree/master/recipient_application/src  and paste it in the newly created angular project for receiever-application.
4.	In both angular projects, make sure to have only the following lines of code in src/index.html to load the UI properly
<my-app>loading</my-app>

5.	Launch both the ngular projects using ng serve
6.	Use the Nodejs discovery action discvery-covid-new (link - https://github.com/DivyaSundar-89/CFC_2020_SlotBooking/blob/master/discovery-covid-new.js ) as a discovery action running as a webhook for the chatbot assistant.
7.	Link the dialog skill (link - https://github.com/DivyaSundar-89/CFC_2020_SlotBooking/blob/master/skill-CDC-COVID-FAQ.json  ) with the chat bot assistant.
8.	Pass a parameter name in the chat-bot assistant, key - recipient_name and value is the name of the receiver.
9.	Register the donor and receiver in the UI and use the watson chatbot assistant to get the necessary details.

** Steps to run the project (hosted in IBM cloud services)
1.	Access the donor and receiver UI applications hosted as the IBM cloud foundry applications.
http://helper-app.eu-gb.cf.appdomain.cloud  – Helper donor registration
http://recipient-app.eu-gb.cf.appdomain.cloud/ - Helper receiver registration

2.	Install docker locally to access the backend micro-service hosted in the IBM cloud registry service.
Steps to install docker engine, docker ce and docker compose can be found at https://docs.docker.com/get-docker/

3.	Use docker pull to pull the image hosted in the IBM cloud registry service. The following command pulls the image with the latest tag from the repository us.icr.io
docker pull us.icr.io/divyasundar/cfc2020:cfc-spring-boot-docker
The namespace created in the registry service is divyasundar and the repository tag is cfc2020.

4.	Install the IBM cloud service/cf cli utility to connect with the central hub where docker images are pushed in IBM repositories. Ibmcloud command line utility offers a wide variety of docker commands and cf push commands.

5.	Use the Nodejs discovery action discvery-covid-new (link - https://github.com/DivyaSundar-89/CFC_2020_SlotBooking/blob/master/discovery-covid-new.js  ) as a discovery action running as a webhook for the chatbot assistant to get the receiver details.

6.	Use the Nodejs discovery action discovery-covid-donor (link - https://github.com/DivyaSundar-89/CFC_2020_SlotBooking/blob/master/discovery-covid-donor.js ) as as a discovery action running as a webhook for the chatbot assistant to get the details of the receiver slots and receiver names given a donor.

7.	Link the dialog skill (link - https://github.com/DivyaSundar-89/CFC_2020_SlotBooking/blob/master/skill-CDC-COVID-FAQ.json   ) with the chat bot assistant.

8.	Pass a parameter name in the chat-bot assistant, key - recipient_name and value is the name of the receiver.
9.	Register the donor and receiver in the UI and use the watson chatbot assistant to get the necessary details.
10.	Use the webhook function of discovery-covid-donor to get the details of the receiver slot id and the receiver names given a donor queries through the chatbot assistant.

** Demo recording of use case 2
              Demo recording video link - https://www.youtube.com/watch?v=sCK1K-pKS7Q 
