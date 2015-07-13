package xwizard.karteczki.events.handlers;

import com.google.common.eventbus.Subscribe;

import xwizard.karteczki.events.CardCorrectEvent;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardCorrectHandler {
  private final BoxRepo boxRepo;

  public CardCorrectHandler(BoxRepo boxRepo) {
    super();
    this.boxRepo = boxRepo;
  }

  @Subscribe
  public void handle(CardCorrectEvent event) {
    Box box = boxRepo.get(event.getBoxId());
    box.advanceCard(event.getCardId());
    boxRepo.save(box);
  }
}
