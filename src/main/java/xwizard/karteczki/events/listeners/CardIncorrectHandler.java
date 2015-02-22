package xwizard.karteczki.events.listeners;

import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.events.Handler;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardIncorrectHandler implements Handler<CardIncorrectEvent> {
  private final BoxRepo boxRepo;

  public CardIncorrectHandler(BoxRepo boxRepo) {
    super();
    this.boxRepo = boxRepo;
  }

  public void handle(CardIncorrectEvent event) {
    Box box = boxRepo.get(event.getBoxId());
    box.degradeCard(event.getCardId());
  }

  public boolean handles(Class<CardIncorrectEvent> aClass) {
    return CardIncorrectEvent.class.equals(aClass);
  }

}
