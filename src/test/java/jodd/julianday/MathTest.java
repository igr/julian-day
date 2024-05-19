package jodd.julianday;

import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.floor;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MathTest {

    /**
     * Just want to be sure that I didn't miss something.
     */
    @Test
    void test_intCase_vs_floor() {
        assertEquals(1, (int) 1.5);
        assertEquals(1, (int) floor(1.5));

        assertEquals(-1, (int) -1.5);
        assertEquals(-2, (int) floor(-1.5));    // the difference!
    }
}
