package com.example.whereparty.presentation.model.concertAPI;

public class MetroArea {

    private String displayName;
    private Country country;
    private int id;

    public Country getCountry() {
        return country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return Integer.toString(id);
    }

    public MetroArea(Country country, String displayName, int id) {
        this.country = country;
        this.displayName = displayName;
        this.id = id;
    }
}
