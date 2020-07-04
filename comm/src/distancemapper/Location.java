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

public class Location
{
    private double latitude1;
    private double longitude1;
    private double latitude2;
    private double longitude2;

    public Location()
    {
    }

    public double distanceTo()
    {
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        double lat1 = Math.toRadians(this.latitude1);
        double lon1 = Math.toRadians(this.longitude1);
        double lat2 = Math.toRadians(this.latitude2);
        double lon2 = Math.toRadians(this.longitude2);

        double angle = Math
                .acos(Math.sin(lat1) * Math.sin(lat2)
                        + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }

    public double distance(String[] latOwner, String[] latSuccessor)
    {
        latitude1 = Double.parseDouble(latOwner[0]);
        longitude1 = Double.parseDouble(latOwner[1]);
        latitude2 = Double.parseDouble(latSuccessor[0]);
        longitude2 = Double.parseDouble(latSuccessor[1]);
        double distance = distanceTo();
        return distance;
    }
}
