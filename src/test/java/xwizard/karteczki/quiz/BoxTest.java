package xwizard.karteczki.quiz;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoxTest {

  private Box box;
  private UUID cardId;
  
  @Before
  public void setUp() {
    box = new Box();
    cardId = UUID.randomUUID();
  }

  @Test
  public void addCard() {
    box.addCard(1, cardId);
    
    Assert.assertTrue(box.containsCard(cardId));
  }
  
  @Test
  public void addTwoCards() {
    UUID cardId2 = UUID.randomUUID();
    
    box.addCard(1, cardId);
    box.addCard(2, cardId2);
    
    Assert.assertTrue(box.containsCard(cardId));
    Assert.assertTrue(box.containsCard(cardId2));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addCardWithNullId() {
    box.addCard(0, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addCardToNotExistingCompartment(){
    box.addCard(Integer.MAX_VALUE, cardId);
  }
  
  @Test(expected = IllegalStateException.class)
  public void addExistingCard() {
    box.addCard(2, cardId);
    box.addCard(0, cardId);
  }
}
