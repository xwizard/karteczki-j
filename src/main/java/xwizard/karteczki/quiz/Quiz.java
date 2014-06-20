package xwizard.karteczki.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.EventEmitter;

public class Quiz {
  private final UUID id;
  private List<UUID> cards;
  private final EventEmitter eventEmitter;
  
  Quiz(UUID id, List<UUID> cards, EventEmitter eventEmitter) {
    super();
    this.id = id;
    this.cards = new ArrayList<UUID>();
    this.cards.addAll(cards);
    this.eventEmitter = eventEmitter;
  }

  public void cardCorrect(UUID cardId) {

    eventEmitter.emit(new CardCorrectEvent(this, cardId));
  }

  public UUID getId() {
    return id;
  }
  
}
