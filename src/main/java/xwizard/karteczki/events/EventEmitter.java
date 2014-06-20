package xwizard.karteczki.events;

import java.util.EventObject;

public interface EventEmitter {
  void emit(EventObject event);
}
