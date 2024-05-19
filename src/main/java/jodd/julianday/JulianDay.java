package jodd.julianday;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jodd.julianday.JulianDayFunctions.gregorianToJd;
import static jodd.julianday.JulianDayFunctions.hmsj;
import static jodd.julianday.JulianDayFunctions.jdToGregorian;
import static jodd.julianday.JulianDayFunctions.jdToJulian;
import static jodd.julianday.JulianDayFunctions.jhms;
import static jodd.julianday.JulianDayFunctions.julianDay;
import static jodd.julianday.JulianDayFunctions.julianToJd;

public class JulianDay {

    /**
     * Julian Day when Reduced Julian Day (RJD) is 0.
     * RJD = JD − 2400000
     */
    public static final JulianDay REDUCED_JULIAN_DAY_0 = new JulianDay(2400000, 0);

    /**
     * Julian Day when Modified Julian Day (MJD) is 0.
     * MJD = JD − 2400000.5
     */
    public static final JulianDay MODIFIED_JULIAN_DAY_0 = new JulianDay(2400000, 0.5);

    /**
     * Julian Day when Truncated Julian Day (TJD) is 0.
     * TJD began at midnight at the beginning of May 24, 1968 (Friday).
     */
    public static final JulianDay TRUNCATED_JULIAN_DAY_0 = new JulianDay(2440000, 0.5);

    private final DayValue value;

    /**
     * Returns integer part of the Julian Day.
     */
    public int day() {
        return this.value.day();
    }

    /**
     * Returns the fraction part of Julian Day (JD).
     * Returned value is always in [0.0, 1.0) range.
     */
    public double time() {
        return this.value.time();
    }

    /**
     * Creates Julian Day from day and time values.
     * If time is out of [0.0, 1.0) range, it will be normalized and day will be adjusted accordingly.
     */
    public JulianDay(final int day, final double time) {
        final int timeDays = (int) time;
        final int daysCorrected = day + timeDays;
        final double timeCorrected = time - timeDays;

        if (timeCorrected < 0) {
            this.value = new DayValue(daysCorrected - 1, timeCorrected + 1);
        } else {
            this.value = new DayValue(daysCorrected, timeCorrected);
        }
    }

    // <editor-fold desc="Arithmetic">

    /**
     * Adds another Julian Day to this one.
     */
    public JulianDay add(final JulianDay jd) {
        return new JulianDay(this.day() + jd.day(), this.time() + jd.time());
    }

    /**
     * Adds days to this Julian Day.
     */
    public JulianDay add(final double days) {
        return new JulianDay(this.day() + (int) days, this.time() + (days - (int) days));
    }

    /**
     * Adds days to this Julian Day.
     */
    public JulianDay add(final DayValue value) {
        return new JulianDay(this.day() + value.day(), this.time() + value.time());
    }

    /**
     * Subtracts another Julian Day from this one.
     */
    public JulianDay subtract(final JulianDay jd) {
        return new JulianDay(this.day() - jd.day(), this.time() - jd.time());
    }

    /**
     * Subtracts days from this Julian Day.
     */
    public JulianDay subtract(final double days) {
        return new JulianDay(this.day() - (int) days, this.time() - (days - (int) days));
    }

    /**
     * Subtracts days from this Julian Day.
     */
    public JulianDay subtract(final DayValue value) {
        return new JulianDay(this.day() - value.day(), this.time() - value.time());
    }

    // </editor-fold>

    // <editor-fold desc="TO converters">

    /**
     * Converts Julian Day to less precise double value.
     * <b>Warning:</b> double values are not precise enough to represent all Julian Days.
     */
    public double toDouble() {
        return this.value.toDouble();
    }

    /**
     * Converts Julian Day to BigDecimal.
     */
    public BigDecimal toBigDecimal() {
        return this.value.toBigDecimal();
    }

    /**
     * Converts Julian Day to Julian calendar date.
     */
    public JulianDateTime toJulianDate() {
        final int[] d = jdToJulian(this);
        final int[] t = jhms(this);
        return new JulianDateTime(d[0], d[1], d[2], t[0], t[1], t[2], t[3]);
    }

    /**
     * Converts Julian Day to Gregorian calendar date.
     */
    public LocalDateTime toGregorianDate() {
        final int[] d = jdToGregorian(this);
        final int[] t = jhms(this);
        return LocalDateTime.of(d[0], d[1], d[2], t[0], t[1], t[2], t[3] * 1_000_000);
    }

    /**
     * Converts Julian Day to Unix time in milliseconds.
     */
    public long toUnixMilliseconds() {
        return JulianDayFunctions.toUnixMillis(this);
    }

    /**
     * Returns string representation of Julian Day.
     */
    @Override
    public String toString() {
        final String s = Double.toString(this.time());
        final int i = s.indexOf('.');
        return this.day() + s.substring(i);
    }

    // </editor-fold>


    // <editor-fold desc="OF Ctors">

    /**
     * Creates Julian Day from day and time values.
     */
    public static JulianDay of(final int day, final double time) {
        return new JulianDay(day, time);
    }

    /**
     * Creates Julian Day from less precise double value of days.
     * <b>Warning:</b> double values are not precise enough to represent all Julian Days.
     */
    public static JulianDay of(final double jd) {
        final int integer = (int) jd;
        final double fraction = jd - (double) integer;
        return new JulianDay(integer, fraction);
    }

    /**
     * Creates Julian Day from BigDecimal.
     */
    public static JulianDay of(final BigDecimal bigDecimal) {
        final double d = bigDecimal.doubleValue();
        final int integer = (int) d;
        final double fraction = bigDecimal.subtract(new BigDecimal(integer)).doubleValue();
        return new JulianDay(integer, fraction);
    }

    /**
     * Creates Julian Day from Julian calendar date.
     */
    public static JulianDay ofJulianDate(final JulianDateTime jdt) {
        final double jd = julianToJd(jdt.getYear(), jdt.getMonthValue(), jdt.getDayOfMonth());
        final double jt = hmsj(jdt.getHour(), jdt.getMinute(), jdt.getSecond(), jdt.getMillisecond());
        return julianDay(jd, jt);
    }

    /**
     * Creates Julian Day from Gregorian calendar date.
     */
    public static JulianDay ofGregorianDate(final LocalDateTime ldt) {
        final double jd = gregorianToJd(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
        final double jt = hmsj(ldt.getHour(), ldt.getMinute(), ldt.getSecond(), ldt.getNano() / 1_000_000);
        return julianDay(jd, jt);
    }

    /**
     * Creates Julian Day from Gregorian calendar date.
     */
    public static JulianDay ofGregorianDate(final LocalDate ld) {
        final double jd = gregorianToJd(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        return julianDay(jd, 0);
    }

    /**
     * Creates Julian Day from Unix time in milliseconds.
     */
    public static JulianDay ofUnixMilliseconds(final long milliseconds) {
        return JulianDay.of(JulianDayFunctions.fromUnixMillis(milliseconds));
    }

    /**
     * Creates Julian Day from current Gregorian calendar date and time.
     */
    public static JulianDay now() {
        return JulianDay.ofGregorianDate(LocalDateTime.now());
    }

    // </editor-fold>

    // <editor-fold desc="VALUES">

    /**
     * Returns the precise value of this Julian Day.
     */
    private DayValue value() {
        return this.value;
    }

    /**
     * Returns the precise value of this Julian Day as Reduced Julian Day (RJD).
     */
    public DayValue valueAsReducedJulianDay() {
        return this.subtract(REDUCED_JULIAN_DAY_0).value();
    }

    /**
     * Returns the precise value of this Julian Day as Modified Julian Day (MJD).
     */
    public DayValue valueAsModifiedJulianDay() {
        return this.subtract(MODIFIED_JULIAN_DAY_0).value();
    }

    /**
     * Returns the precise value of this Julian Day as truncated Julian Day (TJD).
     */
    public DayValue valueAsTruncatedJulianDay() {
        return this.subtract(TRUNCATED_JULIAN_DAY_0).value();
    }

    /**
     * Creates Julian Day from precise value of days.
     */
    public static JulianDay of(final DayValue value) {
        return new JulianDay(value.day(), value.time());
    }

    /**
     * Creates Julian Day from precise value of Reduced Julian Day (RJD).
     */
    public static JulianDay ofReducedJulianDay(final DayValue value) {
        return JulianDay.REDUCED_JULIAN_DAY_0.add(value);
    }

    /**
     * Creates Julian Day from precise value of Modified Julian Day (MJD).
     */
    public static JulianDay ofModifiedJulianDay(final DayValue value) {
        return JulianDay.MODIFIED_JULIAN_DAY_0.add(value);
    }

    /**
     * Creates Julian Day from precise value of truncated Julian Day (TJD).
     */
    public static JulianDay ofTruncatedJulianDay(final DayValue value) {
        return JulianDay.TRUNCATED_JULIAN_DAY_0.add(value);
    }

    // </editor-fold>

    // <editor-fold desc="equals & hashCode & clone" defaultstate="collapsed">

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        final JulianDay stamp = (JulianDay) object;
        return  this.value.equals(stamp.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    protected JulianDay clone() {
        return new JulianDay(this.day(), this.time());
    }

    // </editor-fold>

}

