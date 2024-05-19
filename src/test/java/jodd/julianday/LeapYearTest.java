package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeapYearTest {

    /**
     * This test should not throw any exception.
     */
    @Test
    void test_leapYear() {
        final int start = JulianDay.ofGregorianDate(LocalDate.of(0, 1, 1)).day();
        final int end = JulianDay.ofGregorianDate(LocalDate.of(9999, 12, 31)).day();

        int assertCount = 0;
        for (int i = start; i <= end; i++) {
            final JulianDay date = new JulianDay(i, 0.5);
            final LocalDateTime l = date.toGregorianDate();

            assertNotNull(l);
            if (i == start) {
                assertEquals(0, l.getYear());
                assertEquals(1, l.getMonthValue());
                assertEquals(1, l.getDayOfMonth());
                assertCount++;
            } else if (i == end) {
                assertEquals(9999, l.getYear());
                assertEquals(12, l.getMonthValue());
                assertEquals(31, l.getDayOfMonth());
                assertCount++;
            }
        }

        assertEquals(2, assertCount);
    }
}
