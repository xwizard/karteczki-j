package xwizard.karteczki.quiz;

import com.google.common.eventbus.EventBus;

public class QuizFactory {
  private final EventBus eventBus;

  public QuizFactory(EventBus eventBus) {
    this.eventBus = eventBus;
  }
}