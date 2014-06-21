package xwizard.karteczki.events.listeners;

import java.util.EventListener;

import xwizard.karteczki.events.AbstractCardEvent;

public interface CardCorrectListener extends EventListener {
  void cardCorrect(AbstractCardEvent event);
}
