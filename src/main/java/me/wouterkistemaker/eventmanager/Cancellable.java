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
 * Represents an {@link Event} that has the option to be cancelled.
 * Cancelled {@link Event Events} may result in a different processing
 * by {@link EventListener EventListeners}
 */
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
