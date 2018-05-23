package com.vs.routeadviser.helper;

import java.util.List;

import com.vs.routeadviser.model.Place;

public class DistanceHelper {

    public Place getNearest(Place from, List<Place> places) {
        if (places == null || places.isEmpty()) {
            return null;
        }
        // assuming first one is the closest
        Place nearest = places.get(0);
        double minDistance = distance(from, nearest);
        double d = 0;
        
        List<Place> toGo = places.subList(1, places.size());
        for (Place place : toGo) {
            d = distance(from, place);
            if (d < minDistance) {
                minDistance = d;
                nearest = place;
            }
        }
        return nearest;
    }

    private double distance(Place from, Place to) {
        return distance(from.getLatitude(), to.getLatitude(), from.getLongitude(), to.getLongitude());
    }

    private double distance(double lat1, double lat2, double lon1, double lon2) {
        return distance(lat1, lat2, lon1, lon2, 0.0, 0.0);
    }

    /**
     * Calculate distance between two points in latitude and longitude 
     * taking into account height difference. 
     * If you are not interested in height difference pass 0.0. Uses Haversine method as its base.
     * 
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * 
     * @returns Distance in Meters
     */
    private double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
