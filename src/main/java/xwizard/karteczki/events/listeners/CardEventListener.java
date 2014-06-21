package xwizard.karteczki.events.listeners;

import java.util.EventListener;

import xwizard.karteczki.events.AbstractCardEvent;

public interface CardEventListener<T extends AbstractCardEvent> extends EventListener {
  void onCardEvent(T event);
}
