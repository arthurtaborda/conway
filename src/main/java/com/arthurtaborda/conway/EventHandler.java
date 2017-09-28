package com.arthurtaborda.conway;

public interface EventHandler<T> {

    void handle(T event);
}
