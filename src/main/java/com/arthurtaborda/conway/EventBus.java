package com.arthurtaborda.conway;

public interface EventBus {

    <T> void subscribe(Class<T> clazz, EventHandler<T> eventHandler);

    <T> void send(T event);
}
