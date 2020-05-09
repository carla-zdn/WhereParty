package com.example.whereparty.presentation.model.areaAPI;

public class Location {

    private MetroArea metroArea;

    public MetroArea getMetroArea() {
        return metroArea;
    }

    public void setMetroArea(MetroArea metroArea) {
        this.metroArea = metroArea;
    }

    public Location(MetroArea metroArea) {
        this.metroArea = metroArea;
    }
}
