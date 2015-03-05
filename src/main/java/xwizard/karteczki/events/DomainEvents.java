package xwizard.karteczki.events;

import java.util.LinkedList;
import java.util.List;

public class DomainEvents {

  static final List<Handler<? extends Event>> handlers = new LinkedList<Handler<? extends Event>>();;

  public static <T extends Event> void register(Handler<T> handler) {
    handlers.add(handler);
  }
  
  public static <T extends Event> void unregister(Handler<T> handler) {
    handlers.remove(handler);
  }
  
  public static <E extends Event> void raise(E event) {
    for (Handler handler : handlers) {
      if (handler.handles(event.getClass())) {
        handler.handle(event);
      }
    }
  }
}
