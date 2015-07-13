package xwizard.karteczki.events;

import java.util.UUID;

public abstract class AbstractCardEvent extends Event {

  private final UUID cardId;
  private final UUID boxId;

  AbstractCardEvent(UUID eventId, UUID boxId, UUID cardId) {
    super(eventId);
    if (cardId == null) throw new IllegalStateException("cardId cannot be null");
    if (boxId == null) throw new IllegalStateException("boxId cannot be null");
    this.boxId = boxId;
    this.cardId = cardId;
  }
  
  public AbstractCardEvent(UUID boxId, UUID cardId) {
    this(UUID.randomUUID(), boxId, cardId);
  }

  public UUID getCardId() {
    return cardId;
  }

  public UUID getBoxId() {
    return boxId;
  }

}