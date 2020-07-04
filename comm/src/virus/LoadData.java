package virus;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LoadData
{

    static JsonObject completeData = new JsonObject();

    public static JsonObject getCompleteData()
    {

        // ------------------- START OF vENDOR 1--------------------------------
        JsonObject vendor1 = new JsonObject();
        vendor1.addProperty("vendorId", "1");
        vendor1.addProperty("vendorName", "Lego");
        vendor1.addProperty("description", "Multibranded super market");
        vendor1.addProperty("address", "26, Bajanai Koil Street,Velachery,Chennai-600042");
        vendor1.addProperty("contact", "9393939393");
        vendor1.addProperty("holidaydate", "13Feb,14Feb");
        vendor1.addProperty("slotTimePeriod", "10");
        vendor1.addProperty("regWorkingDays", "Mon-Sat");
        vendor1.addProperty("slotOpeningTime", "9");
        vendor1.addProperty("slotClosingTime", "7");
        vendor1.addProperty("totalSlots", "54");
        vendor1.addProperty("remainingSlots", "54");
        vendor1.addProperty("date", getTime().getTimeInMillis());
        vendor1.addProperty("exclusionTime", "1to2PM");
        vendor1.addProperty("allotedSlot", "");

        JsonArray categoryArray = new JsonArray();
        //item 1
        JsonObject object1 = new JsonObject();
        object1.addProperty("type", "oil");
        object1.addProperty("category", "grocery");
        object1.addProperty("item", "idyam");
        object1.addProperty("itemprice", "100");
        object1.addProperty("unit", "litre");
        object1.addProperty("instock", "100");

        //item 2
        JsonObject object2 = new JsonObject();
        object2.addProperty("type", "rice");
        object2.addProperty("category", "grocery");
        object2.addProperty("item", "rice");
        object2.addProperty("itemprice", "60");
        object2.addProperty("unit", "kg");
        object2.addProperty("instock", "1000");

        categoryArray.add(object1);
        categoryArray.add(object2);

        vendor1.add("items", categoryArray);

        // ------------------- END OF vENDOR 1--------------------------------

        // ------------------- START OF vENDOR 1--------------------------------
        JsonObject vendor2 = new JsonObject();
        vendor2.addProperty("vendorId", "2");
        vendor2.addProperty("vendorName", "Grace");
        vendor2.addProperty("description", "Multibranded super market");
        vendor2.addProperty("address", "89, 6th Street,Kamaraj Nagar, Korattur,Chennai-600080");
        vendor2.addProperty("contact", "0447878783");
        vendor2.addProperty("holidaydate", "17Feb");
        vendor2.addProperty("slotTimePeriod", "10");
        vendor2.addProperty("regWorkingDays", "Mon-Fri");
        vendor2.addProperty("slotOpeningTime", "9");
        vendor2.addProperty("slotClosingTime", "6");
        vendor2.addProperty("totalSlots", "48");
        vendor2.addProperty("remainingSlots", "48");
        vendor2.addProperty("date", getTime().getTimeInMillis());
        vendor2.addProperty("exclusionTime", "1to2PM");
        vendor2.addProperty("allotedSlot", "");

        JsonArray categoryArray1 = new JsonArray();
        //item 1
        JsonObject v2i1 = new JsonObject();
        v2i1.addProperty("type", "oil");
        v2i1.addProperty("category", "grocery");
        v2i1.addProperty("item", "idyam");
        v2i1.addProperty("itemprice", "100");
        v2i1.addProperty("unit", "litre");
        v2i1.addProperty("instock", "100");

        //item 2
        JsonObject v2i2 = new JsonObject();
        v2i2.addProperty("type", "rice");
        v2i2.addProperty("category", "grocery");
        v2i2.addProperty("item", "rice");
        v2i2.addProperty("itemprice", "60");
        v2i2.addProperty("unit", "kg");
        v2i2.addProperty("instock", "1000");

        categoryArray1.add(v2i1);
        categoryArray1.add(v2i2);

        vendor2.add("items", categoryArray1);

        completeData.add("Lego", vendor1);
        completeData.add("Grace", vendor2);

        return completeData;
    }

    public static Calendar getTime()
    {
        Calendar date = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date;
    }
}
