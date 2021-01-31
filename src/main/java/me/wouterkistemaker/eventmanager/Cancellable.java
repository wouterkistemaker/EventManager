package me.wouterkistemaker.eventmanager;

public interface Cancellable {


    /**
     * This method is called to check whether
     * an {@link Event} was cancelled inside
     * a {@link EventListener}
     *
     * @return <code>true</code> if the event was cancelled
     * <code>false</code> otherwise
     */
    boolean isCancelled();

    /**
     * This method is called to cancel an {@link Event}
     * preferably in an {@link EventListener}
     *
     * @param b whether the {@link Event} should be flagged
     *          as cancelled or not
     *          <code>true</code> cancels the {@link Event}
     *          <code>false</code> otherwise
     */

    void setCancelled(boolean b);
}
