package xwizard.karteczki.repo.jsonInit;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.eventbus.EventBus;

import org.junit.Assert;

public class JsonRepoInitializerTest {
  private final static UUID[] IDS = {UUID.fromString("8d467d29-59ad-4541-8dd8-83db5eda08a6"), UUID.fromString("cac7ec52-c47f-47da-b955-35462f9964e7")};
  private final static String STUB_JSON_FILE_NAME = "stub.json";
  private final static String INCORRECT_JSON_FILE_NAME = "incorrect.json";
  
  private final TypeReference<List<StubEntity>> typeReference = new TypeReference<List<StubEntity>>() {};
  private final EventBus eventBus = new EventBus();
  
  @Test
  public void deserializesProperAmountOfObjects() throws IOException {
    JsonRepoInitializer<UUID, StubEntity> init = getinItializer(STUB_JSON_FILE_NAME);
    Map<UUID, StubEntity> result = init.getValues();
    
    Assert.assertEquals(2, result.size());
  }

  @Test
  public void deserializesProperObjects() throws IOException {
    JsonRepoInitializer<UUID, StubEntity> init = getinItializer(STUB_JSON_FILE_NAME);
    Map<UUID, StubEntity> result = init.getValues();
    
    for (UUID id : IDS) {
      Assert.assertEquals(id, result.get(id).getId());
    }
  }
  
  @Test(expected = RuntimeException.class)
  public void deserializeIncorrectJsonShouldThrow() throws IOException {
    getinItializer(INCORRECT_JSON_FILE_NAME);
  }
  
  @Test
  public void deserializedObjectsShouldHaveEventBusSet() throws IOException {
    JsonRepoInitializer<UUID, StubEntity> init = getinItializer(STUB_JSON_FILE_NAME);
    Map<UUID, StubEntity> result = init.getValues();
    
    for (StubEntity stubEntity : result.values()) {
      Assert.assertEquals(eventBus, stubEntity.getEventBus());
    }
  }

  private JsonRepoInitializer<UUID, StubEntity> getinItializer(String fileName) throws IOException {
    InputStream stubJson = getStubJson(fileName);
    JsonRepoInitializer<UUID, StubEntity> init = new JsonRepoInitializer<>(stubJson, eventBus, typeReference);
    stubJson.close();
    return init;
  }
  
  private InputStream getStubJson(String fileName) {
    return getClass().getClassLoader().getResourceAsStream(fileName);
  }
}
