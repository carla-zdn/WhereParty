package com.example.whereparty.presentation.model.areaAPI;

import java.util.List;

public class Results {

    private List<Location> location;

    public List<Location> getLocation() {
        return location;
    }

    public Results(List<Location> location) {
        this.location = location;
    }
}
