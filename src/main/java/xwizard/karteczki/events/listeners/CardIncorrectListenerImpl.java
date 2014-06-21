package xwizard.karteczki.events.listeners;

import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardIncorrectListenerImpl implements CardEventListener<CardIncorrectEvent> {
  private final BoxRepo boxRepo;

  public CardIncorrectListenerImpl(BoxRepo boxRepo) {
    super();
    this.boxRepo = boxRepo;
  }

  public void onCardEvent(CardIncorrectEvent event) {
    Box box = boxRepo.get(event.getBoxId());
    box.degradeCard(event.getCardId());
  }

}
