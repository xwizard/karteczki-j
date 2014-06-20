package xwizard.karteczki.quiz;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import xwizard.karteczki.events.AbstractCardEvent;
import xwizard.karteczki.events.CardAdvancedEvent;
import xwizard.karteczki.events.EventEmitterMock;

public class BoxTest {

  private Box box;
  private UUID cardId;
  private EventEmitterMock eventEmitter;
  
  @Before
  public void setUp() {
    eventEmitter = new EventEmitterMock();
    box = new Box(eventEmitter);
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
  public void degradeCard() {
    box.addCard(1, cardId);
    
    box.degradeCard(cardId);
    
    Assert.assertTrue(box.containsCard(0, cardId));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void degradeNotExistingCard() {
    box.degradeCard(cardId);
  }
  
  @Test(expected = NullPointerException.class)
  public void degradeNullCard() {
    box.degradeCard(null);
  }
  
  @Test
  public void advanceCard() {
    box.addCard(3, cardId);
    
    box.advanceCard(cardId);
    
    Assert.assertTrue(box.containsCard(4, cardId));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void advanceNotExisitingCard() {
    box.advanceCard(cardId);
  }
  
  @Test(expected = NullPointerException.class)
  public void advanceNullCard() {
    box.advanceCard(null);
  }
  
  @Test
  public void advanceCardFromLastCompartment() {
    box.addCard(4, cardId);
    
    box.advanceCard(cardId);
    
    Assert.assertFalse(box.containsCard(cardId));
    eventEmitter.assertEmitted(CardAdvancedEvent.class, 1);
    Assert.assertEquals(((AbstractCardEvent)eventEmitter.events.get(CardAdvancedEvent.class).get(0)).getCardId(), cardId);
  }
}
