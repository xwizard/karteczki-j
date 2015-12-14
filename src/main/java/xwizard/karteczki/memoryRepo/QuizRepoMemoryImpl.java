package xwizard.karteczki.memoryRepo;

import java.util.Map;
import java.util.UUID;

import xwizard.karteczki.quiz.Quiz;
import xwizard.karteczki.repo.jsonInit.JsonRepoInitializer;
import xwizard.karteczki.repos.QuizRepo;

public class QuizRepoMemoryImpl implements QuizRepo {
  
  private final JsonRepoInitializer<UUID, Quiz> initializer;
  private final Map<UUID, Quiz> map;

  public QuizRepoMemoryImpl(JsonRepoInitializer<UUID, Quiz> init) {
    this.initializer = init;
    map = init.getValues();
  }

  @Override
  public void save(Quiz entity) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Quiz get(UUID id) {
    return map.get(id);
  }

  @Override
  public void delete(UUID id) {
    // TODO Auto-generated method stub
    
  }

}
