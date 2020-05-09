package com.example.whereparty.presentation.model;

public class Performance {

    private String displayName;
    private Artist artist;

    public String getDisplayName() {
        return displayName;
    }

    public Artist getArtist() {
        return artist;
    }

    public Performance(String displayName, Artist artist) {
        this.displayName = displayName;
        this.artist = artist;
    }
}
