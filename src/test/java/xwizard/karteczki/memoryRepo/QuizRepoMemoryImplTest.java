package xwizard.karteczki.memoryRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.eventbus.EventBus;

import xwizard.karteczki.quiz.Quiz;
import xwizard.karteczki.repo.jsonInit.JsonRepoInitializer;
import xwizard.karteczki.repos.QuizRepo;

public class QuizRepoMemoryImplTest {
  private final static UUID QUIZ_ID = UUID.fromString("0562c8fc-c481-48b1-9e12-812208ff9ff8");
  private final static UUID BOX_ID = UUID.fromString("358a0290-b5e7-4f55-824c-6ffc9cff5053");
  private final static UUID[] CARD_IDS = new UUID[] { UUID.fromString("186e5ae3-75cf-4702-988b-c9ee0d5fab1b"),
      UUID.fromString("3c7452de-4cdd-46a8-9c5a-907ad8bf2f5f") };

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
  
  @Test
  public void repoReturnsProperQuiz() {
    Quiz q = repo.get(QUIZ_ID);
    Assert.assertEquals(QUIZ_ID, q.getId());
  }
  
  @Test
  public void repoReturnsProperOriginatingBoxId() {
    Quiz q = repo.get(QUIZ_ID);
    Assert.assertEquals(BOX_ID, q.getOriginatingBoxId());
  }
  
  @Test
  public void repoReturnsProperCardList() {
    Quiz q = repo.get(QUIZ_ID);
    Assert.assertArrayEquals(CARD_IDS, q.getCards().toArray());
  }
}
