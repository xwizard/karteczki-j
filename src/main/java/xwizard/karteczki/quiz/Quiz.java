package xwizard.karteczki.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.google.common.eventbus.EventBus;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.events.EventEmitter;
import xwizard.karteczki.events.QuizFinishedEvent;
import xwizard.karteczki.repos.Entity;

public class Quiz implements Entity<UUID>, EventEmitter {
  private EventBus eventBus;
  
  private UUID id;
  private UUID originatingBoxId;
  private List<UUID> cards;
  
  @SuppressWarnings("unused")
  private Quiz() {}
  
  Quiz(UUID originatingBoxId, List<UUID> cards, EventBus eventBus) {
    this.eventBus = eventBus;
    this.id = UUID.randomUUID();
    this.originatingBoxId = originatingBoxId;
    this.cards = new ArrayList<UUID>();
    this.cards.addAll(cards);
  }

  public void cardCorrect(UUID cardId) {
    checkNull(cardId);
    checkIfQuizFinished();
    checkIfContainsCard(cardId);
    
    cards.remove(cardId);
    eventBus.post(new CardCorrectEvent(getOriginatingBoxId(), cardId));
    
    emitEventIfQuizFinished();
  }

  private void emitEventIfQuizFinished() {
    if (cards.isEmpty()) {
      eventBus.post(new QuizFinishedEvent(id));
    }
  }

  public List<UUID> getCards() {
    return Collections.unmodifiableList(cards);
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
    eventBus.post(new CardIncorrectEvent(getOriginatingBoxId(), cardId));
    
    emitEventIfQuizFinished();
  }

  private void checkIfContainsCard(UUID cardId) {
    if (!containsCard(cardId)) throw new IllegalArgumentException("Card " + cardId + " doesn't exist in Quiz " + id);
  }

  private void checkNull(UUID cardId) {
    if (cardId == null) throw new NullPointerException("cardId cannot be null!");
  }

  public UUID getOriginatingBoxId() {
    return originatingBoxId;
  }

  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
  }
  
  
}
