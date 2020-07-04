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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ComputeShortDistance
{
    Map<String, String> latLongMap = new HashMap<String, String>();
    List<String> areaList = new ArrayList<String>();
    Map<String, Map<String, Double>> closerLocation = new HashMap<String, Map<String, Double>>();
    Map<String, Map<String, Double>> newMap = new HashMap<String, Map<String, Double>>();

    public void setAreaMap(Map<String, String> areaMap)
    {
        this.latLongMap = areaMap;
    }

    public void setAreas(List<String> areas)
    {
        this.areaList = areas;
    }

    public Map<String, Map<String, Double>> getSortedMap()
    {
        return newMap;
    }

    public void findClosestLocation()
    {
        Map<String, Double> map;
        for (String area : areaList)
        {
            String coordinates = latLongMap.get(area);
            map = new HashMap<String, Double>();
            for (Map.Entry<String, String> mapEntry : latLongMap.entrySet())
            {
                if (!mapEntry.getKey().equals(area))
                {
                    String neighborCoordinate = mapEntry.getValue();
                    double distance = new Location().distance(coordinates.split(","), neighborCoordinate.split(","));
                    map.put(mapEntry.getKey(), distance);
                }
            }
            closerLocation.put(area, map);
        }
        for (Entry<String, Map<String, Double>> entry : closerLocation.entrySet())
        {
            Map<String, Double> radius = entry.getValue();
            List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(radius.entrySet());

            // Sort the list 
            Collections.sort(list, new Comparator<Map.Entry<String, Double>>()
            {
                public int compare(Map.Entry<String, Double> o1,
                                   Map.Entry<String, Double> o2)
                {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });
            // put data from sorted list to hashmap  
            HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
            for (Map.Entry<String, Double> aa : list)
            {
                temp.put(aa.getKey(), aa.getValue());
            }
            newMap.put(entry.getKey(), temp);
        }
    }
}
