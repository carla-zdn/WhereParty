package com.example.whereparty;

import java.util.List;

public class Event {

    private String displayName;
    private String type;
    private String uri;
    private Double popularity;
    private Start start;
    private List<Performance> performance;
    private Venue venue;

    public String getDisplayName() {
        return displayName;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Start getStart() {
        return start;
    }

    public List<Performance> getPerformance() {
        return performance;
    }

    public Venue getVenue() {
        return venue;
    }
}
