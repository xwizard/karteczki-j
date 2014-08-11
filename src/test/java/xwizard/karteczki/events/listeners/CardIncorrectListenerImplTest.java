package xwizard.karteczki.events.listeners;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardIncorrectListenerImplTest {
  private Box box;
  private UUID cardId;
  private UUID boxId;
  private CardEventListener<CardIncorrectEvent> listener;
  private BoxRepo boxRepo;
  
  @Before
  public void before() {
    cardId = UUID.randomUUID();
    boxId = UUID.randomUUID();
    
    box = mock(Box.class);
    when(box.getId()).thenReturn(boxId);
    
    boxRepo = mock(BoxRepo.class);
    when(boxRepo.get(boxId)).thenReturn(box);
    listener = new CardIncorrectListenerImpl(boxRepo);
  }
  
  @Test
  public void cardCorrectEventShouldAdvanceCard() {
    listener.onCardEvent(new CardIncorrectEvent(box.getId(), cardId));
    
    verify(box).degradeCard(cardId);
  }
}
