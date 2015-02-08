/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xwizard.karteczki.events;

import java.util.LinkedList;
import java.util.List;

public class DomainEvents {

  private static final List<Handler<? extends Event>> handlers = new LinkedList<Handler<? extends Event>>();;

  public static <T extends Event> void register(Handler<T> handler) {
    handlers.add(handler);
  }
  
  public static <T extends Event> void raise(T event) {
    for (Handler handler : handlers) {
      if (handler.handles(event.getClass())) {
        handler.handle(event);
      }
    }
  }
}
