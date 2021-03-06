package xwizard.karteczki.quiz;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.google.common.eventbus.EventBus;

import xwizard.karteczki.events.CardAdvancedEvent;
import xwizard.karteczki.events.EventEmitter;
import xwizard.karteczki.repos.Entity;


public class Box implements Entity<UUID>, EventEmitter {
  
  private final static int COMPARTMENT_AMOUNT = 5;
  
  private EventBus eventBus;
  
  private UUID id;
  private List<List<UUID>> compartments;
  
  @SuppressWarnings("unused")
  private Box() {}
  
  Box(UUID id, EventBus eventBus) {
    if (id == null) throw new NullPointerException("id cannot be null!");
    if (eventBus == null) throw new NullPointerException("eventBus cannot be null!");
    
    this.id = id;
    this.eventBus = eventBus;
    
    compartments = new LinkedList<List<UUID>>();
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

  public boolean containsCard(int compartmentNumber, UUID cardId) {
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
    
    eventBus.post(new CardAdvancedEvent(id, cardId));
  }

  public UUID getId() {
    return id;
  }

  public List<List<UUID>> getCompartments() {
    return Collections.unmodifiableList(compartments);
  }

  @Override
  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
  }

}
