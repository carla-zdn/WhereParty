package com.example.whereparty.presentation.model.concertAPI;

public class Venue {

    private MetroArea metroArea;
    private String displayName;
    private String uri;

    public String getDisplayName() {
        return displayName;
    }

    public MetroArea getMetroArea() {
        return metroArea;
    }

    public String getUri() {
        return uri;
    }

    public Venue(MetroArea metroArea,String displayName, String uri) {
        this.metroArea = metroArea;
        this.displayName = displayName;
        this.uri = uri;
    }
}