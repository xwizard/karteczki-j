package xwizard.karteczki.quiz;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.events.EventEmitterMock;
import xwizard.karteczki.events.QuizFinishedEvent;

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
  public void cardCorrectShouldEmitEvent() {
    quiz.cardCorrect(cardId1);
    
    eventEmitter.assertEmitted(CardCorrectEvent.class, 1);
    Assert.assertEquals(cardId1, eventEmitter.getEvent(CardCorrectEvent.class, 0).getCardId());
    Assert.assertEquals(quiz.getOriginatingBoxId(), eventEmitter.getEvent(CardCorrectEvent.class, 0).getBoxId());
  }
  
  @Test
  public void cardCorrectShouldRemoveCard() {
    quiz.cardCorrect(cardId2);
    
    Assert.assertFalse(quiz.containsCard(cardId2));
  }
  
  @Test(expected = NullPointerException.class)
  public void cardCorrectShouldntAcceptNull() {
    quiz.cardCorrect(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void cardCorrectShouldntAcceptNotExistentCard() {
    quiz.cardCorrect(UUID.randomUUID());
  }
  
  @Test
  public void cardIncorrectShouldEmitEvent() {
    quiz.cardIncorrect(cardId2);
    
    eventEmitter.assertEmitted(CardIncorrectEvent.class, 1);
    Assert.assertEquals(cardId2, eventEmitter.getEvent(CardIncorrectEvent.class, 0).getCardId());
    Assert.assertEquals(quiz.getOriginatingBoxId(), eventEmitter.getEvent(CardIncorrectEvent.class, 0).getBoxId());
  }
  
  @Test
  public void cardIncorrectShouldRemoveCard() {
    quiz.cardIncorrect(cardId1);
    
    Assert.assertFalse(quiz.containsCard(cardId1));
  }
  
  @Test(expected = NullPointerException.class)
  public void cardIncorrectShouldntAcceptNull() {
    quiz.cardIncorrect(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void cardIncorrectShouldntAcceptNotExistantCard() {
    quiz.cardIncorrect(UUID.randomUUID());
  }
  
  @Test(expected = IllegalStateException.class)
  public void cardIncorrectIsntAllowedOnFinishedQuiz() {
    quiz.cardIncorrect(cardId1);
    quiz.cardIncorrect(cardId2);
    quiz.cardIncorrect(cardId1);
  }
  
  @Test(expected = IllegalStateException.class)
  public void cardCorrectIsntAllowedOnFinishedQuiz() {
    quiz.cardIncorrect(cardId1);
    quiz.cardIncorrect(cardId2);
    quiz.cardCorrect(cardId1);
  }
  
  @Test
  public void quizShouldEmitEventAfterLastCardCorrect() {
    quiz.cardCorrect(cardId2);
    quiz.cardCorrect(cardId1);
    
    eventEmitter.assertEmitted(QuizFinishedEvent.class, 1);
    Assert.assertEquals(quiz.getId(), eventEmitter.getEvent(QuizFinishedEvent.class, 0).getQuizId());
  }
  
  @Test
  public void quizShouldEmitEventAfterLastCardInCorrect() {
    quiz.cardCorrect(cardId2);
    quiz.cardIncorrect(cardId1);
    
    eventEmitter.assertEmitted(QuizFinishedEvent.class, 1);
    Assert.assertEquals(quiz.getId(), eventEmitter.getEvent(QuizFinishedEvent.class, 0).getQuizId());
  }
}
