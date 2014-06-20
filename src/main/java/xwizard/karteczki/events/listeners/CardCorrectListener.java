package xwizard.karteczki.events.listeners;

import java.util.EventListener;

import xwizard.karteczki.events.CardCorrectEvent;

public interface CardCorrectListener extends EventListener {
  void cardCorrect(CardCorrectEvent event);
}
