package me.wouterkistemaker.eventmanager;

import jakarta.annotation.Nonnull;

/**
 * An example listener.
 * @see ExampleEvent
 */
public class ExampleListener implements EventListener {
    /**
     * Called to handle the example event.
     * @param event The event being called
     */
    @EventTag(priority = HandlerPriority.MEDIUM)
    public void onExampleEvent(@Nonnull ExampleEvent event) {
        // Prints the event's description to the console
        System.out.println(event.getDescription());
    }
}
