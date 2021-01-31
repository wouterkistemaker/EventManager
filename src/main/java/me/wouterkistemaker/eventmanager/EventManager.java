package me.wouterkistemaker.eventmanager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public final class EventManager {

    private List<EventListener> listeners;
    private EventLoop eventLoop;
    private Thread eventLoopThread;

    public EventManager() {
        this.listeners = new ArrayList<>();
        this.eventLoop = new EventLoop(this);
    }

    /**
     * This method initializes the {@link Thread} that is used
     * with {@link Runnable} = {@link EventLoop}
     * <p>
     * After initializing the {@link Thread} it calls the 'start()'
     * of the thread which will start the {@link Runnable} to run.
     */
    public void start() {
        this.eventLoopThread = new Thread(this.eventLoop);
        this.eventLoopThread.start();
    }

    /**
     * This method checks whether the {@link Thread} of the {@link EventLoop}
     * is still running or not. If the {@link Thread} is still running,
     * the method accepts this, and doesn't do anything.
     * <p>
     * If the {@link Thread} is not running, it calls the forceStop()
     * method that simply closes the {@link Thread} and resets the
     * value that is used in this {@link Class}
     */
    public void stop() {
        if (!eventLoop.getEventQueue().isEmpty()){
            return;
        }
        forceStop();
    }

    /**
     * This method instantly shuts down the {@link Thread} of the {@link EventLoop}
     * without checking of it is still running or not.
     * <p>
     * After interrupting, it resets the value of the {@link Thread} that
     * is being used in  this {@link Class}
     */
    public void forceStop() {
        this.eventLoopThread.interrupt();
        this.eventLoopThread = null;
    }

    /**
     * This method registers a new {@link EventListener} to the {@link List}
     * of {@link EventListener}
     *
     * @param eventListener The {@link EventListener} that is being registered
     *                      to the {@link List} in this class.
     */
    public void register(EventListener eventListener) {
        this.listeners.add(eventListener);
    }


    /**
     * This method executes an {@link Event}. By doing so, the method
     * iterates over all registered {@link EventListener} and
     * checks whether these {@link EventListener} contains a method
     * that can be used to invoke on the called {@link Event}
     * <p>
     * If there is no method that can be invoked on the called {@link Event}
     *
     * @param event An instance of an {@link Class} that is a superclass
     *              from {@link Event}
     */
    protected <T extends Event> void executeEvent(T event) {
        Class<? extends Event> eventClass = event.getClass();

        for (EventListener listener : this.listeners) {
            for (Method m : listener.getClass().getDeclaredMethods()) {
                if (isGoodMethod(m)) { // So we are sure it is a Listener Method
                    if (m.getParameters()[0].getType().isAssignableFrom(eventClass)) { // We are sure the parameter is for this specific event
                        try {
                            m.invoke(listener, event);
                        } catch (IllegalAccessException | InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is used to call an {@link Event}, which means that
     * the {@link Event} is added to the {@link Queue} so that, in case of overloads
     * the {@link Event}s will be executed in the right order
     *
     * @param event Instance of the event to be added to the {@link Queue}
     *              in the {@link EventLoop}
     */
    public <T extends Event> void callEvent(T event) {
        eventLoop.queue(event);
    }

    /**
     * Simple method to check whether the given {@link Method}
     * is a method that can be invoked on a called {@link Event}
     *
     * @param m The method to check whether it can be
     *          invoked on a called {@link Event}
     * @return <code>true</code> the method can be invoked on a called {@link Event}
     * <code>false</code> otherwise
     */
    private boolean isGoodMethod(final Method m) {
        boolean b = m.getParameters().length == 1 && m.isAnnotationPresent(EventTag.class);
        return m.getParameters().length == 1 && m.isAnnotationPresent(EventTag.class);
    }
}
