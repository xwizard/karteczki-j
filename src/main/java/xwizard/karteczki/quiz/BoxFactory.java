package xwizard.karteczki.quiz;

import java.util.UUID;

import com.google.common.eventbus.EventBus;

public class BoxFactory {
  
  private final EventBus eventBus;
  
  public BoxFactory(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public Box create(UUID id, EventBus eventBus) {
    return new Box(id, eventBus);
  }
  
  public Box createWithRandomId() {
    return create(UUID.randomUUID(), eventBus);
  }
}
