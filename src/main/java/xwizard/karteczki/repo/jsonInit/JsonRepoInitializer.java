package xwizard.karteczki.repo.jsonInit;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import xwizard.karteczki.repos.Entity;
import xwizard.karteczki.repos.RepoInitializer;

public class JsonRepoInitializer<ID, E extends Entity<ID>> implements RepoInitializer<ID, E> {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Map<ID, E> itemMap;
  
  public JsonRepoInitializer(InputStream inputStream, TypeReference<List<E>> typeReference) {
    super();
    List<E> items;
    try {
      items = objectMapper.readValue(inputStream, typeReference);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    itemMap = new HashMap<>();
    for (E item : items) {
      itemMap.put(item.getId(), item);
    }
  }

  @Override
  public Map<ID, E> getValues() {
    return itemMap;
  }

}
