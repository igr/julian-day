package jodd.julianday;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class Issue4Test {

    public static Instant fromMJD(final double mjd) {
        return JulianDay.ofModifiedJulianDay(mjd).toInstant();
    }

    public static double toMJD(final Instant instant) {
        return JulianDay.ofInstant(instant).valueAsModifiedJulianDay().toDouble();
    }

    @Test
    public void test_time_convertsToMjd() {
        assertThat(toMJD(Instant.parse("2016-12-31T21:36:00Z")), is(57753.9));
        assertThat(toMJD(Instant.parse("2017-01-01T00:00:00Z")), is(57754.0));
        assertThat(toMJD(Instant.parse("2017-01-01T02:24:00Z")), is(57754.1));
        assertThat(toMJD(Instant.parse("2017-01-01T04:48:00Z")), is(57754.2));
        assertThat(toMJD(Instant.parse("2017-01-01T07:12:00Z")), is(57754.3));
    }
}
