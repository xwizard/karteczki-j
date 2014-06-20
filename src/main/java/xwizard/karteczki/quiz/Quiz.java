package xwizard.karteczki.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.events.EventEmitter;
import xwizard.karteczki.events.QuizFinishedEvent;

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
    checkNull(cardId);
    checkIfQuizFinished();
    checkIfContainsCard(cardId);
    
    cards.remove(cardId);
    eventEmitter.emit(new CardCorrectEvent(this, cardId));
    
    emitEventIfQuizFinished();
  }

  private void emitEventIfQuizFinished() {
    if (cards.isEmpty()) {
      eventEmitter.emit(new QuizFinishedEvent(this, id));
    }
  }

  private void checkIfQuizFinished() {
    if (cards.isEmpty()) throw new IllegalStateException("Quiz " + id + "is finished!");
  }

  public UUID getId() {
    return id;
  }
  
  boolean containsCard(UUID cardId) {
    return cards.contains(cardId);
  }

  public void cardIncorrect(UUID cardId) {
    checkNull(cardId);
    checkIfQuizFinished();
    checkIfContainsCard(cardId);
    
    cards.remove(cardId);
    eventEmitter.emit(new CardIncorrectEvent(this, cardId));
    
    emitEventIfQuizFinished();
  }

  private void checkIfContainsCard(UUID cardId) {
    if (!containsCard(cardId)) throw new IllegalArgumentException("Card " + cardId + " doesn't exist in Quiz " + id);
  }

  private void checkNull(UUID cardId) {
    if (cardId == null) throw new NullPointerException("cardId cannot be null!");
  }
}
