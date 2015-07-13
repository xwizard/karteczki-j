package xwizard.karteczki.events;

import java.util.UUID;

public class CardCorrectEvent extends AbstractCardEvent {
  CardCorrectEvent(UUID eventId, UUID boxId, UUID cardId) {
    super(eventId, boxId, cardId);
  }
  
  public CardCorrectEvent(UUID boxId, UUID cardId) {
    super(boxId, cardId);
  }
}
