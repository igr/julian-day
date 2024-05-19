package jodd.julianday;

import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.round;

/**
 * Internal Julian Day calculations.
 * All math calculations are made here.
 */
class JulianDayFunctions {

    static final double GREGORIAN_EPOCH = 1721425.5;
    static final double J1970 = 2440587.5;

    /**
     * Converts Julian day to Julian calendar date(year, month and day).
     */
    static int[] jdToJulian(final JulianDay jd) {
        final double r = jd.toDouble() + .5;
        final double t = floor(r);
        final double n = t + 1524;
        final double s = floor((n - 122.1) / 365.25);
        final double o = floor(365.25 * s);
        final double u = floor((n - o) / 30.6001);
        final double h = floor(u < 14 ? u - 1 : u - 13);
        final double i = floor(h > 2 ? s - 4716 : s - 4715);
        final double l = n - o - floor(30.6001 * u);
        return new int[]{(int)i, (int)h, (int)l};
    }

    /**
     * Converts Julian calendar date to Julian day.
     * Algorithm as given in Meeus, Astronomical Algorithms, Chapter 7, page 61.
     */
    static double julianToJd(int year, int month, final int day) {
        if (month <= 2) {
            year--;
            month += 12;
        }

        return (floor((365.25 * (year + 4716))) +
                floor((30.6001 * (month + 1))) +
                day) - 1524.5;
    }

    /**
     * Converts Julian day to Gregorian calendar date(year, month and day).
     */
    static int[] jdToGregorian(final JulianDay jd) {
        final double wjd = floor(jd.toDouble() - 0.5) + 0.5;
        final double depoch = wjd - GREGORIAN_EPOCH;
        final double quadricent = floor(depoch / 146097);
        final double dqc = mod(depoch, 146097);
        final double cent = floor(dqc / 36524);
        final double dcent = mod(dqc, 36524);
        final double quad = floor(dcent / 1461);
        final double dquad = mod(dcent, 1461);
        final double yindex = floor(dquad / 365);
        int year = (int) ((quadricent * 400) + (cent * 100) + (quad * 4) + yindex);
        if (!(cent == 4 || yindex == 4)) {
            year++;
        }
        final double yearday = wjd - gregorianToJd(year, 1, 1);
        final double leapadj = (wjd < gregorianToJd(year, 3, 1)) ?
                0 :
                (isLeapYear(year) ? 1 : 2);
        final int month = (int) floor((((yearday + leapadj) * 12) + 373) / 367);
        final double day = (wjd - gregorianToJd(year, month, 1)) + 1;

        return new int[]{year, month, (int) day};
    }

    /**
     *  Converts Julian time to hour, minutes, seconds and milliseconds,
     *  returned as a four-element array.
     */
    static int[] jhms(final JulianDay jd) {
        final double civil = jd.time() + 0.5;            // astronomical to civil

        final double a = (civil - floor(civil)) * 86400 + 0.5;
        int h = (int) floor(a / 3600);
        int m = (int) floor(a / 60 % 60);
        final double sm = a % 60;    // seconds with millis
        int s = (int) floor(sm);
        final double millis = ((sm - s - 0.5) * 1000);
        int roundedMillis = (int) round(millis);

        if (roundedMillis < 0) {
            roundedMillis += 1000;
            s--;
        }
        if (s < 0) {
            s += 60;
            m--;
        }
        if (m < 0) {
            m += 60;
            h--;
        }

        return new int[] {h, m, s, roundedMillis};
    }

    /**
     * Converts hour, minutes, seconds and milliseconds to Julian time, a rational day offset.
     */
    static double hmsj(final int h, final int m, final int s, final int millis) {
        return (h * 3600 + m * 60 + s) / 86400.0 + (millis / 86400000.0);
    }

    /**
     * Converts Gregorian calendar date to Julian day.
     */
    static double gregorianToJd(final double year, final double month, final double day) {
        return GREGORIAN_EPOCH - 1
                + 365 * (year - 1)
                + floor((year - 1) / 4)
                - floor((year - 1) / 100)
                + floor((year - 1) / 400)
                + floor((367 * month - 362) / 12
                + (month <= 2 ? 0 : (isLeapYear(year) ? -1 : -2)) + day);
    }

    /**
     * Checks if a year is a leap year by following the standard rules from Gregorian calendar:
     * a year is a leap year if it is divisible by 4, but not by 100, unless it is also divisible by 400.
     */
    static boolean isLeapYear(final double year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * Checks if a year is a leap year by following the standard rules from Julian calendar:
     * a year is a leap year if it is divisible by 4.
     */
    static boolean isLeapJulianYear(final int julianYear) {
        return julianYear % 4 == 0;
    }

    /**
     * Modulus function which works for non-integers.
     */
    static double mod(final double a, final double b) {
        return a - b * floor(a / b);
    }

    /**
     * High-precision calculation of Julian Day from given day and time value.
     * We can't simply add day and time, because of floating point precision:
     * day is a big number and time is a small number. Hence, we need to add them separately.
     */
    static JulianDay julianDay(final double dayAtMidnight, final double timeOfDay) {
        final double jd = dayAtMidnight + timeOfDay;

        final double dayFraction = dayAtMidnight - (int) dayAtMidnight;
        final double timeOffset = dayFraction + timeOfDay;
        final double time = timeOffset >= 1 ? timeOffset - 1 : timeOffset;

        return new JulianDay((int)jd, time);
    }

    /**
     * Converts Julian Day to Unix time in milliseconds.
     */
    static long toUnixMillis(final JulianDay jd) {
        final long utime = ((long)(jd.toDouble() - J1970) * 86_400_000L);
        final int[] t = jhms(jd);
        return utime + t[0] * 3_600_000L + t[1] * 60_000L + t[2] * 1_000L + t[3];

        // due to rounding errors, the following code is not used
        // instead, we first calculate the date difference in days
        // and then use the same calculation to get hour/minute/second/millisecond value
        // finally we add them together
        //return round(utime);
    }

    /**
     * Converts Unix time in milliseconds to Julian Day.
     */
    static double fromUnixMillis(final long unixMillis) {
        return (unixMillis / 8_6400_000.0) + J1970;
    }
}
