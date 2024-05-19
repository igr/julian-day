package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetAndGetTests {

    @Test
    void test_oneDayDifference() {
        final JulianDay jdt = JulianDay.ofJulianDate(new JulianDateTime(2008, 12, 20, 10, 44, 55, 0));
        final JulianDay jdt2 = JulianDay.of(jdt.day() - 1, jdt.time());

        final LocalDateTime ldt1 = jdt.toGregorianDate();
        final LocalDateTime ldt2 = jdt2.toGregorianDate();
        assertEquals(ldt1.getYear(), ldt2.getYear());
        assertEquals(ldt1.getMonth(), ldt2.getMonth());
        assertEquals(ldt1.getDayOfMonth() - 1, ldt2.getDayOfMonth());
        assertEquals(ldt1.getHour(), ldt2.getHour());
        assertEquals(ldt1.getMinute(), ldt2.getMinute());
        assertEquals(ldt1.getSecond(), ldt2.getSecond(), 0.0001);
    }

    @Test
    void test_toAndFromLocalDateTime() {
        LocalDateTime ldt = LocalDateTime.of(2016, 12, 31, 21, 36, 0, 0);
        JulianDay jd = JulianDay.ofGregorianDate(ldt);
        assertEquals(ldt, jd.toGregorianDate());
        assertEquals(ldt, JulianDay.ofGregorianDate(ldt).toGregorianDate());

        ldt = LocalDateTime.of(2017, 1, 1, 0, 0, 0, 0);
        jd = JulianDay.ofGregorianDate(ldt);
        assertEquals(ldt, jd.toGregorianDate());
        assertEquals(ldt, JulianDay.ofGregorianDate(ldt).toGregorianDate());

        ldt = LocalDateTime.of(2017, 1, 1, 2, 24, 0, 0);
        jd = JulianDay.ofGregorianDate(ldt);
        assertEquals(ldt, jd.toGregorianDate());
        assertEquals(ldt, JulianDay.ofGregorianDate(ldt).toGregorianDate());

        ldt = LocalDateTime.of(2017, 1, 1, 4, 48, 0, 0);
        jd = JulianDay.ofGregorianDate(ldt);
        assertEquals(ldt, jd.toGregorianDate());
        assertEquals(ldt, JulianDay.ofGregorianDate(ldt).toGregorianDate());

        ldt = LocalDateTime.of(2017, 1, 1, 7, 12, 0, 0);
        jd = JulianDay.ofGregorianDate(ldt);
        assertEquals(ldt, jd.toGregorianDate());
        assertEquals(ldt, JulianDay.ofGregorianDate(ldt).toGregorianDate());

        ldt = LocalDateTime.of(2016, 2, 29, 22, 22, 22, 0);
        jd = JulianDay.ofGregorianDate(ldt);
        assertEquals(ldt, jd.toGregorianDate());
        assertEquals(ldt, JulianDay.ofGregorianDate(ldt).toGregorianDate());
    }

    @Test
    void test_toAndFromUnixMilliseconds() {
        JulianDay jd = JulianDay.ofGregorianDate(LocalDateTime.of(2016, 12, 31, 21, 36, 0, 0));
        long milliseconds = 1483220160_000L;
        assertEquals(jd, JulianDay.ofUnixMilliseconds(milliseconds));
        assertEquals(milliseconds, jd.toUnixMilliseconds());
        assertEquals(milliseconds, JulianDay.ofUnixMilliseconds(milliseconds).toUnixMilliseconds());

        jd = JulianDay.ofGregorianDate(LocalDateTime.of(2017, 1, 1, 0, 0, 0, 0));
        milliseconds = 1483228800_000L;
        assertEquals(jd, JulianDay.ofUnixMilliseconds(milliseconds));
        assertEquals(milliseconds, jd.toUnixMilliseconds());
        assertEquals(milliseconds, JulianDay.ofUnixMilliseconds(milliseconds).toUnixMilliseconds());
    }

    @Test
    void test_calendarDateTimeCloseToJulianDateTime() {
        JulianDay jd1 = JulianDay.ofGregorianDate(LocalDateTime.of(2016, 12, 31, 21, 36, 0, 0));
        JulianDay jd2 = JulianDay.of(2457754, 0.4);
        assertEquals(jd1.toDouble(), jd2.toDouble());
        assertEquals(jd1.toUnixMilliseconds(), jd2.toUnixMilliseconds());

        jd1 = JulianDay.ofGregorianDate(LocalDateTime.of(2017, 1, 1, 0, 0, 0, 0));
        jd2 = JulianDay.of(2457754, 0.5);
        assertEquals(jd1.toDouble(), jd2.toDouble());
        assertEquals(jd1.toUnixMilliseconds(), jd2.toUnixMilliseconds());

        jd1 = JulianDay.ofGregorianDate(LocalDateTime.of(2017, 1, 1, 2, 24, 0, 0));
        jd2 = JulianDay.of(2457754, 0.6);
        assertEquals(jd1.toDouble(), jd2.toDouble());
        assertEquals(jd1.toUnixMilliseconds(), jd2.toUnixMilliseconds());

        jd1 = JulianDay.ofGregorianDate(LocalDateTime.of(2017, 1, 1, 4, 48, 0, 0));
        jd2 = JulianDay.of(2457754, 0.7);
        assertEquals(jd1.toDouble(), jd2.toDouble());
        assertEquals(jd1.toUnixMilliseconds(), jd2.toUnixMilliseconds());

        jd1 = JulianDay.ofGregorianDate(LocalDateTime.of(2017, 1, 1, 7, 12, 0, 0));
        jd2 = JulianDay.of(2457754, 0.8);
        assertEquals(jd1.toDouble(), jd2.toDouble());
        assertEquals(jd1.toUnixMilliseconds(), jd2.toUnixMilliseconds());

        jd1 = JulianDay.ofGregorianDate(LocalDateTime.of(2016, 2, 29, 22, 22, 22, 0));
        jd2 = JulianDay.of(2457448, 0.43219907);
        assertEquals(jd1.toDouble(), jd2.toDouble(), 10e-9);
        assertEquals(jd1.toUnixMilliseconds(), jd2.toUnixMilliseconds());
    }

}
