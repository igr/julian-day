package jodd.julianday;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllSecondsOfDayTest {

    @Test
    void test_iterate_all_julian_date_seconds() {
        for (int i = 0; i < 86400; i++) {
            final int h = i / 3600;
            final int m = (i % 3600) / 60;
            final int s = i % 60;
            final JulianDateTime expectedJdt = new JulianDateTime(2020, 1, 1, h, m, s, 0);
            final JulianDay jd = JulianDay.ofJulianDate(expectedJdt);
            final JulianDateTime jdt = jd.toJulianDate();
            assertEquals(expectedJdt, jdt, "Failed at " + i + " seconds of the day");
        }
    }
}
