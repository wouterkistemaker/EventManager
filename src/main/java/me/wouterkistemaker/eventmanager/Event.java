package me.wouterkistemaker.eventmanager;

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

import jakarta.annotation.Nullable;

/**
 * The default implementation of {@link Handleable}. Events can be called to an event manager
 * in order to be processed by listeners.
 *
 * @see Handleable
 * @see EventListener
 */
public abstract class Event implements Handleable {
    /**
     * Creates a new event with no specified cause.
     */
    protected Event() {
        this(null);
    }

    /**
     * Creates a new event.
     *
     * @param cause The cause of this event
     * @since 1.1
     */
    protected Event(@Nullable Handleable cause) {
        this.cause = cause;
    }

    //
    // Variables
    //

    /**
     * The cause of this event.
     *
     * @since 1.1
     */
    @Nullable
    protected final Handleable cause;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @since 1.1
     */
    @Override
    @Nullable
    public Handleable getCause() {
        return cause;
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
