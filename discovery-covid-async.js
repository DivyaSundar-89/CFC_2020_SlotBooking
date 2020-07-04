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
const DiscoveryV1 = require("watson-developer-cloud/discovery/v1");

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}


async function main(params) {
      let cust_name = params.cust_name;
      const summary = await request({
        method: "GET",
        uri: 'http://localhost:8083/getslotdetail/${cust_name}',        
        json: true,
      });

        let slot = summary.slot;
        let vendor_id = summary.vendor_id;
        let cust_id = summary.cust_id;

        return {
          result: `Vendor Slot: ${slot} \n Customer Name: ${cust_id} \n Vendor ID: ${vendor_id}`
        };
         
}