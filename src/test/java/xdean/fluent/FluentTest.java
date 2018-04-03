package xdean.fluent;

import static org.junit.Assert.*;

import org.junit.Test;

public class FluentTest implements FluentRx<FluentTest> {

  private static final FluentTest other = new FluentTest();
  int here = 0;

  @Test
  public void test() throws Exception {
    assertReach(() -> let(f -> here()));
    assertReach(() -> letIf(f -> true, f -> here()));
    assertNotReach(() -> letIf(f -> false, f -> here()));
    assertReach(() -> assertTrue(map(f -> here(1)) == 1));
    assertReach(() -> assertTrue(mapIf(f -> true, f -> here(other)) == other));
    assertNotReach(() -> assertTrue(mapIf(f -> false, f -> here(other)) == this));
    assertReach(() -> assertTrue(mapIfOrElse(f -> true, f -> here(1), f -> 2) == 1));
    assertReach(() -> assertTrue(mapIfOrElse(f -> false, f -> 1, f -> here(2)) == 2));
    assertTrue(filter(f -> true).isPresent());
    assertFalse(filter(f -> false).isPresent());
    toOptional();
    toStream();
    toFlowable();
    toObservable();
    toMaybe();
    toSingle();
  }

  private void assertReach(int count, Runnable r) {
    try {
      r.run();
      assertTrue("Not reach there.", here == count);
    } finally {
      here = 0;
    }
  }

  private void assertReach(Runnable r) {
    assertReach(1, r);
  }

  private void assertNotReach(Runnable r) {
    assertReach(0, r);
  }

  private <T> T here(T t) {
    here++;
    return t;
  }

  private <T> T here() {
    return here(null);
  }
}
