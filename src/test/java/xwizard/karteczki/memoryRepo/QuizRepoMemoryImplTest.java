package xwizard.karteczki.memoryRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.junit.Before;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.eventbus.EventBus;

import xwizard.karteczki.quiz.Quiz;
import xwizard.karteczki.repo.jsonInit.JsonRepoInitializer;
import xwizard.karteczki.repos.QuizRepo;

public class QuizRepoMemoryImplTest {
  private QuizRepo repo;
  private EventBus eventBus;
  
  @Before
  public void before() throws IOException {
    eventBus = new EventBus();
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("quiz.json");
    TypeReference<List<Quiz>> typeReference = new TypeReference<List<Quiz>>() {};
    JsonRepoInitializer<UUID, Quiz> init = new JsonRepoInitializer<>(inputStream, eventBus, typeReference);
    inputStream.close();
    repo = new QuizRepoMemoryImpl(init);
  }
}
