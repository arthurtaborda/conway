package com.arthurtaborda.outfittery;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AsyncEventBusTest {

    @Test
    public void whenSingleSubscribe_getEvent() {
        AsyncEventBus eventBus = new AsyncEventBus();
        Object event = new Object();
        EventHandlerSpy eventHandler = new EventHandlerSpy();

        eventBus.subscribe(Object.class, eventHandler);
        eventBus.send(event);

        assertThat(eventHandler.getEventsSent()).hasSize(1).contains(event);
    }

    @Test
    public void whenMultipleSubscribe_getEventInAll() {
        AsyncEventBus eventBus = new AsyncEventBus();
        Object event = new Object();
        EventHandlerSpy eventHandler1 = new EventHandlerSpy();
        EventHandlerSpy eventHandler2 = new EventHandlerSpy();

        eventBus.subscribe(Object.class, eventHandler1);
        eventBus.subscribe(Object.class, eventHandler2);
        eventBus.send(event);

        assertThat(eventHandler1.getEventsSent()).hasSize(1).contains(event);
        assertThat(eventHandler2.getEventsSent()).hasSize(1).contains(event);
    }
}
