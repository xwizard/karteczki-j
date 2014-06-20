package xwizard.karteczki.events;

import java.util.EventObject;
import java.util.UUID;

public class QuizFinishedEvent extends EventObject {
  private static final long serialVersionUID = -1169380366977887694L;

  private final UUID quizId;

  public QuizFinishedEvent(Object source, UUID quizId) {
    super(source);
    this.quizId = quizId;
  }

  public UUID getQuizId() {
    return quizId;
  }
}
