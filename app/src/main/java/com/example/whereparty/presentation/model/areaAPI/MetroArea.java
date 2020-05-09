package com.example.whereparty.presentation.model.areaAPI;

public class MetroArea {

    private Country country;
    private String displayName;
    private int id;

    public Country getCountry() {
        return country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }

    public MetroArea(Country country, String displayName, int id) {
        this.country = country;
        this.displayName = displayName;
        this.id = id;
    }
}
