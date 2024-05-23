package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JulianDayNumberTest {

    @Test
    void test_2023_5_23() {
        final JulianDay jd = JulianDay.ofGregorianDate(LocalDate.of(2023,5,23));
        assertEquals(2460087, jd.day());
        assertEquals(0.5, jd.time());
        assertEquals(2460088, jd.julianDayNumber());
    }
}
