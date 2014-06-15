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
    
    Assert.assertTrue(box.containsCard(1, cardId));
  }
  
  @Test
  public void addTwoCards() {
    UUID cardId2 = UUID.randomUUID();
    
    box.addCard(1, cardId);
    box.addCard(2, cardId2);
    
    Assert.assertTrue(box.containsCard(1, cardId));
    Assert.assertTrue(box.containsCard(2, cardId2));
  }
  
  @Test(expected = NullPointerException.class)
  public void addCardWithNullId() {
    box.addCard(0, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addCardToNotExistingCompartment(){
    box.addCard(Integer.MAX_VALUE, cardId);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addExistingCard() {
    box.addCard(2, cardId);
    box.addCard(0, cardId);
  }
  
  @Test
  public void moveCardToFirst() {
    box.addCard(1, cardId);
    
    box.moveCardToFirst(cardId);
    
    Assert.assertTrue(box.containsCard(0, cardId));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void moveNotExistingCardToFirst() {
    box.moveCardToFirst(cardId);
  }
  
  @Test(expected = NullPointerException.class)
  public void moveNullCardToFirst() {
    box.moveCardToFirst(null);
  }
  
  @Test
  public void advanceCard() {
    box.addCard(3, cardId);
    
    box.advanceCard(cardId);
    
    Assert.assertTrue(box.containsCard(4, cardId));
  }
}
