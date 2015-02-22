package xwizard.karteczki.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.CardIncorrectEvent;
import static xwizard.karteczki.events.DomainEvents.raise;
import xwizard.karteczki.events.QuizFinishedEvent;

public class Quiz {
  private final UUID id;
  private final UUID originatingBoxId;
  private List<UUID> cards;
  
  Quiz(UUID originatingBoxId, List<UUID> cards) {
    super();
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
    raise(new CardCorrectEvent(getOriginatingBoxId(), cardId));
    
    emitEventIfQuizFinished();
  }

  private void emitEventIfQuizFinished() {
    if (cards.isEmpty()) {
      raise(new QuizFinishedEvent(id));
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
    raise(new CardIncorrectEvent(getOriginatingBoxId(), cardId));
    
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
}
