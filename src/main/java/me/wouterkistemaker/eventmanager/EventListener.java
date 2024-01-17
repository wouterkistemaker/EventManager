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

import jakarta.annotation.Nonnull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents an interface that is used to indicate
 * whether a class contains methods that
 * listen for the calls of an {@link Event}
 */
public interface EventListener {
    /**
     * Iterates through all methods declared within this class, then returns a collection
     * containing references to all valid event handlers within this class. Modify at your own risk.
     *
     * @return A collection of valid handler references within this class
     * @since 1.1
     */
    @Nonnull
    default Collection<HandlerReference> getHandlerReferences() {
        final List<HandlerReference> references = new ArrayList<>();

        for (final Method method : getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventTag.class)) continue;
            if (method.getParameterCount() != 1) continue;
            if (!Handleable.class.isAssignableFrom(method.getParameterTypes()[0])) continue;

            references.add(new HandlerReference(this, method));
        }

        return references;
    }
}
