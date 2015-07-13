package xwizard.karteczki.events.handlers;

import com.google.common.eventbus.Subscribe;

import xwizard.karteczki.events.CardIncorrectEvent;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardIncorrectHandler {
  private final BoxRepo boxRepo;

  public CardIncorrectHandler(BoxRepo boxRepo) {
    super();
    this.boxRepo = boxRepo;
  }

  @Subscribe
  public void handle(CardIncorrectEvent event) {
    Box box = boxRepo.get(event.getBoxId());
    box.degradeCard(event.getCardId());
  }
}
