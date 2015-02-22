package xwizard.karteczki.quiz;

import java.util.Arrays;
import java.util.UUID;
import org.junit.After;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.events.DomainEvents;
import xwizard.karteczki.events.StubHandler;
import xwizard.karteczki.events.QuizFinishedEvent;

public class QuizTest {
  private Quiz quiz;
  private StubHandler stubHandler;
  private UUID cardId1, cardId2;
  
  @Before
  public void before() {
    stubHandler = new StubHandler();
    DomainEvents.register(stubHandler);
    cardId1 = UUID.randomUUID();
    cardId2 = UUID.randomUUID();
    quiz = new Quiz(UUID.randomUUID(), Arrays.asList(cardId1, cardId2));
  }
  
  @After
  public void afetr() {
    DomainEvents.unregister(stubHandler);
  }
  
  @Test
  public void cardCorrectShouldEmitEvent() {
    quiz.cardCorrect(cardId1);
    
    stubHandler.assertEmitted(CardCorrectEvent.class, 1);
    Assert.assertEquals(cardId1, stubHandler.getEvent(CardCorrectEvent.class, 0).getCardId());
    Assert.assertEquals(quiz.getOriginatingBoxId(), stubHandler.getEvent(CardCorrectEvent.class, 0).getBoxId());
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
    
    stubHandler.assertEmitted(CardIncorrectEvent.class, 1);
    Assert.assertEquals(cardId2, stubHandler.getEvent(CardIncorrectEvent.class, 0).getCardId());
    Assert.assertEquals(quiz.getOriginatingBoxId(), stubHandler.getEvent(CardIncorrectEvent.class, 0).getBoxId());
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
    
    stubHandler.assertEmitted(QuizFinishedEvent.class, 1);
    Assert.assertEquals(quiz.getId(), stubHandler.getEvent(QuizFinishedEvent.class, 0).getQuizId());
  }
  
  @Test
  public void quizShouldEmitEventAfterLastCardInCorrect() {
    quiz.cardCorrect(cardId2);
    quiz.cardIncorrect(cardId1);
    
    stubHandler.assertEmitted(QuizFinishedEvent.class, 1);
    Assert.assertEquals(quiz.getId(), stubHandler.getEvent(QuizFinishedEvent.class, 0).getQuizId());
  }
}
