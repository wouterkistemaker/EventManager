package me.wouterkistemaker.eventmanager;

import jakarta.annotation.Nonnull;

import java.lang.annotation.*;

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
 * Simple annotation to help indicating
 * which methods are able to invoke
 * on a called {@link Event}
 * <p>
 * This annotation has no other use
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTag {
    /**
     * Returns the execution priority of this event handler.
     *
     * @return The execution priority of this event handler
     * @see HandlerPriority
     * @since 1.1
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.MEDIUM;
}
