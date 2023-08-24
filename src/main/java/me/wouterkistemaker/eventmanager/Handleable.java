package me.wouterkistemaker.eventmanager;

import jakarta.annotation.Nullable;

/**
 * A superinterface for classes which can be called to event managers to be processed.
 *
 * @author themrsung
 * @see Event
 */
public interface Handleable {
    /**
     * Returns the cause of this event. If no cause is specified, this will return {@code null}.
     *
     * @return The cause of this event if specified, {@code null} otherwise
     */
    @Nullable
    Handleable getCause();
}
