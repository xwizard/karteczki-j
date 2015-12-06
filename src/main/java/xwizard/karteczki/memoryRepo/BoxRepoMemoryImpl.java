package xwizard.karteczki.memoryRepo;

import java.util.Map;
import java.util.UUID;

import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repos.BoxRepo;

public class BoxRepoMemoryImpl implements BoxRepo {
  
  private Map<UUID, Box> boxes;

  @Override
  public void save(Box entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public Box get(UUID id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(UUID id) {
    // TODO Auto-generated method stub

  }

}
