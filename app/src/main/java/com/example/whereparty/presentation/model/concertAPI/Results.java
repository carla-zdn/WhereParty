package com.example.whereparty.presentation.model.concertAPI;

import java.util.List;

public class Results {

    private List<Event> event;

    public List<Event> getEvent() {
        return event;
    }

    public Results(List<Event> event) {
        this.event = event;
    }
}
