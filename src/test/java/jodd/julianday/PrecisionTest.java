package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrecisionTest {

    @Test
    void test_decimalFloating() {
        final LocalDateTime ldt = LocalDateTime.of(1970, 1, 13, 14, 24, 0, 0);
        final JulianDay jd = new JulianDay(2440600, 0.1);

        assertTrue(ldt.isEqual(jd.toGregorianDate()));

        final JulianDay jd2 = new JulianDay(2440600, 0.09999999991);
        assertTrue(ldt.isEqual(jd2.toGregorianDate()));

        final JulianDay jdt3 = new JulianDay(2440600, 0.100000001);
        assertTrue(ldt.isEqual(jdt3.toGregorianDate()));
    }

    @Test
    void test_iterateAllMilliseconds() {
        for (int i = 0; i < 1000; i++) {
            final LocalDateTime ldt = LocalDateTime.of(2003, 2, 28, 23, 59, 59, i * 1_000_000);
            final JulianDay jd = JulianDay.ofGregorianDate(ldt);
            final LocalDateTime ldt2 = jd.toGregorianDate();
            assertEquals(i * 1_000_000, ldt2.getNano(), "Failed at " + i);
        }
    }

    @Test
    void test_iterateAllUnixSeconds_firstYear() {
        for (int i = 0; i < 86400 * 365; i++) {
            final JulianDay jd = JulianDay.ofUnixMilliseconds(i * 1_000L);
            final LocalDateTime ldt = jd.toGregorianDate();
            assertEquals(i % 60, ldt.getSecond(), "Failed at " + i);
            assertEquals(i * 1_000L, jd.toUnixMilliseconds(), "Failed at " + i);
        }
    }

    @Test
    void test_set999Millis() {
        {
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(2003, 2, 28, 23, 59, 59, 999));
            assertEquals("2003-02-28J23:59:59.999", jd.toJulianDate().toString());
        }
        {
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(2003, 2, 28, 23, 59, 60, 0));
            assertEquals("2003-03-01J00:00:00.000", jd.toJulianDate().toString());
        }
        {
            // this used to be a problem
            final JulianDay jd = JulianDay.ofJulianDate(new JulianDateTime(2003, 2, 28, 23, 59, 59, 999));        // 12 fraction digits  - last working
            assertEquals("2003-02-28J23:59:59.999", jd.toJulianDate().toString());
        }
    }


    /**
     * This test just verifies that the BigDecimal values
     * are "close", e.g. within double precision.
     * They should be even closer than that,
     * but that would be more of a pain to test.
     */
    @Test
    void test_toAndFromBigDecimal() {
        JulianDay jd = JulianDay.of(2457754, 0.4);
        BigDecimal bigDecimal = BigDecimal.valueOf(2457754.4);
        assertEquals(bigDecimal.doubleValue(), jd.toBigDecimal().doubleValue());
        assertEquals(bigDecimal.doubleValue(), JulianDay.of(bigDecimal).toBigDecimal().doubleValue());

        jd = JulianDay.of(2457754, 0.5);
        bigDecimal = BigDecimal.valueOf(2457754.5);
        assertEquals(bigDecimal.doubleValue(), jd.toBigDecimal().doubleValue());
        assertEquals(bigDecimal.doubleValue(), JulianDay.of(bigDecimal).toBigDecimal().doubleValue());

        jd = JulianDay.of(2457754, 0.6);
        bigDecimal = BigDecimal.valueOf(2457754.6);
        assertEquals(bigDecimal.doubleValue(), jd.toBigDecimal().doubleValue());
        assertEquals(bigDecimal.doubleValue(), JulianDay.of(bigDecimal).toBigDecimal().doubleValue());

        jd = JulianDay.of(2457754, 0.7);
        bigDecimal = BigDecimal.valueOf(2457754.7);
        assertEquals(bigDecimal.doubleValue(), jd.toBigDecimal().doubleValue());
        assertEquals(bigDecimal.doubleValue(), JulianDay.of(bigDecimal).toBigDecimal().doubleValue());

        jd = JulianDay.of(2457754, 0.8);
        bigDecimal = BigDecimal.valueOf(2457754.8);
        assertEquals(bigDecimal.doubleValue(), jd.toBigDecimal().doubleValue());
        assertEquals(bigDecimal.doubleValue(), JulianDay.of(bigDecimal).toBigDecimal().doubleValue());

        jd = JulianDay.of(2457448, 0.43219907);
        bigDecimal = BigDecimal.valueOf(2457448.43219907);
        assertEquals(bigDecimal.doubleValue(), jd.toBigDecimal().doubleValue());
        assertEquals(bigDecimal.doubleValue(), JulianDay.of(bigDecimal).toBigDecimal().doubleValue());
    }

    @Test
    void test_toAndFromDouble() {
        JulianDay jd = JulianDay.of(2457754, 0.4);
        double doubleValue = 2457754.4;
        assertEquals(doubleValue, jd.toDouble());
        assertEquals(doubleValue, JulianDay.of(doubleValue).toDouble());

        jd = JulianDay.of(2457754, 0.5);
        doubleValue = 2457754.5;
        assertEquals(doubleValue, jd.toDouble());
        assertEquals(doubleValue, JulianDay.of(doubleValue).toDouble());

        jd = JulianDay.of(2457754, 0.6);
        doubleValue = 2457754.6;
        assertEquals(doubleValue, jd.toDouble());
        assertEquals(doubleValue, JulianDay.of(doubleValue).toDouble());

        jd = JulianDay.of(2457754, 0.7);
        doubleValue = 2457754.7;
        assertEquals(doubleValue, jd.toDouble());
        assertEquals(doubleValue, JulianDay.of(doubleValue).toDouble());

        jd = JulianDay.of(2457754, 0.8);
        doubleValue = 2457754.8;
        assertEquals(doubleValue, jd.toDouble());
        assertEquals(doubleValue, JulianDay.of(doubleValue).toDouble());

        jd = JulianDay.of(2457448, 0.43219907);
        doubleValue = 2457448.43219907;
        assertEquals(doubleValue, jd.toDouble());
        assertEquals(doubleValue, JulianDay.of(doubleValue).toDouble());
    }


    @Test
    void testDeltaRounding() {
        test_deltaRounding(JulianDay.of(2457754, 0.4));
        test_deltaRounding(JulianDay.of(2457754, 0.6));
        test_deltaRounding(JulianDay.of(2457754, 0.7));
        test_deltaRounding(JulianDay.of(2457754, 0.8));
    }

    /**
     * Tests that more than 0.5 ms to rounds up and less than 0.5 ms rounds down
     * 0.5ms = 5.78703704e-9
     *
     * @param jd a JulianDay equal to a whole number of milliseconds
     */
    private static void test_deltaRounding(final JulianDay jd) {
        final long milliseconds = jd.toUnixMilliseconds();

        final double lessThanHalfMillisecond = 0.00000000578;       // < 0.5ms
        final double moreThanHalfMillisecond = 0.00000000579;       // > 0.5ms

        {
            final JulianDay jdSub = jd.subtract(lessThanHalfMillisecond);
            assertEquals(0, jdSub.toJulianDate().getMillisecond());
            assertEquals(milliseconds, jdSub.toUnixMilliseconds());

            final JulianDay jdAdd = jd.add(lessThanHalfMillisecond);
            assertEquals(0, jdAdd.toJulianDate().getMillisecond());
            assertEquals(milliseconds, jdAdd.toUnixMilliseconds());
        }

        {
            final JulianDay jdSub = jd.subtract(moreThanHalfMillisecond);
            assertEquals(999, jdSub.toJulianDate().getMillisecond());
            assertEquals(milliseconds - 1, jdSub.toUnixMilliseconds());

            final JulianDay jdAdd = jd.add(moreThanHalfMillisecond);
            assertEquals(1, jdAdd.toJulianDate().getMillisecond());
            assertEquals(milliseconds + 1, jdAdd.toUnixMilliseconds());
        }
    }

    @Test
    void test_halfMilliseconds() {
        for (double i = 0.0; i < 1; i += 0.1) {
            final int[] a = JulianDayFunctions.jhms(JulianDay.of(2457754, i + 0.00000000578));
            assertEquals(0, a[3]);

            final int[] b = JulianDayFunctions.jhms(JulianDay.of(2457754, i + 0.00000000579));
            assertEquals(1, b[3]);
        }
    }


}
