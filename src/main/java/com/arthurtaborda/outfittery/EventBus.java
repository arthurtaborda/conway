package com.arthurtaborda.outfittery;

public interface EventBus {

    <T> void subscribe(Class<T> clazz, EventHandler<T> eventHandler);

    <T> void send(T event);
}
