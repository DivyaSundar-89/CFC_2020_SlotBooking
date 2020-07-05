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
    var db = cloudant.db.use('cus')
    var score = {};
    var ven_id = {};
    await db.list({include_docs:true}, function(err, body){
        body.rows.forEach(function(row){
            var doc  = row.doc;
            if(doc.cust_id == params.cust_name)
            {
            score[0] = doc.slot;
            ven_id[0] = doc.vendor_id;
            }
        });
        
    });

      let cust_name = params.cust_name;
      const summary = await request({
        method: "GET",
        uri: 'https://api.covidindiatracker.com/state_data.json?state=Tamil%20Nadu',
        json: true,
      });

      let test_slot = score[0];
      let venid = ven_id[0];
      
        return {
          result: `Vendor Slot: ${test_slot} \n Customer Name: ${cust_name} \n Vendor ID: ${venid}`
        };
         
}