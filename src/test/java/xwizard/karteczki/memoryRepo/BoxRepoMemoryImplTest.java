package xwizard.karteczki.memoryRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import xwizard.karteczki.quiz.Box;
import xwizard.karteczki.repo.jsonInit.JsonRepoInitializer;
import xwizard.karteczki.repos.BoxRepo;

public class BoxRepoMemoryImplTest {
  private static final UUID BOXID = UUID.fromString("8d467d29-59ad-4541-8dd8-83db5eda08a6");
  private static final UUID CARDID = UUID.fromString("159af688-b42a-4766-a79a-6d9ecb7a70a5");
  private BoxRepo repo;
  
  @Before
  public void before() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("box.json");
    TypeReference<List<Box>> typeReference = new TypeReference<List<Box>>() {};
    JsonRepoInitializer<UUID, Box> init = new JsonRepoInitializer<>(inputStream, typeReference);
    inputStream.close();
    repo = new BoxRepoMemoryImpl(init);
  }
  
  @Test
  public void boxRepoRetrievesProperCardId() {
    Box result = repo.get(BOXID);
    
    Assert.assertEquals(BOXID, result.getId());
  }
}
