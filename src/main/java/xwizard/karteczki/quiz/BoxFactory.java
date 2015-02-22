package xwizard.karteczki.quiz;

import java.util.UUID;

public class BoxFactory {
  
  public Box create(UUID id) {
    return new Box(id);
  }
  
  public Box createWithRandomId() {
    return create(UUID.randomUUID());
  }
}
