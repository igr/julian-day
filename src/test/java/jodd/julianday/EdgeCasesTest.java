package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeCasesTest {

    /**
     * "2020-01-01 0:0:0 J" == 2458862.5
     * "2020-01-01 12:0:0 J" == 2458863
     * "2020-01-01 23:59:59 J" == 2458863.499988426
     */
    @Test
    void test_2020_01_01_12_0_0_0_J() {
        {
            final JulianDateTime expectedJdt = new JulianDateTime(2020, 1, 1, 0, 0, 0, 0);
            final JulianDay jd = JulianDay.ofJulianDate(expectedJdt);
            final JulianDateTime jdt = jd.toJulianDate();
            assertEquals(expectedJdt, jdt);
        }
        {
            final JulianDateTime expectedJdt = new JulianDateTime(2020, 1, 1, 12, 0, 0, 0);
            final JulianDay jd = JulianDay.ofJulianDate(expectedJdt);
            final JulianDateTime jdt = jd.toJulianDate();
            assertEquals(expectedJdt, jdt);
        }
    }

    @Test
    void test_2452699_499994224537037() {
        final JulianDay jd = new JulianDay(2452699, 0.499994224537037);
        final LocalDateTime ldt = jd.toGregorianDate();
        assertEquals(2003, ldt.getYear());
    }
}
