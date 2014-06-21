package xwizard.karteczki.quiz;

import java.util.UUID;

import xwizard.karteczki.events.EventEmitter;

public class BoxFactory {
  private final EventEmitter eventEmitter;

  BoxFactory(EventEmitter eventEmitter) {
    super();
    this.eventEmitter = eventEmitter;
  }
  
  public Box create(UUID id) {
    return new Box(id, eventEmitter);
  }
  
  public Box createWithRandomId() {
    return create(UUID.randomUUID());
  }
}
