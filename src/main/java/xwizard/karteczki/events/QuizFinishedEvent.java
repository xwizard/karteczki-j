package xwizard.karteczki.events;

import java.util.UUID;

public class QuizFinishedEvent extends Event {
  private final UUID quizId;

  QuizFinishedEvent(UUID eventId, UUID quizId) {
    super(eventId);
    this.quizId = quizId;
  }
  
  public QuizFinishedEvent(UUID quizId) {
    super();
    this.quizId = quizId;
  }

  public UUID getQuizId() {
    return quizId;
  }
}
