package me.wouterkistemaker.eventmanager;

import java.util.LinkedList;
import java.util.Queue;

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
 * Package-private class that contains the {@link Queue<Event>} of {@link Event Events}
 * that are being called through/by this {@link EventManager}. The main
 * purpose of this class, is to make sure that {@link Event Events} are processed
 * in the right order, especially when there is delay caused by any reason
 */
final class EventLoop implements Runnable {

    private final EventManager eventManager;
    private final Queue<Event> eventQueue;

    /**
     * Default constructor to construct the {@link EventLoop}.
     * Never call this constructor, it is already being done
     * for you once you setup the {@link EventManager}
     *
     * @param eventManager the {@link EventManager} class to
     *                     setup the me.wouterkistemaker.eventmanager.EventLoop
     */
    EventLoop(EventManager eventManager) {
        this.eventManager = eventManager;
        this.eventQueue = new LinkedList<>();
    }

    /**
     * Run method implemented from {@link Runnable}.
     * This method keeps cleaning the {@link Queue} and executing
     * the tasks of each {@link Event} in the {@link Queue}
     */
    @Override
    public final void run() {
        try {
            for (; ; ) {
                do {
                    Event e = this.eventQueue.poll();
                    if (e == null) continue;
                    this.eventManager.executeEvent(e);
                } while (!this.eventQueue.isEmpty());
            }
        } catch (Exception e){
            e.printStackTrace();
            run();
        }
    }

    /**
     * @return {@link Queue<Event>} containing all not yet processed {@link Event Events}
     */
    protected final Queue<Event> getEventQueue() {
        return eventQueue;
    }

    /**
     * Adds a new {@link Event} to the {@link Queue<Event>}
     * @param event {@link Event} to add to the {@link Queue<Event>}
     * @param <T> any subclass of {@link Event}
     */
    protected final <T extends Event> void queue(T event) {
        eventQueue.add(event);
    }
}

