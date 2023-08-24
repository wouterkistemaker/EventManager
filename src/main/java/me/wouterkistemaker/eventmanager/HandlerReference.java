package me.wouterkistemaker.eventmanager;

import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A reference object to an event handler method. Handler references are transient
 * objects used internally by the event manager.
 *
 * @param listener The event listener object
 * @param method   The event handler method
 * @author themrsung
 * @since 1.1
 */
public record HandlerReference(@Nonnull EventListener listener, @Nonnull Method method) {
    /**
     * Returns the execution priority of this event handler. If not priority is specified,
     * this will return {@link HandlerPriority#MEDIUM}.
     *
     * @return The execution priority of this event handler
     */
    @Nonnull
    public HandlerPriority priority() {
        try {
            return method.getAnnotation(EventTag.class).priority();
        } catch (final NullPointerException e) {
            return HandlerPriority.MEDIUM; // Fallback value in case no annotation is found
        }
    }

    /**
     * Returns whether this event handler accepts the provided event.
     *
     * @param event The event of which to check for acceptance.
     * @param <E>   The type of event to check for acceptance
     * @return {@code true} if this event handler accepts the event
     */
    public <E extends Handleable> boolean accepts(@Nonnull E event) {
        try {
            return method.getParameterTypes()[0].isAssignableFrom(event.getClass());
        } catch (final IndexOutOfBoundsException e) {
            throw new RuntimeException("An invalid method was referenced.", e);
        }
    }

    /**
     * Called to handle the event. This handles the invocation of the event handler.
     *
     * @param event The event of which to handle
     * @param <E>   The type of event to handle
     */
    public <E extends Handleable> void handle(@Nonnull E event) {
        try {
            method.invoke(listener, event);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException("The handler method is inaccessible from this thread.", e);
        } catch (final InvocationTargetException e) {
            throw new RuntimeException("The handler method threw an exception.", e);
        } catch (final Throwable e) {
            throw new RuntimeException("Unknown error.", e);
        }
    }
}
