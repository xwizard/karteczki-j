package xwizard.karteczki.quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Box {
  
  private final static int COMPARTMENT_AMOUNT = 5;
  
  List<List<UUID>> compartments;
  
  Box() {
    compartments = new LinkedList<List<UUID>>();
    for (int i = 0; i < COMPARTMENT_AMOUNT; i++) {
      compartments.add(new LinkedList<UUID>());
    }
  }

  public void addCard(int compartmentNumber, UUID cardId) {
    compartments.get(compartmentNumber).add(cardId);
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
