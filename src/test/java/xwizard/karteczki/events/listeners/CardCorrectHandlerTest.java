package xwizard.karteczki.events.listeners;

import xwizard.karteczki.events.handlers.CardCorrectHandlerImpl;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.Handler;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardCorrectHandlerTest {
  private Box box;
  private UUID cardId;
  private UUID boxId;
  private Handler<CardCorrectEvent> handler;
  private BoxRepo boxRepo;
  
  @Before
  public void before() {
    cardId = UUID.randomUUID();
    boxId = UUID.randomUUID();
    
    box = mock(Box.class);
    when(box.getId()).thenReturn(boxId);
    
    boxRepo = mock(BoxRepo.class);
    when(boxRepo.get(boxId)).thenReturn(box);
    handler = new CardCorrectHandlerImpl(boxRepo);
  }
  
  @Test
  public void cardCorrectEventShouldAdvanceCard() {
    handler.handle(new CardCorrectEvent(box.getId(), cardId));
    
    verify(box).advanceCard(cardId);
  }
}
