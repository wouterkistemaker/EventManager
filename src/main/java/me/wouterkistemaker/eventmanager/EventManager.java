package me.wouterkistemaker.eventmanager;

import jakarta.annotation.Nonnull;

import java.util.*;

/*
  Copyright (C) 2020-2021, Wouter Kistemaker.
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published
  by the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.
  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

/**
 * An event manager. Event managers handle the lifecycle of events and listeners.
 * This implementation is completely synchronous, meaning it has only one thread.
 * (the instance itself is a thread) Thus, concurrency issues are not a concern.
 *
 * @see Event
 * @see EventListener
 */
public final class EventManager extends Thread {
    //
    // Variables
    //

    /**
     * The list of handler references.
     *
     * @since 1.1
     */
    @Nonnull
    private final List<HandlerReference> handlers = new ArrayList<>();

    /**
     * The queue of unprocessed events.
     *
     * @since 1.1
     */
    @Nonnull
    private final Deque<Handleable> eventQueue = new ArrayDeque<>();

    //
    // Methods
    //

    /**
     * Starts this event manager, entering an infinite loop processing events until interrupted.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            // Poll next event and check for null
            final Handleable event = eventQueue.pollFirst();
            if (event == null) continue;

            // Iterate through copied list of handlers to prevent concurrency issues
            for (final HandlerReference handler : List.copyOf(handlers)) {
                // Continue if the handler rejects the event
                if (!handler.accepts(event)) continue;

                // Handles the event
                handler.handle(event);
            }
        }
    }

    /**
     * Deprecated method. This delegates to {@link #interrupt()}.
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public void forceStop() {
        interrupt();
    }

    /**
     * Registers an event listener to this event manager.
     *
     * @param eventListener The listener to register to this event manager
     */
    public void register(@Nonnull EventListener eventListener) {
        handlers.addAll(eventListener.getHandlerReferences());

        // Preemptively sort by priority
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * Unregisters an event listener from this event manager.
     *
     * @param eventListener The listener to unregister from this event manager
     * @since 1.1
     */
    public void unregister(@Nonnull EventListener eventListener) {
        handlers.removeAll(eventListener.getHandlerReferences());
    }

    /**
     * Calls an event to this event manager, adding it to the event queue.
     * The event will be processed on a FILO (first-in-last-out) basis.
     *
     * @param event The event to call to this manager
     * @param <T>   The type of event to call to this manager
     */
    public <T extends Handleable> void callEvent(@Nonnull T event) {
        eventQueue.offerLast(event);
    }

    /**
     * Priority calls an event to this event manager, adding it to the head of the event queue.
     * The event will be processed on a FIFO (first-in-first-out) basis.
     *
     * @param event The event to call to this manager
     * @param <T>   The type of event to call to this manager
     */
    public <T extends Handleable> void priorityCallEvent(@Nonnull T event) {
        eventQueue.addFirst(event);
    }
}
