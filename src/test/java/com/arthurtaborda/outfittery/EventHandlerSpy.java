package com.arthurtaborda.outfittery;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerSpy implements EventHandler {

    private final List eventsSent;

    public EventHandlerSpy() {
        eventsSent = new ArrayList<>();
    }

    @Override
    public void handle(Object event) {
        eventsSent.add(event);
    }

    public List getEventsSent() {
        return eventsSent;
    }
}
