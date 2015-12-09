package xwizard.karteczki.events;

import com.google.common.eventbus.EventBus;

public interface EventEmitter {
  void setEventBus(EventBus eventBus);
}
