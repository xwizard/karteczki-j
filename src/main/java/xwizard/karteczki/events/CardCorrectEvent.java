package xwizard.karteczki.events;

import java.util.UUID;

public class CardCorrectEvent extends AbstractCardEvent {
  private static final long serialVersionUID = 1940403217748985496L;

  CardCorrectEvent(UUID eventId, UUID boxId, UUID cardId) {
    super(eventId, boxId, cardId);
  }
  
  public CardCorrectEvent(UUID boxId, UUID cardId) {
    super(boxId, cardId);
  }
}
