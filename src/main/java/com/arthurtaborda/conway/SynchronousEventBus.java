package com.arthurtaborda.conway;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.runAsync;

public class SynchronousEventBus implements EventBus {

    private final Map<Class, List<EventHandler>> eventHandlers;

    public SynchronousEventBus() {
        eventHandlers = new HashMap<>();
    }

    @Override
    public <T> void subscribe(Class<T> clazz, EventHandler<T> eventHandler) {
        List<EventHandler> handlers = this.eventHandlers.computeIfAbsent(clazz, c -> new LinkedList<>());
        handlers.add(eventHandler);
    }

    @Override
    public <T> void send(T event) {
        List<EventHandler> handlers = eventHandlers.get(event.getClass());
        if (handlers != null) {
            handlers.forEach(handler -> handler.handle(event));
        }
    }
}
