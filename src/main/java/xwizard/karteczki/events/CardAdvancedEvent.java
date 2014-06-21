package xwizard.karteczki.events;

import java.util.UUID;

public class CardAdvancedEvent extends AbstractCardEvent {
  private static final long serialVersionUID = -9141826227620625963L;
  
  public CardAdvancedEvent(Object source, UUID boxId, UUID cardId) {
    super(source, boxId, cardId);
  }
}
