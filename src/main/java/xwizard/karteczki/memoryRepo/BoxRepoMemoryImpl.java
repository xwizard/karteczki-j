package xwizard.karteczki.memoryRepo;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repo.jsonInit.JsonRepoInitializer;
import xwizard.karteczki.repos.BoxRepo;

public class BoxRepoMemoryImpl implements BoxRepo {
  
  private Map<UUID, Box> boxes;

  public BoxRepoMemoryImpl(JsonRepoInitializer<UUID, Box> init) {
    boxes = Collections.unmodifiableMap(init.getValues());
  }

  @Override
  public void save(Box entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public Box get(UUID id) {
    return boxes.get(id);
  }

  @Override
  public void delete(UUID id) {
    // TODO Auto-generated method stub

  }

}
