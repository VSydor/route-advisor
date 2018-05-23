package com.vs.routeadviser.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Sets;
import com.vs.routeadviser.model.Advice;
import com.vs.routeadviser.model.Category;
import com.vs.routeadviser.model.Place;

public class RouteHelper {

    public List<String> buildRoute(List<Advice> advices) {
        Advice startingPoint = getMostRelevant(advices);
        return buildRoute(startingPoint.getPlace(), toPlaces(advices), new DistanceHelper(), new LinkedList<String>());
    }

    private Advice getMostRelevant(List<Advice> advices) {
        if (advices == null || advices.isEmpty()) {
            return null;
        }
        Collections.sort(advices, new Comparator<Advice>() {
            @Override
            public int compare(Advice a1, Advice a2) {
                return a2.getScore() - a1.getScore();
            }

        });
        return advices.get(0);
    }

    private List<Place> toPlaces(List<Advice> advices) {
        List<Place> places = new ArrayList<>();
        for (Advice a : advices) {
            places.add(a.getPlace());
        }
        return places;
    }

    private List<String> buildRoute(Place start, List<Place> placesToGo, DistanceHelper distanceHelper, List<String> route) {
        if (start == null || placesToGo == null || placesToGo.isEmpty()) {
            return route;
        }
        
        // To avoid pointing to self
        if (placesToGo.contains(start)) {
            placesToGo.remove(start);
        }
        
        Place nextPlace = distanceHelper.getNearest(start, placesToGo);
        // route is built
        if (nextPlace == null) {
            return route;
        }
        placesToGo.remove(nextPlace);
        route.add(start.getName() + " >> " + nextPlace.getName());
        
        return buildRoute(nextPlace, placesToGo, distanceHelper, route);
    }

    public static void main(String[] args) {

        List<Place> places = new ArrayList<Place>();

        // Should use Google Maps API here (requires google API key)
        
        // Used https://www.gps-coordinates.org/ to collect coordinates data
        Place casaBatllo = new Place("Casa Batllo", 41.3916384, 2.164769799999931, Category.CULTURE, Category.ARCHITECTURE, Category.ART, Category.OUTDOOR);
        casaBatllo.addTags(Sets.newHashSet("bones", "Gaudi"));
        Place sagradaFamilia = new Place("Sagrada Familia", 41.4036299, 2.1743558000000576, Category.CULTURE, Category.ARCHITECTURE, Category.ART, Category.HISTORY);
        sagradaFamilia.addTags(Sets.newHashSet("church of saint family", "gothic", "basilica"));
        Place campNou = new Place("Camp Nou", 41.380898, 2.122820, Category.ARCHITECTURE, Category.SPORT, Category.OUTDOOR);
        campNou.addTags(Sets.newHashSet("footbal", "Barca", "Messi"));
        Place parkDeMontjuic = new Place("Parc de Montjuic", 41.3635139, 2.157303299999967, Category.ARCHITECTURE, Category.OUTDOOR, Category.HISTORY, Category.RECREATIONAL);
        parkDeMontjuic.addTags(Sets.newHashSet("park", "nature", "scenery", "fresh air"));
        Place parkGuel = new Place("Park Guell", 41.4144948, 2.1526945000000524, Category.ARCHITECTURE, Category.OUTDOOR, Category.RECREATIONAL);
        parkGuel.addTags(Sets.newHashSet("fountain", "green", "scenery"));
        Place lasRamblas = new Place("Las Ramblas", 41.3815486, 2.1730972000000293, Category.ARCHITECTURE, Category.OUTDOOR, Category.RECREATIONAL);
        lasRamblas.addTags(Sets.newHashSet("night time", "night life", "disco", "bar"));
        Place bogatelliBeach = new Place("Bogatell Beach", 41.3943735, 2.2070109, Category.OUTDOOR, Category.RECREATIONAL);
        bogatelliBeach.addTags(Sets.newHashSet("beach", "sun", "sand"));
        Place portVell = new Place("Port Vell", 41.3751254, 2.180829200000062, Category.OUTDOOR, Category.RECREATIONAL);
        portVell.addTags(Sets.newHashSet("marine", "seagull", "ship", "sail"));
        Place museoPicasso = new Place("Museo Picasso", 41.385216, 2.1808926999999585, Category.ART, Category.HISTORY);
        museoPicasso.addTags(Sets.newHashSet("Picasso", "art", "gallery"));
        Place arcDeTriomf = new Place("Arc de Triomf", 41.3910524, 2.180644900000061, Category.ART, Category.ARCHITECTURE, Category.HISTORY, Category.OUTDOOR);
        arcDeTriomf.addTags(Sets.newHashSet("scenery", "park", "green", "arc"));

        places.add(casaBatllo);
        places.add(sagradaFamilia);
        places.add(campNou);
        places.add(parkDeMontjuic);
        places.add(parkGuel);
        places.add(lasRamblas);
        places.add(bogatelliBeach);
        places.add(portVell);
        places.add(museoPicasso);
        places.add(arcDeTriomf);
        
        List<Advice> advised = new AdviceHelper().advisePlaces(
                Arrays.asList(Category.OUTDOOR, Category.ARCHITECTURE, Category.RECREATIONAL), 
                Arrays.asList("Gaudi", "beach", "footbal"), places);

        System.out.println(new RouteHelper().buildRoute(advised));
    }

}
