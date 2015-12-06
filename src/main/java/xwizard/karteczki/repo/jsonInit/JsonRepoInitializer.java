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
  private final InputStream inputStream;
  private final TypeReference<List<E>> typeReference;
  
  public JsonRepoInitializer(InputStream inputStream, TypeReference<List<E>> typeReference) {
    super();
    this.inputStream = inputStream;
    this.typeReference = typeReference;
  }

  @Override
  public Map<ID, E> getValues() {
    List<E> items;
    try {
      items = objectMapper.readValue(inputStream, typeReference);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Map<ID, E> result = new HashMap<>();
    for (E item : items) {
      result.put(item.getId(), item);
    }
    
    return result;
  }

}
