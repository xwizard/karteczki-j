package xwizard.karteczki.events;

import java.util.UUID;

public class CardIncorrectEvent extends AbstractCardEvent {
  CardIncorrectEvent(UUID eventId, UUID boxId, UUID cardId) {
    super(eventId, boxId, cardId);
  }
  
  public CardIncorrectEvent(UUID boxId, UUID cardId) {
    super(UUID.randomUUID(), boxId, cardId);
  }

}
