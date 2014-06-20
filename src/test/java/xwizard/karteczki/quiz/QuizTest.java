package xwizard.karteczki.quiz;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.EventEmitterMock;

public class QuizTest {
  private Quiz quiz;
  private EventEmitterMock eventEmitter;
  private UUID cardId1, cardId2;
  
  @Before
  public void before() {
    eventEmitter = new EventEmitterMock();
    cardId1 = UUID.randomUUID();
    cardId2 = UUID.randomUUID();
    quiz = new Quiz(UUID.randomUUID(), Arrays.asList(cardId1, cardId2), eventEmitter);
  }
  
  @Test
  public void cardCorrectEmitsEvent() {
    quiz.cardCorrect(cardId1);
    
    eventEmitter.assertEmitted(CardCorrectEvent.class, 1);
    Assert.assertEquals(eventEmitter.getEvent(CardCorrectEvent.class, 0).getCardId(), cardId1);
  }
}
