package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToJulianDayTest {

    @Test
    void test_to_jd_0() {
        {
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(-4712, 1, 1, 12, 0, 0, 0));
            assertEquals(0, jd.day());
            assertEquals(0.0, jd.time());
        }
        {
            final JulianDay jd = JulianDay.ofGregorianDate(LocalDateTime.of(-4713, 11, 24, 12, 0, 0, 0));
            assertEquals(0, jd.day());
            assertEquals(0.0, jd.time());
        }
    }

    /**
     * Test for: 100-02-29 J
     * This is a leap year in Julian calendar.
     * This is not a leap year in Gregorian calendar.
     */
    @Test
    void test_to_jd_1757641_5() {
        {
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(100, 2, 29, 0, 0, 0, 0));
            assertEquals(1757641, jd.day());
            assertEquals(0.5, jd.time());
        }
        {
            final JulianDay jd = JulianDay.ofGregorianDate(LocalDateTime.of(100, 2, 27, 0, 0, 0, 0));
            assertEquals(1757641, jd.day());
            assertEquals(0.5, jd.time());
        }
    }

    /**
     * Test for: 1582-10-04 J
     * This is a day before the Gregorian calendar was introduced.
     */
    @Test
    void test_to_jd_2299159_5() {
        {
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(1582, 10, 4, 0, 0, 0, 0));
            assertEquals(2299159, jd.day());
            assertEquals(0.5, jd.time());
        }
        {
            final JulianDay jd = JulianDay.ofGregorianDate(LocalDateTime.of(1582, 10, 14, 0, 0, 0, 0));
            assertEquals(2299159, jd.day());
            assertEquals(0.5, jd.time());
        }
    }

    /**
     * Test for: 1582-10-05 J
     * This is the first day of the Gregorian calendar.
     */
    @Test
    void test_to_jd_2299160_5() {
        {
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(1582, 10, 5, 0, 0, 0, 0));
            assertEquals(2299160, jd.day());
            assertEquals(0.5, jd.time());
        }
        {
            final JulianDay jd = JulianDay.ofGregorianDate(LocalDateTime.of(1582, 10, 15, 0, 0, 0, 0));
            assertEquals(2299160, jd.day());
            assertEquals(0.5, jd.time());
        }
    }

    @Test
    void test_to_jd_2436116_3115() {
        {
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(1957, 9, 21, 19, 28, 34, 0));
            assertEquals(2436116, jd.day());
            assertEquals(0.3115046296, jd.time(), 1E-8);
        }
        {
            final JulianDay jd = JulianDay.ofGregorianDate(LocalDateTime.of(1957, 10, 4, 19, 28, 34, 0));
            assertEquals(2436116, jd.day());
            assertEquals(0.3115046296, jd.time(), 1E-8);
        }
    }
}
