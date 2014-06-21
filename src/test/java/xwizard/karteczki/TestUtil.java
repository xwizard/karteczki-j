package xwizard.karteczki;

import org.junit.Assert;


public class TestUtil {
  public static <T> void testEquals(T o1, T o2) {
    Assert.assertEquals(o1, o2);
    Assert.assertEquals(o2, o1);
    Assert.assertEquals(o1, o1);
    Assert.assertEquals(o2, o2);
  }
}
