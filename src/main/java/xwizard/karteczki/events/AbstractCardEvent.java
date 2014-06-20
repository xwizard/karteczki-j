package xwizard.karteczki.events;

import java.util.EventObject;
import java.util.UUID;

public abstract class AbstractCardEvent extends EventObject {
  private static final long serialVersionUID = 5355827253214596380L;
  private final UUID cardId;

  public AbstractCardEvent(Object source, UUID cardId) {
    super(source);
    this.cardId = cardId;
  }

  public UUID getCardId() {
    return cardId;
  }

}