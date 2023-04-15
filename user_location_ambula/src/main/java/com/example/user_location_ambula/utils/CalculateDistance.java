package com.example.user_location_ambula.utils;

public class CalculateDistance {

    /*
    * Utility function for determining the distance between the points from (0,0)
    */

    public Double getDistance(double lat1, double lon1, double lat2, double lon2){
        double R = 6371.0; // radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }
}
