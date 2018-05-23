package com.vs.routeadviser.model;

public class Advice {

    private final int score;
    private final Place place;

    public Advice(int score, Place place) {
        this.score = score;
        this.place = place;
    }

    public int getScore() {
        return this.score;
    }

    public Place getPlace() {
        return this.place;
    }

    public String getName() {
        return this.place.getName();
    }

    @Override
    public String toString() {
        return "Advice [score=" + score + ", place=" + place.getName() + "]";
    }

}
