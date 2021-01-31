package me.wouterkistemaker.eventmanager;

import java.util.LinkedList;
import java.util.Queue;

public final class EventLoop implements Runnable {

    private EventManager eventManager;
    private Queue<Event> eventQueue;

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
    public void run() {
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

    public Queue<Event> getEventQueue() {
        return eventQueue;
    }

    public <T extends Event> void queue(T event) {
        eventQueue.add(event);
    }
}

