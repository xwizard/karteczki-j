package xwizard.karteczki.quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Box {
  
  private final static int COMPARTMENT_AMOUNT = 5;
  
  UUID id;
  List<List<UUID>> compartments;
  
  Box() {
    compartments = new LinkedList<List<UUID>>();
    id = UUID.randomUUID();
    for (int i = 0; i < COMPARTMENT_AMOUNT; i++) {
      compartments.add(new LinkedList<UUID>());
    }
  }

  public void addCard(int compartmentNumber, UUID cardId) {
    if (cardId == null) throw new IllegalArgumentException("cardId cannot be null!");
    if (isIllegal(compartmentNumber)) throw new IllegalArgumentException("Illegal compartment number: " + compartmentNumber);
    if (containsCard(cardId)) throw new IllegalStateException("Card with id " + cardId + " already exists in box " + id);
    
    compartments.get(compartmentNumber).add(cardId);
  }

  private boolean isIllegal(int compartmentNumber) {
    return compartmentNumber < 0 || compartmentNumber >= COMPARTMENT_AMOUNT;
  }

  public boolean containsCard(UUID cardId) {
    for (List<UUID> compartment : compartments) {
      if (compartment.contains(cardId)) {
        return true;
      }
    }
    return false;
  }

}
