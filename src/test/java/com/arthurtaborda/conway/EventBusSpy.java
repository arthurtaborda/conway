package com.arthurtaborda.outfittery;

import java.util.ArrayList;
import java.util.List;

public class EventBusSpy implements EventBus {

    private final List eventsSent;

    public EventBusSpy() {
        eventsSent = new ArrayList<>();
    }

    @Override
    public <T> void subscribe(Class<T> clazz, EventHandler<T> eventHandler) {
        // does nothing
    }

    @Override
    public <T> void send(T event) {
        eventsSent.add(event);
    }

    public List getEventsSent() {
        return eventsSent;
    }

    public void clear() {
        eventsSent.clear();
    }
}
