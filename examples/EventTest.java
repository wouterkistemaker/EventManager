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
public class EventTest {

    public static void main(String[] args) {
        final EventManager manager = new EventManager();
        manager.start();

        manager.register(new MyEventListener());
        manager.callEvent(new MyEvent()); // Console successfully prints 'My event was called!'
    }

    private static final class MyEventListener implements EventListener {

        @EventTag
        public void onEventCall(MyEvent event) {
            System.out.println("My event was called!");
        }
    }

    private static final class MyEvent extends Event {
        @Override
        public String getDescription() {
            return "My very own event";
        }
    }
}
