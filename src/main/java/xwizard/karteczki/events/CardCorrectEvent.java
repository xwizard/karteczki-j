package xwizard.karteczki.events;

import java.util.UUID;

public class CardCorrectEvent extends AbstractCardEvent {
  private static final long serialVersionUID = 1940403217748985496L;

  public CardCorrectEvent(Object source, UUID boxId, UUID cardId) {
    super(source, boxId, cardId);
  }
}
