package com.arthurtaborda.outfittery;

public interface EventHandler<T> {

    void handle(T event);
}
