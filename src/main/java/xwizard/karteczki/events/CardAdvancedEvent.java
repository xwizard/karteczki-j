package xwizard.karteczki.events;

import java.util.UUID;

public class CardAdvancedEvent extends AbstractCardEvent {
  CardAdvancedEvent(UUID eventId, UUID boxId, UUID cardId) {
    super(eventId, boxId, cardId);
  }
  
  public CardAdvancedEvent(UUID boxId, UUID cardId) {
    super(boxId, cardId);
  }
}
