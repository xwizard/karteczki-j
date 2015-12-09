package xwizard.karteczki.repo.jsonInit;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;

import xwizard.karteczki.events.EventEmitter;
import xwizard.karteczki.repos.Entity;
import xwizard.karteczki.repos.RepoInitializer;

public class JsonRepoInitializer<ID, E extends Entity<ID>> implements RepoInitializer<ID, E> {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Map<ID, E> itemMap;
  private final EventBus eventBus;
  
  public JsonRepoInitializer(InputStream inputStream, EventBus eventBus, TypeReference<List<E>> typeReference) {
    this.eventBus = eventBus;
    
    List<E> items;
    try {
      items = objectMapper.readValue(inputStream, typeReference);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    itemMap = new HashMap<>();
    for (E item : items) {
      setEventBus(item);
      itemMap.put(item.getId(), item);
    }
  }

  private void setEventBus(E item) {
    if (item instanceof EventEmitter) {
      ((EventEmitter) item).setEventBus(eventBus);
    }
  }

  @Override
  public Map<ID, E> getValues() {
    return itemMap;
  }

}
