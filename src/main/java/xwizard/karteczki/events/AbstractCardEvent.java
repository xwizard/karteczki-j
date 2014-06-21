package xwizard.karteczki.events;

import java.util.EventObject;
import java.util.UUID;

public abstract class AbstractCardEvent extends EventObject {
  private static final long serialVersionUID = 5355827253214596380L;

  private final UUID cardId;
  private final UUID boxId;

  public AbstractCardEvent(Object source, UUID boxId, UUID cardId) {
    super(source);
    if (cardId == null) throw new IllegalStateException("cardId cannot be null");
    if (boxId == null) throw new IllegalStateException("boxId cannot be null");
    this.boxId = boxId;
    this.cardId = cardId;
  }

  public UUID getCardId() {
    return cardId;
  }

  public UUID getBoxId() {
    return boxId;
  }

}