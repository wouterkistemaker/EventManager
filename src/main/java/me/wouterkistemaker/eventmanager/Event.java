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

/**
 * Represents an Event, each Event requires a getDescription() function
 */
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
