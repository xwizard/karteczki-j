package xwizard.karteczki.memoryRepo;

import org.junit.Before;

import xwizard.karteczki.repos.BoxRepo;

public class BoxRepoMemoryImplTest {
  BoxRepo repo;
  
  @Before
  public void before() {
    repo = new BoxRepoMemoryImpl();
  }
}
