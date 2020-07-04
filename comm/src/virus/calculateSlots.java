package virus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import commodity.ConnectMe;
import distancemapper.GetNearbyDistance;

public class calculateSlots
{

	Map<String, String> customerNameAndAddress = new HashMap();
    static Map<String, String> data = new LinkedHashMap<String, String>();

    static List<JsonObject> updatedVendorDetails = new ArrayList();
    static List<JsonObject> updatedCustomerDetails = new ArrayList();

    static Map<String, JsonObject> finalVendor = new LinkedHashMap();
    JsonObject wholeMap= new JsonObject();
    static Map<String, JsonObject> finalCustomer = new LinkedHashMap();
    
    public String getVendorName(String cName)
    {
        try
        {
            geCustomerAddress(cName);
            geVendorAddress();
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
        return finalCustomer.get("customerId"+cName).get("vendorName").getAsString();
    }
   
    public String getVendorSlot(String cName)
    {
        try
        {
            geCustomerAddress(cName);
            geVendorAddress();
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
        return finalCustomer.get("customerId"+cName).get("allotedSlot").getAsString();
    }
    
    
    private void geVendorAddress() throws SQLException
    {
    	ConnectMe conn = new ConnectMe();
    	Statement stmt = conn.connectDB.createStatement();
    	String query = "select * from vendor where category is NOT NULL";
    	String query1 = "select * from stock where vendor in (select id from vendor)";
    	String query2 = "select * from slot where vendor in (select id from vendor)";
    	ResultSet rst1 = stmt.executeQuery(query);
    	ResultSet rst2 = stmt.executeQuery(query1);
    	ResultSet rst3 = stmt.executeQuery(query2);
    	
    	JsonObject vendor1;
    	while(rst1.next())
    	{
    		vendor1 = new JsonObject();
    		int vendorid = rst1.getInt(5);
            vendor1.addProperty("vendorId", rst1.getInt(5));
            String vendorName = rst1.getString(1);
            vendor1.addProperty("vendorName", rst1.getString(1));
            vendor1.addProperty("address", rst1.getString(1));
            vendor1.addProperty("contact", rst1.getString(4));
            rst3 = stmt.executeQuery(query2);
            while(rst3.next())
            {
            	if(vendorid == rst3.getInt(6))
            	{
            		vendor1.addProperty("slotTimePeriod", rst1.getInt(1));
            		vendor1.addProperty("slotOpeningTime", rst1.getString(3).substring(0, rst1.getString(3).length()-2));
                    vendor1.addProperty("slotClosingTime", rst1.getString(4).substring(0, rst1.getString(4).length()-2));
                    vendor1.addProperty("allotedSlot", "");
            	}
            }
            wholeMap.add(vendorName, vendor1);
    	}
    	storeDetails();
    }
    private void geCustomerAddress(String customer) throws SQLException
    {
    	ConnectMe conn = new ConnectMe();
    	Statement stmt = conn.connectDB.createStatement();
    	String query = "select name, address from vendor where category is NULL";
    	ResultSet rst = stmt.executeQuery(query);
    	while(rst.next())
    	{
    		customerNameAndAddress.put(rst.getString(1), rst.getString(2));
    	}
    }
    
    public void storeDetails() throws SQLException
    {
        
        List<String> addressList = new ArrayList();
        for (Entry<String, JsonElement> s3 : wholeMap.entrySet())
        {
            JsonObject jObj = s3.getValue().getAsJsonObject();
            addressList.add(jObj.get("address").getAsString());
        }

        Map<String, JsonObject> customerData = Customer.getCustomerData();
        for (Entry<String, JsonObject> s3 : customerData.entrySet())
        {
            JsonObject jObj = s3.getValue().getAsJsonObject();
            addressList.add(jObj.get("address").getAsString());
            customerNameAndAddress.put(jObj.get("customerId").getAsString(), jObj.get("address").getAsString());
        }

        GetNearbyDistance g1 = new GetNearbyDistance();
        Map<String, Map<String, Double>> locationDetails = g1.getLocationDetails(addressList);
        Map<String, String> refinedData = new HashMap();

        for (Entry<String, String> s3 : customerNameAndAddress.entrySet())
        {
            System.out.println(s3.getKey());
            System.out.println(s3.getValue());

            Map<String, Double> localMap = locationDetails.get(customerNameAndAddress.get(s3.getKey()));
            String nearestLocationForThisUser1 = (String) localMap.keySet().toArray()[0];

            for (String addressess : localMap.keySet())
            {

                for (Entry<String, JsonElement> wholeData : wholeMap.entrySet())
                {
                    JsonObject jObj = wholeData.getValue().getAsJsonObject();
                    if (jObj.get("address").getAsString().equalsIgnoreCase(addressess))
                    {
                        refinedData.put(s3.getKey(), jObj.get("vendorName").getAsString());
                    }
                }
            }
        }

        System.out.println("OOPS " + refinedData);

        data.put("customer1", "Lego");
        data.put("customer2", "Lego");
        data.put("customer3", "Grace");
        data.put("customer4", "Grace");
        data.put("customer5", "Grace");

        createData(refinedData);

        for (JsonObject v : updatedVendorDetails)
        {
            JsonObject jobj = v;
            String vendorId = v.get("vendorId").getAsString();
            finalVendor.put("vendorId" + vendorId, jobj);
        }

        System.out.println("Updated Vendor Details ");
        System.out.println(finalVendor);

        for (JsonObject v : updatedCustomerDetails)
        {
            JsonObject jobj = v;
            String vendorId = v.get("customerId").getAsString();
            finalCustomer.put("customerId" + vendorId, jobj);
        }

        System.out.println("Updated Customer Details ");
        System.out.println(finalCustomer);

        getListOfCustomersInEachVendor(finalCustomer);
        getCustomerToItemMapping(finalCustomer);
        getCustomerTimeSlotsPerVendorWise(finalCustomer);
    }

    private static void getCustomerTimeSlotsPerVendorWise(Map<String, JsonObject> finalCustomer2)
    {
        Map<String, JsonArray> mapOfCustomerToItem = new HashMap();

        Map<String, Map<String, String>> vendorWiseDetails = new HashMap();

        for (Entry<String, JsonObject> map1 : finalCustomer2.entrySet())
        {
            System.out.println(map1.getKey());

            JsonObject jobj = map1.getValue();
            System.out.println(jobj.get("allotedSlot"));

            String key = jobj.get("vendorName").getAsString();
            Map<String, String> vendorDetails = new HashMap();
            vendorDetails.put(jobj.get("allotedSlot").getAsString(), jobj.get("customerId").getAsString());
        }

        System.out.println("getCustomerTimeSlotsPerVendorWise " + vendorWiseDetails);
    }

    private static void getCustomerToItemMapping(Map<String, JsonObject> finalCustomer)
    {
        Map<String, JsonArray> mapOfCustomerToItem = new HashMap();

        for (Entry<String, JsonObject> map1 : finalCustomer.entrySet())
        {
            JsonObject jobj = map1.getValue();
            mapOfCustomerToItem.put(map1.getKey(), jobj.get("items").getAsJsonArray());
        }
    }

    private static void getListOfCustomersInEachVendor(Map<String, JsonObject> finalCustomer)
    {

        Map<String, String> mapOfCustomersPerVendor = new HashMap();

        for (Entry<String, JsonObject> map1 : finalCustomer.entrySet())
        {
            System.out.println(map1.getKey());

            JsonObject jobj = map1.getValue();
            String vName = jobj.get("vendorName").getAsString();
            String cusId = jobj.get("customerId").getAsString();

            if (mapOfCustomersPerVendor.containsKey(vName))
            {
                String map = mapOfCustomersPerVendor.get(vName);
                map = map + " " + cusId;
                mapOfCustomersPerVendor.put(vName, map);
            }
            else
            {
                mapOfCustomersPerVendor.put(vName, cusId);
            }
        }

        System.out.println(mapOfCustomersPerVendor);
    }

    private static void createData(Map<String, String> map) throws SQLException
    {

        JsonObject completeData = LoadData.getCompleteData();
        Map<String, JsonObject> customerData = Customer.getCustomerData();

        for (Entry<String, String> eachItem : map.entrySet())
        {

            JsonObject jObj = customerData.get(eachItem.getKey());
            if (jObj != null)
            {
                if (jObj.has("vendorName"))
                {
                    String vName = jObj.get("vendorName").getAsString();
                    JsonObject vendorDetails = completeData.get(vName).getAsJsonObject();

                    int totalSlots = Integer.parseInt(vendorDetails.get("totalSlots").getAsString());

                    if (vendorDetails.get("allotedSlot").getAsString().isEmpty())
                    {
                        //First slot.
                        int slotOpeningTime = Integer.parseInt(vendorDetails.get("slotOpeningTime").getAsString());
                        Calendar c = getTime(slotOpeningTime, 0);
                        Calendar c1 = addPeriodToHour(c.getTime().getHours(), 0);
                        vendorDetails.addProperty("allotedSlot", c1.getTime().toLocaleString());
                        jObj.addProperty("allotedSlot", c1.getTime().toLocaleString());
                    }
                    else
                    {
                        Date allotedDate = new Date(vendorDetails.get("allotedSlot").getAsString());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(allotedDate);
                        calendar.add(Calendar.MINUTE, 15);
                        vendorDetails.addProperty("allotedSlot", calendar.getTime().toLocaleString());
                        jObj.addProperty("allotedSlot", calendar.getTime().toLocaleString());
                    }

                    jObj.addProperty("vendorName", eachItem.getValue());

                    totalSlots = totalSlots - 1;
                    vendorDetails.addProperty("remainingSlots", totalSlots);
                    updatedVendorDetails.add(vendorDetails);
                }
            }
            updatedCustomerDetails.add(jObj);

        }

    }

    private static void checkDate(JsonObject vendorDetails)
    {
        System.out.println(vendorDetails.get("date"));

        Date d = new Date();
        System.out.println(d.getTime());

    }

    public static Calendar getTime(int slotOpeningTime, int minutes)
    {
        Calendar date = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, slotOpeningTime);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date;
    }

    public static Calendar addPeriodToHour(int slotOpeningTime, int minutes)
    {
        Calendar date = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, slotOpeningTime);
        date.set(Calendar.MINUTE, minutes);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date;
    }

}
