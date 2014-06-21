package xwizard.karteczki.events.listeners;

import xwizard.karteczki.events.AbstractCardEvent;
import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class CardCorrectListenerImpl implements CardCorrectListener {
  private final BoxRepo boxRepo;

  CardCorrectListenerImpl(BoxRepo boxRepo) {
    super();
    this.boxRepo = boxRepo;
  }

  public void cardCorrect(AbstractCardEvent event) {
    Box box = boxRepo.get(event.getBoxId());
    box.advanceCard(event.getCardId());
    boxRepo.save(box);
  }
}
