package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FromJulianDayTest {

    @Test
    void test_from_jd_0() {
        final JulianDay jd = JulianDay.of(0);
        assertEquals(0, jd.day());
        assertEquals(0.0, jd.time());

        final JulianDateTime jdt = jd.toJulianDate();
        assertEquals(-4712, jdt.getYear());
        assertEquals(1, jdt.getMonthValue());
        assertEquals(1, jdt.getDayOfMonth());
        assertEquals(12, jdt.getHour());
        assertEquals(0, jdt.getMinute());
        assertEquals(0, jdt.getSecond());
        assertEquals(0, jdt.getMillisecond());

        final LocalDateTime ldt = jd.toGregorianDate();
        assertEquals(-4713, ldt.getYear());
        assertEquals(11, ldt.getMonthValue());
        assertEquals(24, ldt.getDayOfMonth());
        assertEquals(12, ldt.getHour());
        assertEquals(0, ldt.getMinute());
        assertEquals(0, ldt.getSecond());
        assertEquals(0, ldt.getNano());
    }

    /**
     * Test for: 100-02-29 J
     * This is a leap year in Julian calendar.
     * This is not a leap year in Gregorian calendar.
     */
    @Test
    void test_from_jd_1757641_5() {
        final JulianDay jd = JulianDay.of(1757641.5);
        assertEquals(1757641, jd.day());
        assertEquals(0.5, jd.time());

        final JulianDateTime jdt = jd.toJulianDate();
        assertEquals(100, jdt.getYear());
        assertEquals(2, jdt.getMonthValue());
        assertEquals(29, jdt.getDayOfMonth());
        assertEquals(0, jdt.getHour());
        assertEquals(0, jdt.getMinute());
        assertEquals(0, jdt.getSecond());

        final LocalDateTime ldt = jd.toGregorianDate();
        assertEquals(100, ldt.getYear());
        assertEquals(2, ldt.getMonthValue());
        assertEquals(27, ldt.getDayOfMonth());
        assertEquals(0, ldt.getHour());
        assertEquals(0, ldt.getMinute());
        assertEquals(0, ldt.getSecond());
        assertEquals(0, ldt.getNano());
    }

    /**
     * Test for: 1582-10-04 J
     * This is a day before the Gregorian calendar was introduced.
     */
    @Test
    void test_from_jd_2299159_5() {
        final JulianDay jd = JulianDay.of(2299159.5);
        assertEquals(2299159, jd.day());
        assertEquals(0.5, jd.time());

        final JulianDateTime jdt = jd.toJulianDate();
        assertEquals(1582, jdt.getYear());
        assertEquals(10, jdt.getMonthValue());
        assertEquals(4, jdt.getDayOfMonth());
        assertEquals(0, jdt.getHour());
        assertEquals(0, jdt.getMinute());
        assertEquals(0, jdt.getSecond());
        assertEquals(0, jdt.getMillisecond());

        final LocalDateTime ldt = jd.toGregorianDate();
        assertEquals(1582, ldt.getYear());
        assertEquals(10, ldt.getMonthValue());
        assertEquals(14, ldt.getDayOfMonth());
        assertEquals(0, ldt.getHour());
        assertEquals(0, ldt.getMinute());
        assertEquals(0, ldt.getSecond());
        assertEquals(0, ldt.getNano());
    }

    /**
     * Test for: 1582-10-05 J
     * This is the first day of the Gregorian calendar.
     */
    @Test
    void test_from_jd_2299160_5() {
        final JulianDay jd = JulianDay.of(2299160.5);
        assertEquals(2299160, jd.day());
        assertEquals(0.5, jd.time());

        final JulianDateTime jdt = jd.toJulianDate();
        assertEquals(1582, jdt.getYear());
        assertEquals(10, jdt.getMonthValue());
        assertEquals(5, jdt.getDayOfMonth());
        assertEquals(0, jdt.getHour());
        assertEquals(0, jdt.getMinute());
        assertEquals(0, jdt.getSecond());
        assertEquals(0, jdt.getMillisecond());

        final LocalDateTime ldt = jd.toGregorianDate();
        assertEquals(1582, ldt.getYear());
        assertEquals(10, ldt.getMonthValue());
        assertEquals(15, ldt.getDayOfMonth());
        assertEquals(0, ldt.getHour());
        assertEquals(0, ldt.getMinute());
        assertEquals(0, ldt.getSecond());
        assertEquals(0, ldt.getNano());
    }

    /**
     * Day of Sputnik Launch
     */
    @Test
    void test_from_jd_2436116_3115() {
        final JulianDay jd = JulianDay.of(2436116, 0.3115046296);
        assertEquals(2436116, jd.day());
        assertEquals(0.3115046296, jd.time());

        final JulianDateTime jdt = jd.toJulianDate();
        assertEquals(1957, jdt.getYear());
        assertEquals(9, jdt.getMonthValue());
        assertEquals(21, jdt.getDayOfMonth());
        assertEquals(19, jdt.getHour());
        assertEquals(28, jdt.getMinute());
        assertEquals(34, jdt.getSecond());
        assertEquals(0, jdt.getMillisecond());

        final LocalDateTime ldt = jd.toGregorianDate();
        assertEquals(1957, ldt.getYear());
        assertEquals(10, ldt.getMonthValue());
        assertEquals(4, ldt.getDayOfMonth());
        assertEquals(19, ldt.getHour());
        assertEquals(28, ldt.getMinute());
        assertEquals(34, ldt.getSecond());
        assertEquals(0, ldt.getNano());
    }
}
