package xwizard.karteczki.memoryRepo;

import java.util.UUID;

import xwizard.karteczki.quiz.Quiz;
import xwizard.karteczki.repo.jsonInit.JsonRepoInitializer;
import xwizard.karteczki.repos.QuizRepo;

public class QuizRepoMemoryImpl implements QuizRepo {

  public QuizRepoMemoryImpl(JsonRepoInitializer<UUID, Quiz> init) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(Quiz entity) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Quiz get(UUID id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(UUID id) {
    // TODO Auto-generated method stub
    
  }

}
