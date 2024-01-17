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
 * A demo program to facilitate the onboarding of this library.
 */
public class EventTest {
    /**
     * The event manager instance of this program.
     */
    private static final EventManager manager = new EventManager();

    /**
     * The entry point of this program.
     * @param args Ignored
     */
    public static void main(String[] args) {
        // Register listener
        manager.register(new ExampleListener());

        // Start event manager
        manager.start();

        // Call event
        manager.callEvent(new ExampleEvent());
    }
}
