package xwizard.karteczki.events;

import java.util.UUID;

public class CardIncorrectEvent extends AbstractCardEvent {
  private static final long serialVersionUID = -3490401171244066596L;

  public CardIncorrectEvent(Object source, UUID cardId) {
    super(source, cardId);
  }

}
