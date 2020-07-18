//**********************************************************************
// Copyright (c) 2020 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package distancemapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetNearbyDistance
{
    private static final String OPEN_STREET_MAP_API = "https://nominatim.openstreetmap.org/search?q=";
    private static final String QUERY_PARAM = "&format=json&zoom=18&polygon=1&addressdetails=1";

    public Map<String, Map<String, Double>> getLocationDetails(List<String> addressList)
    {
        try
        {
            JSONParser parser = new JSONParser();
            Map<String, String> latLongMap = new HashMap<String, String>();
            List<String> areas = new ArrayList<String>();
            for (String address : addressList)
            {
                String[] addressSplit = address.split(",");
                int length = addressSplit.length - 1;
                int maxLen = length - 2;
                String query = "";
                while (length > maxLen)
                {
                    String area = addressSplit[length].trim();
                    String updatedArea = area.replaceAll("\\s", "%20");
                    query += updatedArea + "+";
                    length--;
                }

                query.substring(0, query.length() - 1);
                String request = OPEN_STREET_MAP_API + query.substring(0, query.length() - 1) + QUERY_PARAM;
                URL obj = new URL(request);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK)
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null)
                    {
                        response.append(inputLine);
                    }

                    in.close();
                    try
                    {
                        Object object = parser.parse(response.toString());
                        JSONArray records = (JSONArray) object;
                        JSONObject jsonObject = (JSONObject) records.get(0);
                        String lat = jsonObject.get("lat").toString();
                        String lon = jsonObject.get("lon").toString();
                        latLongMap.put(address, lat + "," + lon);
                        areas.add(address);

                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("GET not worked for request " + request);
                }
            }
            ComputeShortDistance shortDistance = new ComputeShortDistance();
            shortDistance.setAreas(areas);
            shortDistance.setAreaMap(latLongMap);
            shortDistance.findClosestLocation();
            return shortDistance.getSortedMap();
        }
        catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }
}
