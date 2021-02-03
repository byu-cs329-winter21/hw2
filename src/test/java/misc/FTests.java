package misc;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing ff method...")
public class FTests {

  @Nested
  @DisplayName("x exists in array...")
  class inArray {

    Object x = 2;

    @Test
    @Tag("x_exists")
    @DisplayName("x already in graph")
    public void inGraph() {
      Object[] array = {1, 2, 3};

      Misc.ff(x, array);

      assertEquals(array.length, 3);
    }

    @Test
    @Tag("x_not_exist")
    @DisplayName("x goes in null")
    public void inNull() {
      Object[] array = {1, null, 3};

      Misc.ff(x, array);

      assertEquals(array.length, 3);
    }
  }

}
