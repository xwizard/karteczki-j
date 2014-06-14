package xwizard.karteczki.quiz;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class BoxTest {

  @Test
  public void addCard() {
    Box box = new Box();
    
    UUID cardId = UUID.randomUUID();
    box.addCard(1, cardId);
    
    Assert.assertTrue(box.containsCard(cardId));
  }
}
