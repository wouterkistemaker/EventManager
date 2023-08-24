package me.wouterkistemaker.eventmanager;

import jakarta.annotation.Nullable;

/**
 * An example event to demonstrate the capabilities of this library.
 * @see ExampleListener
 */
public class ExampleEvent extends Event {
    /**
     * Creates a new event with no specified cause.
     */
    public ExampleEvent() {
    }

    /**
     * Creates a new event.
     * @param cause The cause of this event
     */
    public ExampleEvent(@Nullable Handleable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "This is an example event.";
    }
}
