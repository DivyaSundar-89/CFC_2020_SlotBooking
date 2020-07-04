package virus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

import commodity.ConnectMe;

public class Customer
{

    static Map<String, JsonObject> customerMap = new HashMap<String, JsonObject>();

    public static Map<String, JsonObject> getCustomerData() throws SQLException
    {
    	try
    	{
    		ConnectMe conn = new ConnectMe();
            int i = 1;
            Statement stmt = conn.connectDB.createStatement();
            ResultSet rst = stmt.executeQuery("SELECT v.Id, v.Address, v.name , o.oId, o.date, o.total_price FROM vendor v INNER JOIN orders o ON v.Id = o.id");
            while (rst.next())
            {
                JsonObject customer = new JsonObject();
                customer.addProperty("customerId", rst.getString(1));
                customer.addProperty("address", rst.getString(2));
                customer.addProperty("vendorName", rst.getString(3));
                customer.addProperty("orderId", rst.getString(4));
                customer.addProperty("date", rst.getString(5));
                customer.addProperty("totalPrice", rst.getString(6));
                customerMap.put("customer"+i, customer);
                i++;
            }
    	}
    	catch(Exception e)
    	{
    		
    	}
        return customerMap;
    }
}
