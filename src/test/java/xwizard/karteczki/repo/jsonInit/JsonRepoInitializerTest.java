package xwizard.karteczki.repo.jsonInit;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Assert;

public class JsonRepoInitializerTest {
  private final static UUID[] IDS = {UUID.fromString("8d467d29-59ad-4541-8dd8-83db5eda08a6"), UUID.fromString("cac7ec52-c47f-47da-b955-35462f9964e7")};
  
  private final TypeReference<List<StubEntity>> typeReference = new TypeReference<List<StubEntity>>() {};
  
  @Test
  public void deserializesProperAmountOfObjects() {
    JsonRepoInitializer<UUID, StubEntity> init = new JsonRepoInitializer<>(getStubJson(), typeReference);
    Map<UUID, StubEntity> result = init.getValues();
    
    Assert.assertEquals(2, result.size());
  }

  private InputStream getStubJson() {
    return getClass().getClassLoader().getResourceAsStream("stub.json");
  }
}
