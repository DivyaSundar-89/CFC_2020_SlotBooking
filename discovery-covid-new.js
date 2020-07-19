/**
 *
 * main() will be run when you invoke this action
 *
 * @param Cloud Functions actions accept a single parameter, which must be a JSON object.
 *
 * @return The output of this action, which must be a JSON object.
 *
 */
var request = require("request-promise");
var Cloudant = require('@cloudant/cloudant');
var cloudant = Cloudant({ username:'41853c84-7969-4a90-8330-a577d695bc23-bluemix', password:'ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b', url:'https://41853c84-7969-4a90-8330-a577d695bc23-bluemix:ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b@41853c84-7969-4a90-8330-a577d695bc23-bluemix.cloudantnosqldb.appdomain.cloud',plugins: 'promises',maxAttempt: 5  });

const DiscoveryV1 = require("watson-developer-cloud/discovery/v1");

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

async function main(params) {
    var db = cloudant.db.use('mapping')
    var donorName = {};
    var donorAddress = {};
    var donorEmail = {};
    var donorPhone = {};
    var donorNotes = {};
    var donorTime = {};
    var category = {};
    var recipientName = {};
    await db.list({include_docs:true}, function(err, body){
        body.rows.forEach(function(row){
            var doc  = row.doc;
            if(doc.recipientName == params.recipient_name)
            {
            donorName[0] = doc.donorName;
            donorAddress[0] = doc.donorAddress;
            donorEmail[0] = doc.donorEmail;
            donorPhone[0] = doc.donorPhone;
            donorNotes[0] = doc.donorNotes;
            donorTime[0] = doc.donorTime;
            category[0] = doc.category;
            }
        });
        
    });

    let cust_name = params.cust_name;
      const summary = await request({
        method: "GET",
        uri: 'https://api.covidindiatracker.com/state_data.json?state=Tamil%20Nadu',
        json: true,
      });
      let recipient_Name = params.recipient_name;

      let donor_name = donorName[0];
      let donor_address = donorAddress[0];
      let donor_email = donorEmail[0];
      let donor_phone = donorPhone[0];
      let donor_notes = donorNotes[0];
      let donor_time = donorTime[0];
      let donor_category = category[0];
      
        return {
          result: `Hello ${recipient_Name}. You had registered for the category - ${donor_category}. The details of your good-will giver who has agreed to share with you are: 
                   Name - ${donor_name}
                   Address - ${donor_address}
                   email - ${donor_email}
                   phone - ${donor_phone}
                   Availability - ${donor_time}
                   The giver has left a note for you - ${donor_notes}
                   You can contact the giver and collect your essentials.`
        };
  
     
}