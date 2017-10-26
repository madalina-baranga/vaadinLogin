package com.example.login.events;

import com.google.common.eventbus.EventBus;
import com.vaadin.spring.annotation.SpringComponent;

/**
 * Wrapper for Guava event bus. Declares utility static methods for main actions.
 */
@SpringComponent
public class LoginEventBus {

    private final static EventBus eventBus = new EventBus();

    public static void register(final Object eventListener){
        eventBus.register(eventListener);
    }

    public static void unregister(final Object eventListener){
        eventBus.unregister(eventListener);
    }

    public static void post(final Object event){
        eventBus.post(event);
    }
}
