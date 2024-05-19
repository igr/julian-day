package jodd.julianday;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueTest {

    @Test
    void test_reducedJulianDay() {
        for (int i = 0; i < 1_000_000; i++) {
            final DayValue date = new DayValue(i, 0.0);

            final JulianDay jd = JulianDay.ofReducedJulianDay(date);
            final DayValue value = jd.valueAsReducedJulianDay();

            assertEquals(date, value);
        }
    }

    @Test
    void test_modifiedJulianDay() {
        for (int i = 0; i < 1_000_000; i++) {
            final DayValue date = new DayValue(i, 0.0);

            final JulianDay jd = JulianDay.ofModifiedJulianDay(date);
            final DayValue value = jd.valueAsModifiedJulianDay();

            assertEquals(date, value);
        }
    }

    @Test
    void test_truncatedJulianDay() {
        for (int i = 0; i < 1_000_000; i++) {
            final DayValue date = new DayValue(i, 0.0);

            final JulianDay jd = JulianDay.ofTruncatedJulianDay(date);
            final DayValue value = jd.valueAsTruncatedJulianDay();

            assertEquals(date, value);
        }
    }
}
