package com.example.whereparty.presentation.model.concertAPI;

public class Venue {

    //private String displayName;
    private String uri;

    /*public String getDisplayName() {
        return displayName;
    }*/

    public String getUri() {
        return uri;
    }

    public Venue(/*String displayName,*/ String uri) {
        //this.displayName = displayName;
        this.uri = uri;
    }
}