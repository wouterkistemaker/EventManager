package me.wouterkistemaker.eventmanager;

public abstract class Event {

    /**
     * Default constructor to construct
     * the {@link Object} of an {@link Event}
     */
    protected Event() {
    }

    /**
     * This method allows you to get the name
     * of the {@link Event}
     * <p>
     * This can be overridden by a superclass, but preferably not
     * because this would cause events being named quite differently
     * from another {@link Event} !
     *
     * @return <code>String</code> name of the {@link Event}
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    /**
     * Method to get the description of
     * a specific {@link Event}. Each {@link Event}
     * has its own description.
     *
     * @return <code>String</code> the description of the {@link Event}
     */
    public abstract String getDescription();

}
