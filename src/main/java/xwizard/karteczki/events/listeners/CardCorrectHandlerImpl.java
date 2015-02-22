package xwizard.karteczki.events.listeners;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.events.Handler;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardCorrectHandlerImpl implements Handler<CardCorrectEvent> {
  private final BoxRepo boxRepo;

  public CardCorrectHandlerImpl(BoxRepo boxRepo) {
    super();
    this.boxRepo = boxRepo;
  }

  public void handle(CardCorrectEvent event) {
    Box box = boxRepo.get(event.getBoxId());
    box.advanceCard(event.getCardId());
    boxRepo.save(box);
  }

  public boolean handles(Class<CardCorrectEvent> aClass) {
    return CardCorrectEvent.class.equals(aClass);
  }
}
