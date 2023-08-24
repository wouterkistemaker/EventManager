package me.wouterkistemaker.eventmanager;

/**
 * The execution priority of an event handler.
 *
 * @author themrsung
 * @see EventTag
 * @see Event
 * @see EventManager
 * @since 1.1
 */
public enum HandlerPriority {
    /**
     * <b>A reserved priority for system initialization tasks.</b>
     */
    INITIALIZATION,

    //
    // LOWER PRIORITIES ARE EXECUTED SOONER
    //

    PREEMPTIVE,
    PRE_EARLY,
    EARLY,
    POST_EARLY,
    MEDIUM,
    PRE_LATE,
    LATE,
    POST_LATE,
    PERMISSIVE,

    //
    // HIGHER PRIORITIES ARE EXECUTED LATER
    //

    /**
     * <b>A reserved priority for system termination tasks.</b>
     */
    TERMINAL
}
