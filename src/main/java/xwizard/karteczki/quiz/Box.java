package xwizard.karteczki.quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import xwizard.karteczki.events.CardAdvancedEvent;
import xwizard.karteczki.events.EventEmitter;

public class Box {
  
  private final static int COMPARTMENT_AMOUNT = 5;
  
  private UUID id;
  private List<List<UUID>> compartments;
  
  private final EventEmitter eventEmitter;
  
  public Box(EventEmitter eventEmitter) {
    this.eventEmitter = eventEmitter;
    compartments = new LinkedList<List<UUID>>();
    id = UUID.randomUUID();
    for (int i = 0; i < COMPARTMENT_AMOUNT; i++) {
      compartments.add(new LinkedList<UUID>());
    }
  }
  
  public void addCard(UUID cardId) {
    addCard(0, cardId);
  }

  void addCard(int compartmentNumber, UUID cardId) {
    checkNull(cardId);
    if (isIllegal(compartmentNumber)) throw new IllegalArgumentException("Illegal compartment number: " + compartmentNumber);
    if (containsCard(cardId)) cardDoesntExist(cardId);
    
    compartments.get(compartmentNumber).add(cardId);
  }

  void checkNull(UUID cardId) {
    if (cardId == null) throw new NullPointerException("cardId cannot be null!");
  }

  private boolean isIllegal(int compartmentNumber) {
    return compartmentNumber < 0 || compartmentNumber >= COMPARTMENT_AMOUNT;
  }

  public boolean containsCard(UUID cardId) {
    return findCard(cardId) >= 0;
  }

  boolean containsCard(int compartmentNumber, UUID cardId) {
    List<UUID> compartment = compartments.get(compartmentNumber);
    return compartment.contains(cardId);
  }
  
  private int findCard(UUID cardId) {
    for (int i = 0; i < COMPARTMENT_AMOUNT; i++) {
      if(containsCard(i, cardId)) {
        return i;
      }
    }
    return -1;
  }

  public void degradeCard(UUID cardId) {
    checkNull(cardId);
    
    int compartment = findCard(cardId);
    
    if (compartment < 0) cardDoesntExist(cardId);
    
    compartments.get(compartment).remove(cardId);
    compartments.get(0).add(cardId);
  }

  void cardDoesntExist(UUID cardId) {
    throw new IllegalArgumentException("Card " + cardId + "doesn' exist in box " + id);
  }

  public void advanceCard(UUID cardId) {
    checkNull(cardId);
    
    int compartment = findCard(cardId);
    
    if (compartment < 0) cardDoesntExist(cardId);
    
    compartments.get(compartment).remove(cardId);
    compartment++;
    if (compartment < COMPARTMENT_AMOUNT) {
      compartments.get(compartment).add(cardId);
    }
    
    eventEmitter.emit(new CardAdvancedEvent(this, id, cardId));
  }

  public UUID getId() {
    return id;
  }

}
