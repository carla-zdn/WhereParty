package com.example.whereparty.presentation.model;

import java.util.List;

public class Event {

    private String displayName;
    //private String type;
    private String uri;
    //private Double popularity;
    private Start start;
    private List<Performance> performance;
    private Venue venue;

    public String getDisplayName() {
        return displayName;
    }

    /*public String getType() {
        return type;
    }*/

    public String getUri() {
        return uri;
    }

    /*public Double getPopularity() {
        return popularity;
    }*/

    public Start getStart() {
        return start;
    }

    public List<Performance> getPerformance() {
        return performance;
    }

    public Venue getVenue() {
        return venue;
    }

    public Event(String displayName/*, String type*/, String uri/*, Double popularity*/, Start start, List<Performance> performance, Venue venue) {
        this.displayName = displayName;
        //this.type = type;
        this.uri = uri;
        //this.popularity = popularity;
        this.start = start;
        this.performance = performance;
        this.venue = venue;
    }
}
